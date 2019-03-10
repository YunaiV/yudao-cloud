package cn.iocoder.common.framework.util;

/**
 * 校验工具类
 */
public class ValidationUtil {

    public static boolean isMobile(String mobile) {
        if (mobile == null || mobile.length() != 11) {
            return false;
        }
        // TODO 芋艿，后面完善手机校验
        return true;
    }

}