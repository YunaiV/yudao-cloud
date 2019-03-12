package cn.iocoder.common.framework.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    public static String obtainAccess(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            return null;
        }
        int index = authorization.indexOf("Bearer ");
        if (index == -1) { // 未找到
            return null;
        }
        return authorization.substring(index + 7).trim();
    }

    public static String getIp(HttpServletRequest request) {
        // 基于 X-Forwarded-For 获得
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个 ip 才是真实 ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        // 基于 X-Real-IP 获得
        ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        // 默认方式
        return request.getRemoteAddr();
    }

}