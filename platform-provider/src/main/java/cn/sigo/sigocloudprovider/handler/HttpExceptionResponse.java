package cn.sigo.sigocloudprovider.handler;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 功能描述: 自定义异常消息返回
 *
 * @author wangcong
 * @date 2018/12/15 21:24
 */
public class HttpExceptionResponse {
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @JSONField(name = "error_code",serialize = true)
    private String errorCode;
    @JSONField(name = "error_msg",serialize = true)
    private String errorMsg;

    public HttpExceptionResponse(String errorCode) {
        this.errorCode = errorCode;
    }
    public HttpExceptionResponse(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static HttpExceptionResponse create(String errorCode, String errorMsg) {
        return new HttpExceptionResponse(errorCode, errorMsg);
    }
}
