package cn.iocoder.common.framework.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionUtil {

    public static String getMessage(Throwable th) {
        return ExceptionUtils.getMessage(th);
    }

    public static String getRootCauseMessage(Throwable th) {
        return ExceptionUtils.getRootCauseMessage(th);
    }

    public static String getStackTrace(Throwable th) {
        return ExceptionUtils.getStackTrace(th);
    }

}
