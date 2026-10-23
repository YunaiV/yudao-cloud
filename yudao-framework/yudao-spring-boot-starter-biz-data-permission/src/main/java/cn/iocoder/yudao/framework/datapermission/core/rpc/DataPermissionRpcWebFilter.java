package cn.iocoder.yudao.framework.datapermission.core.rpc;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.RpcConstants;
import cn.iocoder.yudao.framework.datapermission.core.aop.DataPermissionContextHolder;
import cn.iocoder.yudao.framework.datapermission.core.util.DataPermissionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 针对 {@link DataPermissionRequestInterceptor} 的 RPC 调用，设置 {@link DataPermissionContextHolder} 的上下文
 *
 * 注意：只有 RPC 请求（/rpc-api/**）才处理，避免 /admin-api、/app-api 等外部请求伪造 Header 忽略数据权限
 *
 * @author 芋道源码
 */
public class DataPermissionRpcWebFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 和 Spring MVC 的路由匹配保持一致：使用 requestURI 去除 contextPath 后的路径
        String path = StrUtil.removePrefix(request.getRequestURI(), request.getContextPath());
        return !StrUtil.startWith(path, RpcConstants.RPC_API_PREFIX + StrUtil.SLASH);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String enable = request.getHeader(DataPermissionRequestInterceptor.ENABLE_HEADER_NAME);
        if (Objects.equals(enable, Boolean.FALSE.toString())) {
            DataPermissionUtils.executeIgnore(() -> {
                try {
                    chain.doFilter(request, response);
                } catch (IOException | ServletException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            chain.doFilter(request, response);
        }
    }

}
