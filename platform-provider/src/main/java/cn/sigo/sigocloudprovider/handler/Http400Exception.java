package cn.sigo.sigocloudprovider.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 功能描述: 自定义400消息异常
 *
 * @author wangcong
 * @date 2018/12/15 21:15
 */
public class Http400Exception extends RuntimeException {
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private String errorCode;

    public Http400Exception(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }
}
