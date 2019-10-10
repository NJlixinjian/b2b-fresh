package cn.sigo.sigocloudprovider.controller;

import cn.sigo.sigocloudprovider.elastic.model.*;
import cn.sigo.sigocloudprovider.elastic.service.ElasticOrderKey;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Auther: lxj
 * @Date: 2019/9/5 14:57
 * @Description:
 */
@RestController
public class ElasticSearchController {

//    private ElasticService elasticService;

    private TransportClient client = null;

    @Value("${spring.es.ip}")
    public String SPRING_ES_IP;

    @Value("${spring.es.port}")
    public  Integer SPRING_ES_PORT;

    @Value("${spring.es.cluster.name}")
    public  String SPRING_ES_CLUSTER_NAME;

    @RequestMapping(value = "/order/getlist", method = RequestMethod.POST)
    @ResponseBody
    public PageList searchOrderLists(@RequestBody OrderEsSearchDto orderEsSearchDto) {
        PageList pageList = new PageList();
        PageInfo pageInfo = new PageInfo();
        try {
            //创建es客户端
            Settings settings = Settings.builder().put(ElasticOrderKey.CLUSTER_NAME, SPRING_ES_CLUSTER_NAME).build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(SPRING_ES_IP), SPRING_ES_PORT));
//            client = elasticService.getElasticClient();//创建es客户端
            //订单金额
            AggregationBuilder sumOrderPrice = AggregationBuilders.sum("sum_price").field(ElasticOrderKey.ORDER_PRICE);
            //支付款金额
            AggregationBuilder sumActualPay = AggregationBuilders.sum("actual_pay").field(ElasticOrderKey.ACTUAL_PAY);
            //退款金额
            AggregationBuilder refundPay = AggregationBuilders.sum("refund_pay").field(ElasticOrderKey.TOTAL_AMOUNT);
            //状态数量统计
            AggregationBuilder termsBuilder = AggregationBuilders.terms("order_state_key").field(ElasticOrderKey.ORDER_STATE_KEY);
            AggregationBuilder orderStateKey = AggregationBuilders.count("order_state_key").field(ElasticOrderKey.ORDER_STATE_KEY);
            //统计买家数量
            AggregationBuilder buyerNum = AggregationBuilders.cardinality("buyer_num").field(ElasticOrderKey.BUYER_SHOP_ID);
            termsBuilder.subAggregation(orderStateKey);
//            termsBuilder.subAggregation(buyerNum);
            //查询条件
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (StringUtils.isNotBlank(orderEsSearchDto.getProduct_name())) {
                if (orderEsSearchDto.getParticiple() == 0) {
                    AnalyzeRequest an = new AnalyzeRequest().text(orderEsSearchDto.getProduct_name()).analyzer("ik_max_word");
                    List<AnalyzeResponse.AnalyzeToken> tokens = client.admin().indices()
                            .analyze(an)
                            .actionGet()
                            .getTokens();
                    // 循环赋值
                    List<String> searchTermList = new ArrayList<>();
                    tokens.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });
                    boolQueryBuilder.must(QueryBuilders.nestedQuery("sku", QueryBuilders.boolQuery().
                            must(QueryBuilders.matchQuery(ElasticOrderKey.PRODUCT_NAME, searchTermList).operator(Operator.OR)), ScoreMode.Total));
                } else {
                    boolQueryBuilder.must(QueryBuilders.nestedQuery("sku", QueryBuilders.boolQuery().
                            must(QueryBuilders.matchQuery(ElasticOrderKey.PRODUCT_NAME, orderEsSearchDto.getProduct_name()).operator(Operator.AND)), ScoreMode.Total));
                }
//                if (str.length > 1) {
//                    boolQueryBuilder.must(QueryBuilders.nestedQuery("sku", QueryBuilders.boolQuery().
//                            must(QueryBuilders.multiMatchQuery(orderEsSearchDto.getProduct_name(),ElasticOrderKey.PRODUCT_NAME).minimumShouldMatch("60%")), ScoreMode.Total));
//                } else {
//                    boolQueryBuilder.must(QueryBuilders.nestedQuery("sku", QueryBuilders.boolQuery().
//                            must(QueryBuilders.matchQuery(ElasticOrderKey.PRODUCT_NAME, orderEsSearchDto.getProduct_name()).operator(Operator.AND)), ScoreMode.Total));
//                }
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>支付时间查询>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            if (StringUtils.isNotBlank(orderEsSearchDto.getPay_time_begin())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.PAY_TIME_BEGIN).from(orderEsSearchDto.getPay_time_begin(), true));
            }
            if (StringUtils.isNotBlank(orderEsSearchDto.getPay_time_end())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.PAY_TIME_END).to(orderEsSearchDto.getPay_time_end(), true));
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>下单时间查询>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            if (StringUtils.isNotBlank(orderEsSearchDto.getCreate_time_begin())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.CREATE_TIME_BEGIN).from(orderEsSearchDto.getCreate_time_begin(), true));
            }
            if (StringUtils.isNotBlank(orderEsSearchDto.getCreate_time_end())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.CREATE_TIME_END).to(orderEsSearchDto.getCreate_time_end(), true));
            }
            Map<String, String[]> mapArray = queryOrderArray(orderEsSearchDto);
            if (!mapArray.isEmpty()) {
                for (String in : mapArray.keySet()) {
                    //map.keySet()返回的是所有key的值
                    String[] str2 = mapArray.get(in);//得到每个key多对用value的值
                    boolQueryBuilder.must(QueryBuilders.termsQuery(in, str2));
                }
            }

            Map<String, String> mapCondition = queryOrderCondition(orderEsSearchDto);
            if (!mapCondition.isEmpty()) {
                for (String in : mapCondition.keySet()) {
                    String str3 = mapCondition.get(in);
                    boolQueryBuilder.must(QueryBuilders.wildcardQuery(in, str3));
                }
            }
            SearchRequestBuilder setQuery = this.client.prepareSearch(ElasticOrderKey.ORDER_INDEX).setTypes(ElasticOrderKey.ORDER_TYPE)
                    .setQuery(boolQueryBuilder).addAggregation(sumOrderPrice).addAggregation(sumActualPay).addAggregation(refundPay).addAggregation(buyerNum)
                    .addAggregation(termsBuilder).addSort(SortBuilders.fieldSort(ElasticOrderKey.CREATE_TIME_BEGIN).unmappedType("date").order(SortOrder.DESC))
                    ;

            System.out.println(setQuery + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            // 设置分页
            setQuery.setFrom(orderEsSearchDto.getPage_index() * orderEsSearchDto.getPage_size());
            setQuery.setSize(orderEsSearchDto.getPage_size());
            //过滤订单状态
//            if (orderEsSearchDto.getOrder_state_key() != null) {
//                if (orderEsSearchDto.getOrder_state_key().length != 0) {
//                    setQuery.setPostFilter(boolQueryBuilder);
//                }
//            }

            SearchHits searchHits = setQuery.get().getHits();
            Iterator<SearchHit> iterator = searchHits.iterator();

            List<ResponsePushOrderToEs> list = new ArrayList<>();

            long totalHits = searchHits.getTotalHits();//总条数
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>设置分页信息>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            pageInfo.setPageSize(orderEsSearchDto.getPage_size());
            pageInfo.setPageNum(totalHits / orderEsSearchDto.getPage_size());
            pageInfo.setPageIndex(orderEsSearchDto.getPage_index());
            pageInfo.setTotal(totalHits);
            while (iterator.hasNext()) {
                SearchHit searchHit = iterator.next();
                ResponsePushOrderToEs order = JSON.parseObject(searchHit.getSourceAsString(), ResponsePushOrderToEs.class);
                list.add(order);
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>统计>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            List<ResponsePushOrderToEs> newList = optimizationData(list);
            SearchResponse response = null;
            PageList pageList1 = new PageList();
            PageList statistics = statistics(response, pageInfo, setQuery);
            if (orderEsSearchDto.getOrder_state_key() != null) {
                if (orderEsSearchDto.getOrder_state_key().length != 0) {
                    pageList1 = stateStatistics(orderEsSearchDto);
                    pageList.setStateNum(pageList1.getStateNum());
                } else {
                    pageList.setStateNum(statistics.getStateNum());
                }
            }
            pageList.setPageInfo(pageInfo);
//            pageList.setResponsePushOrderToEs(list);
            pageList.setResponsePushOrderToEs(newList);
            pageList.setStatistics(statistics.getStatistics());

        } catch (Exception e) {
            System.out.println("查询失败========================"+ e);
        } finally {
            if (client != null) {
                close(client);
            }
        }
        return pageList;
    }

    @Async
    public void close(TransportClient client) {
        this.client.close();
    }

    /**
     * 封装查询条件(数组)
     *
     * @param orderEsSearchDto
     * @return
     */
    private Map<String, String[]> queryOrderArray(OrderEsSearchDto orderEsSearchDto) {
        System.out.println("查询条件"+orderEsSearchDto);
        Map<String, String[]> map = new HashMap<>();
        //省
        if (orderEsSearchDto.getBuyer_shop_area_province() != null) {
            if (orderEsSearchDto.getBuyer_shop_area_province().length != 0) {
                map.put(ElasticOrderKey.BUYER_PROVINCE, orderEsSearchDto.getBuyer_shop_area_province());
            }
        }
        //市
        if (orderEsSearchDto.getBuyer_shop_area_city() != null) {
            if (orderEsSearchDto.getBuyer_shop_area_city().length != 0) {
                map.put(ElasticOrderKey.BUYER_CITY, orderEsSearchDto.getBuyer_shop_area_city());
            }
        }
        //订单状态
        if (orderEsSearchDto.getOrder_state_key() != null) {
            if (orderEsSearchDto.getOrder_state_key().length != 0) {
                map.put(ElasticOrderKey.ORDER_STATE_KEY,orderEsSearchDto.getOrder_state_key());
            }
        }

        return map;
    }


    private List<ResponsePushOrderToEs> optimizationData(List<ResponsePushOrderToEs> list) {
        List<ResponsePushOrderToEs> responsePushOrderToEs = new ArrayList<>();
        // 如果需要sum多个字段，可以定义 key value(object) Map<String, object> map
        for (ResponsePushOrderToEs bean : list) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            // 如果需要group by 多个字段，对应key=字段a+字段b...
            List<Sku> skuList = bean.getSku();
            for (int i = 0;i < skuList.size();i++) {
                String productName = skuList.get(i).getProduct_name();
                if (map.containsKey(productName)) {
                    map.put(productName, map.get(productName) + skuList.get(i).getBuy_count());
                } else {
                    map.put(productName,skuList.get(i).getBuy_count());
                }
            }
            List<Sku> newSkuList = new ArrayList<>();
            for (Sku sku :skuList) {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String a = entry.getKey().toString();
                    String b = sku.getProduct_name();
                    if (entry.getKey().toString().equals(sku.getProduct_name())) {
                        sku.setBuy_count(entry.getValue());
                        newSkuList.add(sku);
                    }
                }
            }
            List<String> ids = new ArrayList<>();//用来临时存储person的id
            newSkuList = newSkuList.stream().filter(// 过滤去重
                    v -> {
                        boolean flag = !ids.contains(v.getProduct_name());
                        ids.add(v.getProduct_name());
                        return flag;
                    }
            ).collect(Collectors.toList());
            bean.setSku(newSkuList);
            responsePushOrderToEs.add(bean);
        }

        return responsePushOrderToEs;
    }

    /**
     * 封装查询条件
     * @param orderEsSearchDto
     * @return
     */
    private Map<String,String> queryOrderCondition(OrderEsSearchDto orderEsSearchDto){
        System.out.println("查询条件"+orderEsSearchDto);
        Map<String,String> map = new HashMap<>();
        //------------------------------------------------------------------------------------
        //订单编码
        if (StringUtils.isNotBlank(orderEsSearchDto.getOrder_code())) {
            map.put(ElasticOrderKey.ORDER_CODE, orderEsSearchDto.getOrder_code());
        }
        //供货商
        if (StringUtils.isNotBlank(orderEsSearchDto.getSeller_shop_name())) {
            map.put(ElasticOrderKey.SELLER_ACCOUNT, "*"+orderEsSearchDto.getSeller_shop_name()+"*");
        }
        //采购商
        if (StringUtils.isNotBlank(orderEsSearchDto.getBuyer_shop_name())) {
            map.put(ElasticOrderKey.BUYER_ACCOUNT, "*"+orderEsSearchDto.getBuyer_shop_name()+"*");
        }
        return map;
    }

    /**
     * 数据统计
     * @param response
     * @param pageInfo
     * @param setQuery
     * @return
     * @throws Exception
     */
    public PageList statistics(SearchResponse response,PageInfo pageInfo,SearchRequestBuilder setQuery) throws Exception{
        PageList pageList = new PageList();
        Statistics statistics = new Statistics();
        StateNum stateNum = new StateNum();
        response=  setQuery.get();
        Sum valueCount= response.getAggregations().get("sum_price");
        Sum valueCount1 = response.getAggregations().get("actual_pay");
        Sum valueCount2 = response.getAggregations().get("refund_pay");
//        Sum valueCount3 = response.getAggregations().get("buyer_num");
        StringTerms lt= (StringTerms)response.getAggregations().get("order_state_key");
        stateNum = combination(lt);
        Map lts= response.getAggregations().getAsMap();
        Iterator<Map.Entry<String, Object>> it = lts.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            if ("buyer_num".equals(entry.getKey())) {
                String buyerNum = entry.getValue().toString();
                int indexbegin=buyerNum.indexOf("value\":");
                int indexend=buyerNum.indexOf("}}");
                String num = buyerNum.substring(indexbegin+7,indexend);
                statistics.setBuyerNum(num);
            }
            System.out.println("key"+entry.getKey()+" key:"+entry.getValue());
        }
        //采购商数量
//        double buyerNum = valueCount3.getValue();
        //总金额
        double sum = valueCount.getValue();
        //交易总金额
        double sumActualPay = valueCount1.getValue();
        //退款金额
        double refundPay = valueCount2.getValue();
        //成交额
        double successPay = sumActualPay - refundPay;
        //交易平均值
        double avg = (sum/pageInfo.getTotal());
        //总单量
        statistics.setSum(sum);
        statistics.setAvg(avg);
        statistics.setCount(pageInfo.getTotal());
        statistics.setSumActualPay(sumActualPay);
        statistics.setRefundPay(refundPay);
        statistics.setSuccessPay(successPay);
//        statistics.setBuyerNum(buyerNum);
        pageList.setStatistics(statistics);
        pageList.setStateNum(stateNum);
        return pageList;
    }


    public PageList stateStatistics(OrderEsSearchDto orderEsSearchDto) {
        PageList pageList = new PageList();
        try {
            //状态数量统计
            AggregationBuilder termsBuilder = AggregationBuilders.terms("order_state_key").field(ElasticOrderKey.ORDER_STATE_KEY);
            AggregationBuilder orderStateKey = AggregationBuilders.count("order_state_key").field(ElasticOrderKey.ORDER_STATE_KEY);
            termsBuilder.subAggregation(orderStateKey);
            //将订单状态置空
            orderEsSearchDto.setOrder_state_key(null);
            //查询条件
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (StringUtils.isNotBlank(orderEsSearchDto.getProduct_name())) {
                if (orderEsSearchDto.getParticiple() == 0) {
                    AnalyzeRequest an = new AnalyzeRequest().text(orderEsSearchDto.getProduct_name()).analyzer("ik_max_word");
                    List<AnalyzeResponse.AnalyzeToken> tokens = client.admin().indices()
                            .analyze(an)
                            .actionGet()
                            .getTokens();
                    // 循环赋值
                    List<String> searchTermList = new ArrayList<>();
                    tokens.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });
                    boolQueryBuilder.must(QueryBuilders.nestedQuery("sku", QueryBuilders.boolQuery().
                            must(QueryBuilders.matchQuery(ElasticOrderKey.PRODUCT_NAME, searchTermList).operator(Operator.OR)), ScoreMode.Total));
                } else {
                    boolQueryBuilder.must(QueryBuilders.nestedQuery("sku", QueryBuilders.boolQuery().
                            must(QueryBuilders.matchQuery(ElasticOrderKey.PRODUCT_NAME, orderEsSearchDto.getProduct_name()).operator(Operator.AND)), ScoreMode.Total));
                }
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>支付时间查询>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            if (StringUtils.isNotBlank(orderEsSearchDto.getPay_time_begin())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.PAY_TIME_BEGIN).from(orderEsSearchDto.getPay_time_begin(), true));
            }
            if (StringUtils.isNotBlank(orderEsSearchDto.getPay_time_end())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.PAY_TIME_END).to(orderEsSearchDto.getPay_time_end(), true));
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>下单时间查询>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            if (StringUtils.isNotBlank(orderEsSearchDto.getCreate_time_begin())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.CREATE_TIME_BEGIN).from(orderEsSearchDto.getCreate_time_begin(), true));
            }
            if (StringUtils.isNotBlank(orderEsSearchDto.getCreate_time_end())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticOrderKey.CREATE_TIME_END).to(orderEsSearchDto.getCreate_time_end(), true));
            }
            Map<String, String[]> mapArray = queryOrderArray(orderEsSearchDto);
            if (!mapArray.isEmpty()) {
                for (String in : mapArray.keySet()) {
                    //map.keySet()返回的是所有key的值
                    String[] str2 = mapArray.get(in);//得到每个key多对用value的值
                    boolQueryBuilder.must(QueryBuilders.termsQuery(in, str2));
                }
            }
            Map<String, String> mapCondition = queryOrderCondition(orderEsSearchDto);
            if (!mapCondition.isEmpty()) {
                for (String in : mapCondition.keySet()) {
                    String str3 = mapCondition.get(in);
                    boolQueryBuilder.must(QueryBuilders.wildcardQuery(in, str3));
                }
            }
            SearchRequestBuilder setQuery = this.client.prepareSearch(ElasticOrderKey.ORDER_INDEX).setTypes(ElasticOrderKey.ORDER_TYPE)
                    .setQuery(boolQueryBuilder).addAggregation(termsBuilder);
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>统计>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            SearchResponse response = null;
            PageList statistics = stateNum(response,setQuery);
            pageList.setStateNum(statistics.getStateNum());
        } catch (Exception e) {
            System.out.println("查询失败========================"+ e);
        }
        return pageList;
    }
    public PageList stateNum(SearchResponse response,SearchRequestBuilder setQuery) throws Exception{
        PageList pageList = new PageList();
        StateNum stateNum = new StateNum();
        response=  setQuery.get();
        StringTerms lt= (StringTerms)response.getAggregations().get("order_state_key");
        stateNum = combination(lt);
        pageList.setStateNum(stateNum);
        return pageList;
    }
    public StateNum combination(StringTerms lt) {
        StateNum stateNum = new StateNum();
        for(StringTerms.Bucket bucket : lt.getBuckets()){
            if ("finish".equals(bucket.getKeyAsString())) {
                stateNum.setFinish(bucket.getDocCount());
            }
            if ("close".equals(bucket.getKeyAsString())) {
                stateNum.setClose(bucket.getDocCount());
            }
            if ("sending".equals(bucket.getKeyAsString())) {
                stateNum.setSending(bucket.getDocCount());
            }
            if ("wait_for_pay".equals(bucket.getKeyAsString())) {
                stateNum.setWaitForPay(bucket.getDocCount());
            }
            if ("paid_for_send".equals(bucket.getKeyAsString())) {
                stateNum.setPaidForSend(bucket.getDocCount());
            }
            if ("cancel".equals(bucket.getKeyAsString())) {
                stateNum.setCancel(bucket.getDocCount());
            }
            if ("send_some".equals(bucket.getKeyAsString())) {
                stateNum.setSendSome(bucket.getDocCount());
            }
        }
        return stateNum;
    }

}
