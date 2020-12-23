package cn.iocoder.mall.web.core.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cors 过滤器
 *
 * 未来使用 {@link org.springframework.web.filter.CorsFilter} 替换
 */
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Max-Age", "1800");
        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        // 例如说，vue axios 请求时，会自带该逻辑的
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        // 如果是其它请求方法，则继续过滤器。
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
