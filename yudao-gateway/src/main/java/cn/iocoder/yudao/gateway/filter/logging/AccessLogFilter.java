package cn.iocoder.yudao.gateway.filter.logging;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.util.collection.SetUtils;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.gateway.util.SkyWalkingTraceUtils;
import cn.iocoder.yudao.gateway.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.gateway.util.WebFrameworkUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.CodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_FORMATTER;

/**
 * 网关的访问日志过滤器
 *
 * 从功能上，它类似 yudao-spring-boot-starter-web 的 ApiAccessLogFilter 过滤器
 *
 * TODO 芋艿：如果网关执行异常，不会记录访问日志，后续研究下 https://github.com/Silvmike/webflux-demo/blob/master/tests/src/test/java/ru/hardcoders/demo/webflux/web_handler/filters/logging
 *
 * @author 芋道源码
 */
@Slf4j
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {

    /**
     * UReport2 报表的 URL 前缀
     *
     * 它的请求通过 URL 参数传递 token，请求、响应体可能包含报表模板（例如说数据源的连接密码），所以日志需要脱敏
     */
    private static final String UREPORT_URL_PREFIX = "/ureport";
    /**
     * UReport2 报表请求中，需要脱敏的 URL 参数、请求头、响应头，忽略大小写
     */
    private static final Set<String> UREPORT_SENSITIVE_KEYS = SetUtils.asSet("token", "accesstoken", "refreshtoken",
            "authorization", "proxy-authorization", "cookie", "set-cookie");
    private static final String SENSITIVE_VALUE = "******";
    private static final String IGNORED_BODY = "[UReport2 报表请求，不记录内容]";

    @Resource
    private CodecConfigurer codecConfigurer;

    /**
     * 打印日志
     *
     * @param gatewayLog 网关日志
     */
    private void writeAccessLog(ServerWebExchange exchange, AccessLog gatewayLog) {
        SkyWalkingTraceUtils.callWithTraceIdInMdc(exchange, () -> {
            gatewayLog.setTraceId(SkyWalkingTraceUtils.getTraceId(exchange));
            // 方式一：打印 Logger 后，通过 ELK 进行收集
            // log.info("[writeAccessLog][日志内容：{}]", JsonUtils.toJsonString(gatewayLog));

            // 方式二：调用远程服务，记录到数据库中
            // TODO 芋艿：暂未实现

            // 方式三：打印到控制台，方便排查错误
            try {
                // UReport2 报表的请求，只在日志中脱敏凭据、忽略请求和响应体，不影响实际的请求
                boolean ureport = isUReportRequest(gatewayLog.getRequestUrl());
                Map<String, Object> values = MapUtil.newHashMap(15, true); // 手工拼接，保证排序；15 保证不用扩容
                values.put("traceId", gatewayLog.getTraceId());
                values.put("userId", gatewayLog.getUserId());
                values.put("userType", gatewayLog.getUserType());
                values.put("routeId", gatewayLog.getRoute() != null ? gatewayLog.getRoute().getId() : null);
                values.put("schema", gatewayLog.getSchema());
                values.put("requestUrl", gatewayLog.getRequestUrl());
                values.put("queryParams", maskIfUReport(ureport, gatewayLog.getQueryParams()).toSingleValueMap());
                values.put("requestBody", ureport ? ignoreBody(gatewayLog.getRequestBody()) :
                        JsonUtils.isJson(gatewayLog.getRequestBody()) ? // 保证 body 的展示好看
                        JSONUtil.parse(gatewayLog.getRequestBody()) : gatewayLog.getRequestBody());
                values.put("requestHeaders", JsonUtils.toJsonString(maskIfUReport(ureport, gatewayLog.getRequestHeaders()).toSingleValueMap()));
                values.put("userIp", gatewayLog.getUserIp());
                values.put("responseBody", ureport ? ignoreBody(gatewayLog.getResponseBody()) :
                        JsonUtils.isJson(gatewayLog.getResponseBody()) ? // 保证 body 的展示好看
                        JSONUtil.parse(gatewayLog.getResponseBody()) : gatewayLog.getResponseBody());
                values.put("responseHeaders", gatewayLog.getResponseHeaders() != null ?
                        JsonUtils.toJsonString(maskIfUReport(ureport, gatewayLog.getResponseHeaders()).toSingleValueMap()) : null);
                values.put("httpStatus", gatewayLog.getHttpStatus());
                values.put("startTime", LocalDateTimeUtil.format(gatewayLog.getStartTime(), NORM_DATETIME_MS_FORMATTER));
                values.put("endTime", LocalDateTimeUtil.format(gatewayLog.getEndTime(), NORM_DATETIME_MS_FORMATTER));
                values.put("duration", gatewayLog.getDuration() != null ? gatewayLog.getDuration() + " ms" : null);
                log.info("[writeAccessLog][网关日志：{}]", JsonUtils.toJsonPrettyString(values));
            } catch (Exception e) {
                // 兜底处理，参见 https://gitee.com/zhijiantianya/yudao-cloud/issues/IC9A70
                log.error("[writeAccessLog][打印网关日志时，发生异常]", e);
            }
            return null;
        });
    }

    /**
     * 判断是否为 UReport2 报表的请求
     *
     * 先 URL 解码，避免编码后的路径绕过；以 {@link #UREPORT_URL_PREFIX} 开头的请求都脱敏，宁可多脱敏
     *
     * @param requestUrl 请求地址，未解码
     * @return 是否为 UReport2 报表的请求
     */
    private static boolean isUReportRequest(String requestUrl) {
        return StrUtil.startWith(URLUtil.decode(requestUrl), UREPORT_URL_PREFIX);
    }

    /**
     * 如果是 UReport2 报表的请求，返回脱敏后的副本；否则，返回原值
     *
     * @param ureport 是否为 UReport2 报表的请求
     * @param values URL 参数、请求头或响应头
     * @return 用于日志的值
     */
    private static MultiValueMap<String, String> maskIfUReport(boolean ureport, MultiValueMap<String, String> values) {
        if (!ureport) {
            return values;
        }
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        values.forEach((key, value) -> result.put(key,
                UREPORT_SENSITIVE_KEYS.contains(StrUtil.nullToEmpty(key).toLowerCase())
                        ? Collections.singletonList(SENSITIVE_VALUE) : value));
        return result;
    }

    private static String ignoreBody(String body) {
        return StrUtil.isEmpty(body) ? body : IGNORED_BODY;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 将 Request 中可以直接获取到的参数，设置到网关日志
        ServerHttpRequest request = exchange.getRequest();
        AccessLog gatewayLog = new AccessLog();
        gatewayLog.setRoute(WebFrameworkUtils.getGatewayRoute(exchange));
        gatewayLog.setSchema(request.getURI().getScheme());
        gatewayLog.setRequestMethod(request.getMethodValue());
        gatewayLog.setRequestUrl(request.getURI().getRawPath());
        gatewayLog.setQueryParams(request.getQueryParams());
        gatewayLog.setRequestHeaders(request.getHeaders());
        gatewayLog.setStartTime(LocalDateTime.now());
        gatewayLog.setUserIp(WebFrameworkUtils.getClientIP(exchange));

        // 继续 filter 过滤
        MediaType mediaType = request.getHeaders().getContentType();
        if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)
                || MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) { // 适合 JSON 和 Form 提交的请求
            return filterWithRequestBody(exchange, chain, gatewayLog);
        }
        return filterWithoutRequestBody(exchange, chain, gatewayLog);
    }

    private Mono<Void> filterWithoutRequestBody(ServerWebExchange exchange, GatewayFilterChain chain, AccessLog accessLog) {
        // 包装 Response，用于记录 Response Body
        ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, accessLog);
        return chain.filter(exchange.mutate().response(decoratedResponse).build())
                .then(Mono.fromRunnable(() -> writeAccessLog(exchange, accessLog))); // 打印日志
    }

    /**
     * 参考 {@link ModifyRequestBodyGatewayFilterFactory} 实现
     *
     * 差别主要在于使用 modifiedBody 来读取 Request Body 数据
     */
    private Mono<Void> filterWithRequestBody(ServerWebExchange exchange, GatewayFilterChain chain, AccessLog gatewayLog) {
        // 设置 Request Body 读取时，设置到网关日志
        // 此处 codecConfigurer.getReaders() 的目的，是解决 spring.codec.max-in-memory-size 不生效
        ServerRequest serverRequest = ServerRequest.create(exchange, codecConfigurer.getReaders());
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
            gatewayLog.setRequestBody(body);
            return Mono.just(body);
        });

        // 创建 BodyInserter 对象
        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        // 创建 CachedBodyOutputMessage 对象
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        // the new content type will be computed by bodyInserter
        // and then set in the request decorator
        headers.remove(HttpHeaders.CONTENT_LENGTH); // 移除
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        // 通过 BodyInserter 将 Request Body 写入到 CachedBodyOutputMessage 中
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            // 包装 Request，用于缓存 Request Body
            ServerHttpRequest decoratedRequest = requestDecorate(exchange, headers, outputMessage);
            // 包装 Response，用于记录 Response Body
            ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, gatewayLog);
            // 记录普通的
            return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build())
                    .then(Mono.fromRunnable(() -> writeAccessLog(exchange, gatewayLog))); // 打印日志

        }));
    }

    /**
     * 记录响应日志
     * 通过 DataBufferFactory 解决响应体分段传输问题。
     */
    private ServerHttpResponseDecorator recordResponseLog(ServerWebExchange exchange, AccessLog gatewayLog) {
        ServerHttpResponse response = exchange.getResponse();
        return new ServerHttpResponseDecorator(response) {

            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    DataBufferFactory bufferFactory = response.bufferFactory();
                    // 计算执行时间
                    gatewayLog.setEndTime(LocalDateTime.now());
                    gatewayLog.setDuration((int) (LocalDateTimeUtil.between(gatewayLog.getStartTime(),
                            gatewayLog.getEndTime()).toMillis()));
                    // 设置其它字段
                    gatewayLog.setUserId(SecurityFrameworkUtils.getLoginUserId(exchange));
                    gatewayLog.setUserType(SecurityFrameworkUtils.getLoginUserType(exchange));
                    gatewayLog.setResponseHeaders(response.getHeaders());
                    gatewayLog.setHttpStatus(response.getStatusCode());

                    // 获取响应类型，如果是 json 就打印
                    String originalResponseContentType = exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                    if (StringUtils.isNotBlank(originalResponseContentType)
                            && originalResponseContentType.contains("application/json")) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            // 设置 response body 到网关日志
                            byte[] content = readContent(dataBuffers);
                            String responseResult = new String(content, StandardCharsets.UTF_8);
                            gatewayLog.setResponseBody(responseResult);

                            // 响应
                            return bufferFactory.wrap(content);
                        }));
                    }
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
    }

    // ========== 参考 ModifyRequestBodyGatewayFilterFactory 中的方法 ==========

    /**
     * 请求装饰器，支持重新计算 headers、body 缓存
     *
     * @param exchange 请求
     * @param headers 请求头
     * @param outputMessage body 缓存
     * @return 请求装饰器
     */
    private ServerHttpRequestDecorator requestDecorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {

            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    // TODO: this causes a 'HTTP/1.1 411 Length Required' // on
                    // httpbin.org
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    // ========== 参考 ModifyResponseBodyGatewayFilterFactory 中的方法 ==========

    private byte[] readContent(List<? extends DataBuffer> dataBuffers) {
        // 合并多个流集合，解决返回体分段传输
        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
        DataBuffer join = dataBufferFactory.join(dataBuffers);
        byte[] content = new byte[join.readableByteCount()];
        join.read(content);
        // 释放掉内存
        DataBufferUtils.release(join);
        return content;
    }

}
