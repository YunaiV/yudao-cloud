package cn.iocoder.yudao.gateway.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

/**
 * 环境 Utils
 *
 * copy from yudao-spring-boot-starter-env 的 EnvUtils 类
 *
 * @author 芋道源码
 */
public class EnvUtils {

    private static final String HEADER_TAG = "tag";

    public static final String HOST_NAME_VALUE = "${HOSTNAME}";

    public static String getTag(HttpHeaders headers) {
        String tag = headers.getFirst(HEADER_TAG);
        // 如果请求的是 "${HOSTNAME}"，则解析成对应的本地主机名
        // 目的：特殊逻辑，解决 IDEA Rest Client 不支持环境变量的读取，所以就服务器来做
        return Objects.equals(tag, HOST_NAME_VALUE) ? getHostName() : tag;
    }

    public static String getTag(ServiceInstance instance) {
        return instance.getMetadata().get(HEADER_TAG);
    }

    public static String getHostName() {
        return StrUtil.blankToDefault(NetUtil.getLocalHostName(), IdUtil.fastSimpleUUID());
    }

}
