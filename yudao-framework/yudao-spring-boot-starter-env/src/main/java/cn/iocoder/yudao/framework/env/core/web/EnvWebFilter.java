package cn.iocoder.yudao.framework.env.core.web;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.env.core.context.EnvContextHolder;
import cn.iocoder.yudao.framework.env.core.util.EnvUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 环境的 {@link jakarta.servlet.Filter} 实现类
 * 当有 tag 请求头时，设置到 {@link EnvContextHolder} 的标签上下文
 *
 * @author 芋道源码
 */
public class EnvWebFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 如果没有 tag，则走默认的流程
        String tag = EnvUtils.getTag(request);
        if (StrUtil.isEmpty(tag)) {
            chain.doFilter(request, response);
            return;
        }

        // 如果有 tag，则设置到上下文
        EnvContextHolder.setTag(tag);
        try {
            chain.doFilter(request, response);
        } finally {
            EnvContextHolder.removeTag();
        }
    }

}
