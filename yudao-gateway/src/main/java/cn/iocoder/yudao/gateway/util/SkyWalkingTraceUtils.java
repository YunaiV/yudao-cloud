package cn.iocoder.yudao.gateway.util;

import cn.hutool.core.util.StrUtil;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.apache.skywalking.apm.toolkit.webflux.WebFluxSkyWalkingOperators;
import org.apache.skywalking.apm.toolkit.webflux.WebFluxSkyWalkingTraceContext;
import org.slf4j.MDC;
import org.springframework.web.server.ServerWebExchange;

import java.util.concurrent.Callable;

/**
 * SkyWalking 链路追踪工具类
 *
 * @author 芋道源码
 */
public class SkyWalkingTraceUtils {

    private static final String MDC_KEY_TID = "tid";
    private static final String TRACE_ID_DEFAULT = "N/A";

    private SkyWalkingTraceUtils() {}

    /**
     * 获得 Gateway WebFlux 请求的 SkyWalking TraceId
     *
     * @param exchange ServerWebExchange
     * @return TraceId；不存在时返回 null
     */
    public static String getTraceId(ServerWebExchange exchange) {
        try {
            String traceId = WebFluxSkyWalkingTraceContext.traceId(exchange);
            if (isTraceIdValid(traceId)) {
                return traceId;
            }
        } catch (Throwable ignored) {
        }
        try {
            return getCurrentTraceId();
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * 恢复 SkyWalking 上下文，并在 MDC 中写入 tid 后执行回调
     *
     * @param exchange ServerWebExchange
     * @param callable 回调
     * @param <T> 返回类型
     * @return 回调结果
     */
    public static <T> T callWithTraceIdInMdc(ServerWebExchange exchange, Callable<T> callable) {
        return WebFluxSkyWalkingOperators.continueTracing(exchange, () -> {
            String previousTraceId = putTraceIdIntoMdc(getTraceId(exchange));
            try {
                return callable.call();
            } finally {
                restoreTraceIdInMdc(previousTraceId);
            }
        });
    }

    /**
     * 将 SkyWalking TraceId 写入 MDC，返回原 tid 值
     *
     * @param traceId TraceId
     * @return 原 tid 值
     */
    public static String putTraceIdIntoMdc(String traceId) {
        String previousTraceId = MDC.get(MDC_KEY_TID);
        if (traceId != null) {
            MDC.put(MDC_KEY_TID, traceId);
        } else {
            MDC.remove(MDC_KEY_TID);
        }
        return previousTraceId;
    }

    /**
     * 恢复 MDC 中的 tid 值
     *
     * @param previousTraceId 原 tid 值
     */
    public static void restoreTraceIdInMdc(String previousTraceId) {
        if (previousTraceId == null) {
            MDC.remove(MDC_KEY_TID);
            return;
        }
        MDC.put(MDC_KEY_TID, previousTraceId);
    }

    private static boolean isTraceIdValid(String traceId) {
        return StrUtil.isNotBlank(traceId) && !TRACE_ID_DEFAULT.equals(traceId);
    }

    private static String getCurrentTraceId() {
        String traceId = TraceContext.traceId();
        return isTraceIdValid(traceId) ? traceId : null;
    }

}
