package cn.sigo.sigocloudprovider.service.impl;

import cn.sigo.sigocloudprovider.service.SmsService;
import cn.sigo.sigocloudproviderapi.model.BO.TriggerBO;
import cn.sigo.sigocloudproviderapi.model.DO.MsgDO;
import cn.sigo.sigocloudproviderapi.service.SmsFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Auther: lxj
 * @Date: 2019/9/10 11:32
 * @Description:
 */
@Service
public class SmsServiceImpl implements SmsService {


    @Autowired
    private SmsFeignApi smsFeignApi;

    @Value("${appCode}")
    private String appCode;
    @Value("${sms.sign}")
    private String sign;
    @Value("${sms.sender}")
    private String sender;

    @Override
    public MsgDO sendTrigger(String phone, String message) throws Exception {
        TriggerBO triggerBO = new TriggerBO();
        triggerBO.setPhone(phone);
        triggerBO.setMessage(message);
        triggerBO.setAppCode(appCode);
        triggerBO.setSender(sender);
        triggerBO.setSign(sign);
        return smsFeignApi.sendTrigger(triggerBO);
    }

}
