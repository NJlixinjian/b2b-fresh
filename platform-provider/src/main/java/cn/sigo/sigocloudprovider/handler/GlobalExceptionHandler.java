package cn.sigo.sigocloudprovider.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 功能描述: 全局异常处理
 *
 * @author wangcong
 * @date 2018/12/15 21:42
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String applicationName;

    @ResponseBody
    @ExceptionHandler(value = Http400Exception.class)
    public HttpExceptionResponse handlerException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (ex instanceof Http400Exception) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            Http400Exception http400Exception = (Http400Exception) ex;
            HttpExceptionResponse responseException = HttpExceptionResponse.create(http400Exception.getErrorCode(), http400Exception.getMessage());
            return responseException;
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error(applicationName + "-globalexceptionhandler：（" + "请求url：" + getFullURL(request) + "，  请求内容：" + getRequestBody(request) + " ， 异常内容：" + ex.toString() + "）");
            return HttpExceptionResponse.create(Convert.toStr(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
        }
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public HttpExceptionResponse requestHandlerException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (ex instanceof HttpMessageNotReadableException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            HttpExceptionResponse responseException = HttpExceptionResponse.create("paramerror", "参数错误");
            return responseException;
        } else if (ex instanceof FeignException) {
            log.error(applicationName + "-globalexceptionhandler：（" + "请求url：" + getFullURL(request) + "，  请求内容：" + getRequestBody(request) + " ， 异常内容：" + ex.toString() + "）");
            FeignException ex_400 = (FeignException) ex;
            if (ex_400.status() == 400) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                String exmsg = ex_400.getMessage();
                String error_msg = exmsg.substring(exmsg.lastIndexOf("content:") + 9);
                HttpExceptionResponse http400Exception = JSON.parseObject(error_msg, HttpExceptionResponse.class);
                return http400Exception;
            }
            else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return HttpExceptionResponse.create(Convert.toStr("servererror"), "出现错误，请稍后再试");
            }
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error(applicationName + "-globalexceptionhandler：（" + "请求url：" + getFullURL(request) + "，  请求内容：" + getRequestBody(request) + " ， 异常内容：" + ex.toString() + "）");
            return HttpExceptionResponse.create(Convert.toStr("servererror"), "出现错误，请稍后再试");
        }
    }

    /**
     * 读取request请求的内容
     *
     * @param request
     * @throws IOException
     */
    private String getRequestBody(HttpServletRequest request) {
        try {
            BufferedReader br = request.getReader();
            String str, wholeStr = "";
            while ((str = br.readLine()) != null) {
                wholeStr += str;
            }
            return wholeStr;
        } catch (Exception ex) {
            return StrUtil.EMPTY;
        }
    }

    /**
     * 获取请求URL
     *
     * @param request
     * @return
     */
    private String getFullURL(HttpServletRequest request) {
        try {
            StringBuffer url = request.getRequestURL();
            if (request.getQueryString() != null) {
                url.append('?');
                url.append(request.getQueryString());
            }
            return url.toString();
        } catch (Exception ex) {
            return StrUtil.EMPTY;
        }
    }
}
