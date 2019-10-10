package cn.sigo.sigocloudprovider.service;

import cn.sigo.sigocloudproviderapi.model.DO.MsgDO;

/**
 * @Auther: lxj
 * @Date: 2019/9/10 14:16
 * @Description:
 */
public interface SmsService {

    /**
     * 给指定手机号发送短信
     *
     * @param phone   手机号
     * @param message 发送内容
     * @return
     * @throws Exception
     */
    MsgDO sendTrigger(String phone, String message) throws Exception;
}
