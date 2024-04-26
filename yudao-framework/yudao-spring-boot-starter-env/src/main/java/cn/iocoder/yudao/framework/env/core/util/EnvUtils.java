package cn.iocoder.yudao.framework.env.core.util;

import cn.iocoder.yudao.framework.env.config.EnvProperties;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Objects;

/**
 * 环境 Utils
 *
 * @author 芋道源码
 */
public class EnvUtils {

    private static final String HEADER_TAG = "tag";

    public static final String HOST_NAME_VALUE = "${HOSTNAME}";

    public static String getTag(HttpServletRequest request) {
        String tag = request.getHeader(HEADER_TAG);
        // 如果请求的是 "${HOSTNAME}"，则解析成对应的本地主机名
        // 目的：特殊逻辑，解决 IDEA Rest Client 不支持环境变量的读取，所以就服务器来做
        return Objects.equals(tag, HOST_NAME_VALUE) ? getHostName() : tag;
    }

    public static String getTag(ServiceInstance instance) {
        return instance.getMetadata().get(HEADER_TAG);
    }

    public static String getTag(Environment environment) {
        String tag = environment.getProperty(EnvProperties.TAG_KEY);
        // 如果请求的是 "${HOSTNAME}"，则解析成对应的本地主机名
        // 目的：特殊逻辑，解决 IDEA Rest Client 不支持环境变量的读取，所以就服务器来做
        return Objects.equals(tag, HOST_NAME_VALUE) ? getHostName() : tag;
    }

    public static void setTag(RequestTemplate requestTemplate, String tag) {
        requestTemplate.header(HEADER_TAG, tag);
    }

    /**
     * 获得 hostname 主机名
     *
     * @return 主机名
     */
    @SneakyThrows
    public static String getHostName() {
        return InetAddress.getLocalHost().getHostName();
    }

}
