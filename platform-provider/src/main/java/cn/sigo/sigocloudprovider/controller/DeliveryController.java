package cn.sigo.sigocloudprovider.controller;

import cn.sigo.sigocloudprovider.feign.DeliveryClient;
import cn.sigo.sigocloudprovider.feign.TokenClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryClient deliveryClient;

    @Autowired
    private TokenClient tokenClient;

    /**
     * 获取物流信息
     *
     * @param expressCode
     * @return
     */
    @RequestMapping(value = "/erp/info", method = RequestMethod.GET)
    public JSONObject getOrderDeliveryInfo(@PathParam("expressCode") String expressCode) {
        String token = getRemoteErpToken();

        Map<String, String> map = Maps.newHashMap();
        map.put("requestEntity", "{  \"appkey\" : \"sigo\",\"AccessToken\" : \"" +
                token +
                "\", \"action\" : \"getlogisticsrecord\",\"data\":{\"WaybillCode\" : \"" +
                expressCode +
                "\"}}");
        StringBuffer v = new StringBuffer();
        for (Map.Entry<String, String> entries : map.entrySet()) {
            v.append(entries.getKey()).append("=").append(entries.getValue()).append("&");
        }
        String result = deliveryClient.getDelivery(v.substring(0, v.length() - 1));
        JSONObject jsonObj = JSON.parseObject(result);
        int error_code = (int) jsonObj.get("error_code");
        if (error_code==0){
            jsonObj.put("error_code",200);
        }else {
            jsonObj.put("error_code",400);
            jsonObj.put("error_message","快递信息查询失败");
        }
        return jsonObj;
    }

    private String getRemoteErpToken() {
        Map<String, String> map = Maps.newHashMap();
        map.put("requestEntity", "{\"AppKey\":\"sigo\",\"AppSecret\":\"sigo\",\"Action\":\"gettoken\",\"date\":\"\"}");
        StringBuffer v = new StringBuffer();
        for (Map.Entry<String, String> entries : map.entrySet()) {
            v.append(entries.getKey()).append("=").append(entries.getValue()).append("&");
        }
        String result = tokenClient.getToken(v.substring(0, v.length() - 1));
        JSONObject jsonObj = JSON.parseObject(result);
        return (String) jsonObj.get("data");
    }
}
