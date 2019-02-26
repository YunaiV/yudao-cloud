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

}