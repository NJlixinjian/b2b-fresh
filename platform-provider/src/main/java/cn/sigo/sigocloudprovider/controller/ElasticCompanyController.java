package cn.sigo.sigocloudprovider.controller;

import cn.sigo.sigocloudprovider.elastic.model.MerchantDto;
import cn.sigo.sigocloudprovider.elastic.model.MerchantEs.MerchantStatistics;
import cn.sigo.sigocloudprovider.elastic.model.MerchantEs.PushMerchantToEs;
import cn.sigo.sigocloudprovider.elastic.model.PageInfo;
import cn.sigo.sigocloudprovider.elastic.model.PageList;
import cn.sigo.sigocloudprovider.elastic.service.ElasticMerchantKey;
import cn.sigo.sigocloudprovider.elastic.service.ElasticOrderKey;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.*;

/**
 * @Auther: lxj
 * @Date: 2019/9/23 20:03
 * @Description:
 */
@RestController
public class ElasticCompanyController {

    private TransportClient client = null;

    @Value("${spring.es.ip}")
    public String SPRING_ES_IP;

    @Value("${spring.es.port}")
    public  Integer SPRING_ES_PORT;

    @Value("${spring.es.cluster.name}")
    public  String SPRING_ES_CLUSTER_NAME;

    /**
     * 查询商户列表
     *
     * @param merchantDto
     * @return
     */
    @RequestMapping(value = "/merchant/getlist", method = RequestMethod.POST)
    @ResponseBody
    public PageList searchMerchantLists(@RequestBody MerchantDto merchantDto) {
        PageList pageList = new PageList();
        PageInfo pageInfo = new PageInfo();
        try {
            //创建es客户端
            Settings settings = Settings.builder().put(ElasticOrderKey.CLUSTER_NAME, SPRING_ES_CLUSTER_NAME).build();
             client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(SPRING_ES_IP), SPRING_ES_PORT));
            //企业数量
            AggregationBuilder enterpriseNum = AggregationBuilders.cardinality("enterprise_num").field(ElasticMerchantKey.ENTITY_NAME);
            //账号数量
            AggregationBuilder accountNum = AggregationBuilders.cardinality("account_num").field(ElasticMerchantKey.ACCOUNT_NAME);

            //状态数量统计
            AggregationBuilder termsBuilder = AggregationBuilders.terms("status_key").field(ElasticMerchantKey.STATUS_KEY);
            AggregationBuilder orderStateKey = AggregationBuilders.count("status_key").field(ElasticMerchantKey.STATUS_KEY);
            termsBuilder.subAggregation(orderStateKey);
            //查询条件
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            queryMCondition(merchantDto,boolQueryBuilder);
            Map<String, String[]> mapArray = queryArray(merchantDto);
            if (!mapArray.isEmpty()) {
                for (String in : mapArray.keySet()) {
                    //map.keySet()返回的是所有key的值
                    String[] str2 = mapArray.get(in);//得到每个key多对用value的值
                    boolQueryBuilder.must(QueryBuilders.termsQuery(in, str2));
                }
            }

            Map<String, String> mapCondition = queryCondition(merchantDto);
            if (!mapCondition.isEmpty()) {
                for (String in : mapCondition.keySet()) {
                    String str3 = mapCondition.get(in);
                    boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(in, str3));
                }
            }

            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>支付时间查询>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            if (StringUtils.isNotBlank(merchantDto.getBegin_reg_time())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticMerchantKey.BEGIN_REG_TIME).from(merchantDto.getBegin_reg_time(), true));
            }
            if (StringUtils.isNotBlank(merchantDto.getEnd_reg_time())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticMerchantKey.END_REG_TIME).to(merchantDto.getEnd_reg_time(), true));
            }


            //排序
            SortBuilder sort = sorts(merchantDto);
            SearchRequestBuilder setQuery = this.client.prepareSearch(ElasticMerchantKey.INDEX_MERCHANT_SHOP).setTypes(ElasticMerchantKey.MERCHANT_TYPE)
                    .setQuery(boolQueryBuilder).addAggregation(enterpriseNum).addAggregation(accountNum).addAggregation(termsBuilder).addSort(sort);
            System.out.println(setQuery + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            // 设置分页
            setQuery.setFrom(merchantDto.getPage_index() * merchantDto.getPage_size());
            setQuery.setSize(merchantDto.getPage_size());
            SearchHits searchHits = setQuery.get().getHits();
            Iterator<SearchHit> iterator = searchHits.iterator();
            List<PushMerchantToEs> list = new ArrayList<>();
            long totalHits = searchHits.getTotalHits();//总条数

            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>设置分页信息>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            pageInfo.setPageSize(merchantDto.getPage_size());
            pageInfo.setPageNum(totalHits / merchantDto.getPage_size());
            pageInfo.setPageIndex(merchantDto.getPage_index());
            pageInfo.setTotal(totalHits);
            while (iterator.hasNext()) {
                SearchHit searchHit = iterator.next();
                PushMerchantToEs pushMerchantToEs = JSON.parseObject(searchHit.getSourceAsString(), PushMerchantToEs.class);
                System.out.println(searchHit.getSourceAsString());
                list.add(pushMerchantToEs);
            }
            MerchantStatistics ms = new MerchantStatistics();
            ms = stateStatistics(merchantDto);
            //统计数据封装
            SearchResponse response = null;
            MerchantStatistics statistics = statistics(response, pageInfo, setQuery);
            statistics.setShopNum(ms.getSubmitNum()+ms.getRefusedNum());
            statistics.setSubmitNum(ms.getSubmitNum());
//            statistics.setWaitAuditNum(ms.getWaitAuditNum());
            statistics.setRefusedNum(ms.getRefusedNum());
            pageList.setPageInfo(pageInfo);
            pageList.setPushMerchantToEs(list);
            pageList.setMerchantStatistics(statistics);
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
     * 排序
     *
     * @param merchantDto
     */
    private SortBuilder sorts( MerchantDto merchantDto) {
        SortBuilder sortBuilder = null;
        //采购
        if (merchantDto.getBuy_order_price_total() != null) {
            if (merchantDto.getBuy_order_price_total() == 1) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.BUY_ORDER_PRICE_TOTAL).order(SortOrder.ASC);
            } else if(merchantDto.getBuy_order_price_total() == 2) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.BUY_ORDER_PRICE_TOTAL).order(SortOrder.DESC);
            }
        }
        if (merchantDto.getBuy_order_count() != null) {
            if (merchantDto.getBuy_order_count() == 1) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.BUY_ORDER_COUNT).order(SortOrder.ASC);
            } else if (merchantDto.getBuy_order_count() == 2) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.BUY_ORDER_COUNT).order(SortOrder.DESC);
            }
        }

        //供货
        if (merchantDto.getSell_order_price_total() != null) {
            if (merchantDto.getSell_order_price_total() == 1) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.SELL_ORDER_PRICE_TOTAL).order(SortOrder.ASC);
            } else if (merchantDto.getSell_order_price_total() == 2) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.SELL_ORDER_PRICE_TOTAL).order(SortOrder.DESC);
            }
        }
        if (merchantDto.getSell_order_count() != null) {
            if (merchantDto.getSell_order_count() == 1) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.SELL_ORDER_COUNT).order(SortOrder.ASC);
            } else if (merchantDto.getSell_order_count() == 2) {
                sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.SELL_ORDER_COUNT).order(SortOrder.DESC);
            }
        }
        //默认时间排序
        if (sortBuilder == null) {
            sortBuilder = SortBuilders.fieldSort(ElasticMerchantKey.BEGIN_REG_TIME).order(SortOrder.DESC);
        }
        return sortBuilder;
    }

    /**
     * 封装查询条件
     * @param merchantDto
     * @return
     */
    private Map<String,String> queryCondition(MerchantDto merchantDto){
        System.out.println("查询条件"+merchantDto);
        Map<String,String> map = new HashMap<>();
        //区域经理
        if (StringUtils.isNotBlank(merchantDto.getArea_manager())) {
            map.put(ElasticMerchantKey.AREA_MANAGER, "*"+merchantDto.getArea_manager()+"*");
        }


        return map;
    }

    /**
     * 多条件匹配
     * @param merchantDto
     * @param boolQueryBuilder
     */
    private void queryMCondition(MerchantDto merchantDto,BoolQueryBuilder boolQueryBuilder){
        //查询店铺名称
        if (StringUtils.isNotBlank(merchantDto.getShop_name())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            //店铺名称
            boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.SHOP_NAME,merchantDto.getShop_name()));
//                boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.ENTITY_NAME,merchantDto.getShop_name()));
            boolQueryBuilder.must(boolQueryBuilder1);
        }

        //联系信息
        if (StringUtils.isNotBlank(merchantDto.getContact_info())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            //联系人
            boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.CONTACT_NAME,merchantDto.getContact_info()));
            //联系手机号
            boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.CONTACT_PHONE,merchantDto.getContact_info()));
            boolQueryBuilder.must(boolQueryBuilder1);
        }

        //企业信息
        if (StringUtils.isNotBlank(merchantDto.getCompany_info())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            //企业名称
            boolQueryBuilder1.should(QueryBuilders.wildcardQuery(ElasticMerchantKey.ENTITY_NAME,"*"+merchantDto.getCompany_info()+"*"));
            //营业执照
            boolQueryBuilder1.should(QueryBuilders.wildcardQuery(ElasticMerchantKey.BUSINESS_LICENSE_CODE,"*"+merchantDto.getCompany_info()+"*"));
            boolQueryBuilder.must(boolQueryBuilder1);
        }

        //登录账号
        if (StringUtils.isNotBlank(merchantDto.getLogin_info())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            //店铺编码
            boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.SHOP_CODE,merchantDto.getLogin_info()));
            //注册手机号
            boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.ACCOUNT_PHONE,merchantDto.getLogin_info()));
            boolQueryBuilder.must(boolQueryBuilder1);
        }

        //省市区
        if (merchantDto.getProvice_code() != null) {
            if (merchantDto.getProvice_code().length != 0) {
                BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
                boolQueryBuilder1.should(QueryBuilders.termsQuery(ElasticMerchantKey.PROVINCE_CODE,merchantDto.getProvice_code()));
                boolQueryBuilder1.should(QueryBuilders.termsQuery(ElasticMerchantKey.CITY_CODE,merchantDto.getCity_code()));
                boolQueryBuilder.must(boolQueryBuilder1);
            }
        }

        //业务范围
        if (StringUtils.isNotBlank(merchantDto.getBusiness_scope())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            if (!merchantDto.getBusiness_scope().contains("sell")) {
                boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.BUSINESS_SCOPE,merchantDto.getBusiness_scope()));
                boolQueryBuilder.must(boolQueryBuilder1).mustNot(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.BUSINESS_SCOPE,"sell"));
            } else {
                if (merchantDto.getBusiness_scope().length() > 5) {} else {
                    boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.BUSINESS_SCOPE,merchantDto.getBusiness_scope()));
                    boolQueryBuilder.must(boolQueryBuilder1);
                }
            }

        }
        //采销医疗许可证
        if (merchantDto.getIs_allow_contact_lens() != null) {
            if (merchantDto.getIs_allow_contact_lens().length != 0) {
                BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
                //判断是否没有采销医疗
                if (merchantDto.getIs_allow_contact_lens().length == 1) {
                    if ("nothing".equals(merchantDto.getIs_allow_contact_lens()[0])) {
                        boolQueryBuilder.mustNot(QueryBuilders.existsQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS));
                    }
                    if ("buy".equals(merchantDto.getIs_allow_contact_lens()[0])) {
                        boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,"buy"));
                        boolQueryBuilder.must(boolQueryBuilder1).mustNot(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,"sell"));
                    } else if ("sell".equals(merchantDto.getIs_allow_contact_lens()[0])){
                        boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,"sell"));
                        boolQueryBuilder.must(boolQueryBuilder1);
                    }
                }
                if (merchantDto.getIs_allow_contact_lens().length == 2) {
                    if ("nothing".equals(merchantDto.getIs_allow_contact_lens()[0]) && merchantDto.getIs_allow_contact_lens()[1].equals("buy")) {
                        boolQueryBuilder.must(boolQueryBuilder1).mustNot(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,"sell"));
                    }
                    if ("nothing".equals(merchantDto.getIs_allow_contact_lens()[0]) && "sell".equals(merchantDto.getIs_allow_contact_lens()[1])) {
//                        boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,"sell"));
                        List<String> list = new ArrayList<>();
                        list.add("sell");
                        boolQueryBuilder1.should(QueryBuilders.termsQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,list));
                        boolQueryBuilder1.should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS)));
                        boolQueryBuilder.must(boolQueryBuilder1);
                    }
                    if (merchantDto.getIs_allow_contact_lens()[0].equals("buy") && "sell".equals(merchantDto.getIs_allow_contact_lens()[1])) {
                        boolQueryBuilder.must(QueryBuilders.existsQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS));
                    }
                }
            }
        }
        //医疗状态
        if (merchantDto.getMedical_status() != null) {
            if (merchantDto.getMedical_status().length != 0) {
                BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
                //判断是否没有采销医疗
                if (merchantDto.getMedical_status().length == 1) {
                    if ("nothing".equals(merchantDto.getMedical_status()[0])) {
                        boolQueryBuilder.must(QueryBuilders.existsQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS));
                    }
                    if (!merchantDto.getMedical_status()[0].contains("wholesale")) {
                        boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,merchantDto.getMedical_status()));
//                            boolQueryBuilder.must(boolQueryBuilder1).mustNot(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,"sell"));
                    } else {
                        if (merchantDto.getMedical_status()[0].length() > 5) {} else {
                            boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,merchantDto.getMedical_status()));
                            boolQueryBuilder.must(boolQueryBuilder1);
                        }
                    }
                }
                if (merchantDto.getMedical_status().length == 2) {
                    if ("nothing".equals(merchantDto.getMedical_status()[0]) && !merchantDto.getMedical_status()[1].contains("wholesale")) {
                        boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,merchantDto.getMedical_status()));
                    }
                    if ("nothing".equals(merchantDto.getMedical_status()[0]) && merchantDto.getMedical_status()[1].length() > 5) {
                        boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,merchantDto.getMedical_status()));
                        boolQueryBuilder.must(boolQueryBuilder1);
                    }
                    if (!merchantDto.getMedical_status()[1].contains("wholesale") && merchantDto.getMedical_status()[1].length() < 5) {
                        boolQueryBuilder1.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.IS_ALLOW_CONTACT_LENS,merchantDto.getMedical_status()));
                        boolQueryBuilder.must(boolQueryBuilder1);
                    }
                }
            }
        }
    }
    /**
     * 封装查询条件(数组)
     *
     * @param merchantDto
     * @return
     */
    private Map<String, String[]> queryArray(MerchantDto merchantDto) {
        System.out.println("查询条件"+merchantDto);
        Map<String, String[]> map = new HashMap<>();
        //商户状态
        if (merchantDto.getStatus_key() != null) {
            if (merchantDto.getStatus_key().length != 0) {
                map.put(ElasticMerchantKey.STATUS_KEY,merchantDto.getStatus_key());
            }
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
    public MerchantStatistics statistics(SearchResponse response, PageInfo pageInfo, SearchRequestBuilder setQuery) throws Exception{
        PageList pageList = new PageList();
        MerchantStatistics merchantStatistics = new MerchantStatistics();
        response = setQuery.get();
        Map lts= response.getAggregations().getAsMap();
        Iterator<Map.Entry<String, Object>> it = lts.entrySet().iterator();
        StringTerms lt= (StringTerms)response.getAggregations().get("status_key");
        merchantStatistics = combination(lt);
        while(it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            if ("enterprise_num".equals(entry.getKey())) {
                String num = str(entry.getValue().toString());
                merchantStatistics.setEnterpriseNum(Integer.parseInt(num));
            }
            if ("account_num".equals(entry.getKey())) {
                String num = str(entry.getValue().toString());
                merchantStatistics.setAccountNum(Integer.parseInt(num));
            }
        }
        pageList.setMerchantStatistics(merchantStatistics);
        return merchantStatistics;
    }

    public MerchantStatistics combination(StringTerms lt) {
        MerchantStatistics merchantStatistics = new MerchantStatistics();
        for(StringTerms.Bucket bucket : lt.getBuckets()){
            if ("submit".equals(bucket.getKeyAsString())) {
                merchantStatistics.setSubmitNum(bucket.getDocCount());
            }
//            if ("waitAudit".equals(bucket.getKeyAsString())) {
//                merchantStatistics.setWaitAuditNum(bucket.getDocCount());
//            }
            if ("refused".equals(bucket.getKeyAsString())) {
                merchantStatistics.setRefusedNum(bucket.getDocCount());
            }
        }
        return merchantStatistics;
    }
    //--------------------数据处理----------------------
    public String str(String str){
        int indexbegin = str.indexOf("value\":");
        int indexend = str.indexOf("}}");
        String num = str.substring(indexbegin+7,indexend);
        return num;
    }

    //临时调用
    public MerchantStatistics stateStatistics(MerchantDto merchantDto) {
        MerchantStatistics merchantStatistics = new MerchantStatistics();
        PageInfo pageInfo = new PageInfo();
        try {
            //创建es客户端
            Settings settings = Settings.builder().put(ElasticOrderKey.CLUSTER_NAME, SPRING_ES_CLUSTER_NAME).build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(SPRING_ES_IP), SPRING_ES_PORT));
            //状态数量统计
            AggregationBuilder termsBuilder = AggregationBuilders.terms("status_key").field(ElasticMerchantKey.STATUS_KEY);
            AggregationBuilder orderStateKey = AggregationBuilders.count("status_key").field(ElasticMerchantKey.STATUS_KEY);
            termsBuilder.subAggregation(orderStateKey);
            //将订单状态置空
            merchantDto.setStatus_key(null);
            //查询条件
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (StringUtils.isNotBlank(merchantDto.getShop_name())) {
                BoolQueryBuilder boolQueryBuilder0 = QueryBuilders.boolQuery();
                boolQueryBuilder0.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.ENTITY_NAME,merchantDto.getShop_name()));
                boolQueryBuilder0.should(QueryBuilders.matchPhraseQuery(ElasticMerchantKey.SHOP_NAME,merchantDto.getShop_name()));
                boolQueryBuilder.must(boolQueryBuilder0);
            }

            Map<String, String[]> mapArray = queryArray(merchantDto);
            if (!mapArray.isEmpty()) {
                for (String in : mapArray.keySet()) {
                    String[] str2 = mapArray.get(in);//得到每个key多对用value的值
                    boolQueryBuilder.must(QueryBuilders.termsQuery(in, str2));
                }
            }
            Map<String, String> mapCondition = queryCondition(merchantDto);
            if (!mapCondition.isEmpty()) {
                for (String in : mapCondition.keySet()) {
                    String str3 = mapCondition.get(in);
                    boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(in, str3));
                }
            }
            if (StringUtils.isNotBlank(merchantDto.getBegin_reg_time())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticMerchantKey.BEGIN_REG_TIME).from(merchantDto.getBegin_reg_time(), true));
            }
            if (StringUtils.isNotBlank(merchantDto.getEnd_reg_time())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticMerchantKey.END_REG_TIME).to(merchantDto.getEnd_reg_time(), true));
            }
            SearchRequestBuilder setQuery = this.client.prepareSearch(ElasticMerchantKey.INDEX_MERCHANT_SHOP).setTypes(ElasticMerchantKey.MERCHANT_TYPE)
                    .setQuery(boolQueryBuilder).addAggregation(termsBuilder);
            SearchResponse response = null;
            merchantStatistics = statistics(response, pageInfo, setQuery);
        } catch (Exception e) {
        }
        return merchantStatistics;
    }

}
