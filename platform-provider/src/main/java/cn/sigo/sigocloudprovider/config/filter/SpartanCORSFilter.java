package cn.sigo.sigocloudprovider.config.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/9/6 15:45
 * @Description:
 */
@Component
public class SpartanCORSFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public static List<String> allowOrigins ;

    static
    {
        allowOrigins = new ArrayList<>();
        allowOrigins.add("http://localhost:63342");
        allowOrigins.add("http://localhost:63343");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String origin = request.getHeader("Origin");

//        if(origin != null && allowOrigins.contains(origin))
//        {
//            response.setHeader("Access-Control-Allow-Origin", origin);
//        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, XRequestedWith, Content-Type, LastModified, Is-Client, Form-Token, X-Requested-With, Access-Control-Allow-Origin");
        response.setHeader("Access-Control-Expose-Headers", "Clear-Form-Token, Form-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("P3P", "CP=CAO PSA OUR");

        if("OPTIONS".equalsIgnoreCase(((HttpServletRequest)servletRequest).getMethod())) {
            // 如果请求方式为options 直接返回204 ，不返回数据
            ((HttpServletResponse)servletResponse).setStatus(204);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {}
}
