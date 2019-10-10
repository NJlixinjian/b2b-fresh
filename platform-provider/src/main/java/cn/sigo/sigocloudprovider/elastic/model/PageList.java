package cn.sigo.sigocloudprovider.elastic.model;

import cn.sigo.sigocloudprovider.elastic.model.MerchantEs.MerchantStatistics;
import cn.sigo.sigocloudprovider.elastic.model.MerchantEs.PushMerchantToEs;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/8/26 14:07
 * @Description:
 */
public class PageList implements Serializable {

    private List<ResponsePushOrderToEs> responsePushOrderToEs;

    private List<PushMerchantToEs> pushMerchantToEs;

    public List<PushMerchantToEs> getPushMerchantToEs() {
        return pushMerchantToEs;
    }

    public void setPushMerchantToEs(List<PushMerchantToEs> pushMerchantToEs) {
        this.pushMerchantToEs = pushMerchantToEs;
    }

    private PageInfo pageInfo;

    private Statistics statistics;

    private StateNum stateNum;

    private MerchantStatistics merchantStatistics;

    public MerchantStatistics getMerchantStatistics() {
        return merchantStatistics;
    }

    public void setMerchantStatistics(MerchantStatistics merchantStatistics) {
        this.merchantStatistics = merchantStatistics;
    }

    public StateNum getStateNum() {
        return stateNum;
    }

    public void setStateNum(StateNum stateNum) {
        this.stateNum = stateNum;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public List<ResponsePushOrderToEs> getResponsePushOrderToEs() {
        return responsePushOrderToEs;
    }

    public void setResponsePushOrderToEs(List<ResponsePushOrderToEs> responsePushOrderToEs) {
        this.responsePushOrderToEs = responsePushOrderToEs;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
