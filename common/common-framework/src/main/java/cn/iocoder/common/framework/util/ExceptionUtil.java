package cn.iocoder.common.framework.util;

import cn.iocoder.common.framework.exception.ServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

public class ExceptionUtil {

    public static ServiceException getServiceException(Exception e) {
        if (e instanceof UndeclaredThrowableException) {
            return getServiceException((UndeclaredThrowableException) e);
        }
        return null;
    }

    // 处理 Spring 动态代理调用时，发生 UndeclaredThrowableException 的情况。
    // 不了解的胖友，可以先看看 https://segmentfault.com/a/1190000012262244 文章
    // 原因是：
    //   1. Dubbo 动态代理 Wrapper 会将抛出的异常，包装成 InvocationTargetException 异常
    //   2. Spring AOP 发现是 InvocationTargetException 异常是非方法定义的异常，则会包装成 UndeclaredThrowableException 异常。
    @Deprecated // https://github.com/apache/incubator-dubbo/issues/3386 Dubbo 2.6.5 会触发该问题，在 2.7.1 版本已经解决。
    public static ServiceException getServiceException(UndeclaredThrowableException e) {
        Throwable undeclaredThrowable = e.getUndeclaredThrowable();
        if (undeclaredThrowable instanceof InvocationTargetException) {
            InvocationTargetException invocationTargetException = (InvocationTargetException) undeclaredThrowable;
            Throwable targetException = invocationTargetException.getTargetException();
            if (targetException != null & targetException instanceof ServiceException) {
                return (ServiceException) targetException;
            }
        }
        return null;
    }

    @Deprecated // https://github.com/apache/incubator-dubbo/issues/3386 Dubbo 2.6.5 会触发该问题，在 2.7.1 版本已经解决。
    public static ConstraintViolationException getConstraintViolationException(UndeclaredThrowableException e) {
        Throwable undeclaredThrowable = e.getUndeclaredThrowable();
        if (undeclaredThrowable instanceof InvocationTargetException) {
            InvocationTargetException invocationTargetException = (InvocationTargetException) undeclaredThrowable;
            Throwable targetException = invocationTargetException.getTargetException();
            if (targetException instanceof ConstraintViolationException) {
                return (ConstraintViolationException) targetException;
            }
        }
        return null;
    }

    public static String getRootCauseMessage(Throwable th) {
        return ExceptionUtils.getRootCauseMessage(th);
    }

}
