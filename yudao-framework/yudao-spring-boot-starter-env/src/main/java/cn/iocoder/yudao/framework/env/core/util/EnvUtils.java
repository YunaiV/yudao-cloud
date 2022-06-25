package cn.iocoder.yudao.framework.env.core.util;

import cn.hutool.core.net.NetUtil;
import feign.RequestTemplate;
import org.springframework.cloud.client.ServiceInstance;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 环境 Utils
 *
 * @author 芋道源码
 */
public class EnvUtils {

    private static final String HEADER_TAG = "tag";

    private static final String HOST_NAME_VALUE = "${HOSTNAME}";

    public static String getTag(HttpServletRequest request) {
        String tag = request.getHeader(HEADER_TAG);
        // 如果请求的是 "${HOSTNAME}"，则解析成对应的本地主机名
        // 目的：特殊逻辑，解决 IDEA Rest Client 不支持环境变量的读取，所以就服务器来做
        return Objects.equals(tag, HOST_NAME_VALUE) ? NetUtil.getLocalHostName() : tag;
    }

    public static String getTag(ServiceInstance instance) {
        return instance.getMetadata().get(HEADER_TAG);
    }

    public static void setTag(RequestTemplate requestTemplate, String tag) {
        requestTemplate.header(HEADER_TAG, tag);
    }

}
