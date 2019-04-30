package cn.iocoder.mall.user.sdk.interceptor;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.mall.user.api.UserAccessLogService;
import cn.iocoder.mall.user.api.dto.UserAccessLogAddDTO;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 访问日志拦截器
 */
@Component
public class UserAccessLogInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 开始时间
     */
    private static final ThreadLocal<Date> START_TIME = new ThreadLocal<>();
    /**
     * 管理员编号
     */
    private static final ThreadLocal<Integer> USER_ID = new ThreadLocal<>();

    @Reference
    @Autowired(required = false)
    private UserAccessLogService userAccessLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // TODO 芋艿，临时拿来处理 vue axios options 请求的问题。
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {

            return false; // 通过这样的方式，让前端知道允许的 header 等等。
        }
        // 记录当前时间
        START_TIME.set(new Date());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserAccessLogAddDTO accessLog = new UserAccessLogAddDTO();
        try {
            accessLog.setUserId(USER_ID.get());
            if (accessLog.getUserId() == null) {
                accessLog.setUserId(UserAccessLogAddDTO.USER_ID_NULL);
            }
            accessLog.setUri(request.getRequestURI()); // TODO 提升：如果想要优化，可以使用 Swagger 的 @ApiOperation 注解。
            accessLog.setQueryString(HttpUtil.buildQueryString(request));
            accessLog.setMethod(request.getMethod());
            accessLog.setUserAgent(HttpUtil.getUserAgent(request));
            accessLog.setIp(HttpUtil.getIp(request));
            accessLog.setStartTime(START_TIME.get());
            accessLog.setResponseTime((int) (System.currentTimeMillis() - accessLog.getStartTime().getTime()));// 默认响应时间设为0
            userAccessLogService.addUserAccessLog(accessLog);
            // TODO 提升：暂时不考虑 ELK 的方案。而是基于 MySQL 存储。如果访问日志比较多，需要定期归档。
        } catch (Throwable th) {
            logger.error("[afterCompletion][插入管理员访问日志({}) 发生异常({})", JSON.toJSONString(accessLog), ExceptionUtils.getRootCauseMessage(th));
        } finally {
            clear();
        }
    }

    public static void setUserId(Integer userId) {
        USER_ID.set(userId);
    }

    public static void clear() {
        START_TIME.remove();
        USER_ID.remove();
    }

}
