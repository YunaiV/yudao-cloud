package cn.iocoder.yudao.framework.tracer.core.util;

import io.opentracing.Span;
import io.opentracing.tag.Tags;
import org.apache.skywalking.apm.toolkit.webflux.WebFluxSkyWalkingTraceContext;
import org.slf4j.MDC;
import org.springframework.web.server.ServerWebExchange;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 链路追踪 Util
 *
 * @author 芋道源码
 */
public class TracerFrameworkUtils {
    /**
     * 链路追踪的 TraceId 不存在时，使用的默认值
     */
    private static final String TRACE_ID_DEFAULT = "N/A";

    /**
     * 将异常记录到 Span 中，参考自 com.aliyuncs.utils.TraceUtils
     *
     * @param throwable 异常
     * @param span Span
     */
    public static void onError(Throwable throwable, Span span) {
        Tags.ERROR.set(span, Boolean.TRUE);
        if (throwable != null) {
            span.log(errorLogs(throwable));
        }
    }

    private static Map<String, Object> errorLogs(Throwable throwable) {
        Map<String, Object> errorLogs = new HashMap<String, Object>(10);
        errorLogs.put("event", Tags.ERROR.getKey());
        errorLogs.put("error.object", throwable);
        errorLogs.put("error.kind", throwable.getClass().getName());
        String message = throwable.getCause() != null ? throwable.getCause().getMessage() : throwable.getMessage();
        if (message != null) {
            errorLogs.put("message", message);
        }
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        errorLogs.put("stack", sw.toString());
        return errorLogs;
    }

    /**
     * 获取 TraceId
     *
     * @param exchange ServerWebExchange
     * @return TraceId
     *
     */
    public static String getTraceId(ServerWebExchange exchange) {
        try {
            String traceId = WebFluxSkyWalkingTraceContext.traceId(exchange);
            return TRACE_ID_DEFAULT.equals(traceId) ? null : traceId;
        } catch (Throwable ignored) {
            return null;
        }
    }

    /**
     * 将 TraceId 放入 MDC，默认 key=tid
     *
     * @param exchange ServerWebExchange
     *
     */
    public static void putTidIntoMdc(ServerWebExchange exchange) {
        putTidIntoMdc(exchange, "tid");
    }

    /**
     * 将 TraceId 放入 MDC，指定 key
     *
     * @param exchange ServerWebExchange
     * @param key      MDC key
     *
     */
    public static void putTidIntoMdc(ServerWebExchange exchange, String key) {
        String traceId = getTraceId(exchange);
        if (traceId != null) {
            MDC.put(key, traceId);
        }
    }

    /**
     * 将 TraceId 放入 MDC，传入 traceId 和 key
     *
     * @param traceId TraceId
     * @param key     MDC key
     *
     */
    public static void putTidIntoMdc(String traceId, String key) {
        if (traceId != null && !TRACE_ID_DEFAULT.equals(traceId)) {
            MDC.put(key, traceId);
        }
    }

    /**
     * 从 MDC 中移除 TraceId，默认 key=tid
     *
     */
    public static void removeTidFromMdc() {
        removeTidFromMdc("tid");
    }

    /**
     * 从 MDC 中移除 TraceId，指定 key
     *
     * @param key MDC key
     *
     */
    public static void removeTidFromMdc(String key) {
        MDC.remove(key);
    }
}
