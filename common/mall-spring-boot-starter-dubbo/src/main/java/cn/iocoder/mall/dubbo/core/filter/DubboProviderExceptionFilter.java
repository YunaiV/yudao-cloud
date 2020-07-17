package cn.iocoder.mall.dubbo.core.filter;

import cn.iocoder.common.framework.enums.GlobalErrorCodeEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Type;

public class DubboProviderExceptionFilter implements Filter, Filter.Listener {

    private Logger logger = LoggerFactory.getLogger(DubboProviderExceptionFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                // 转换异常
                Throwable exception = appResponse.getException();
                // 1. 参数校验异常
                if (exception instanceof ConstraintViolationException) {
                    exception = this.constraintViolationExceptionHandler((ConstraintViolationException) exception);
                // 2. 非 ServiceException 业务异常，转换成 ServiceException 业务异常，避免可能存在的反序列化问题
                } if (!(exception instanceof ServiceException)) {
                    exception = this.defaultExceptionHandler(exception, invocation);
                }
                assert exception != null;
                ServiceException serviceException = (ServiceException) exception;
                // 根据不同的方法 schema 返回结果
                // 第一种情况，返回参数类型是 CommonResult 的情况，则将 ServiceException 转换成 CommonResult
                if (isReturnCommonResult(invocation)) {
                    // 清空异常
                    appResponse.setException(null);
                    // 设置结果
                    CommonResult exceptionResult = new CommonResult();
                    exceptionResult.setCode(serviceException.getCode());
                    exceptionResult.setMessage(serviceException.getMessage());
                    exceptionResult.setDetailMessage(serviceException.getDetailMessage());
                    appResponse.setValue(exceptionResult);
                // 第二种情况，未包装成 CommonResult 的情况，则直接抛出 ServiceException 异常
                } else {
                    appResponse.setException(serviceException);
                }
            } catch (Throwable e) {
                logger.warn("Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
        logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
    }

    private boolean isReturnCommonResult(Invocation invocation) {
        if (!(invocation instanceof RpcInvocation)) {
            return false;
        }
        RpcInvocation rpcInvocation = (RpcInvocation) invocation;
        Type[] returnTypes = rpcInvocation.getReturnTypes();
        if (returnTypes.length == 0) {
            return false;
        }
        Type returnType = returnTypes[0];
        if (!(returnType instanceof Class)) {
            return false;
        }
        Class returnClass = (Class) returnType;
        return returnClass == CommonResult.class;
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    private ServiceException constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return ServiceExceptionUtil.exception0(GlobalErrorCodeEnum.BAD_REQUEST.getCode(),
                String.format("请求参数不正确:%s", constraintViolation.getMessage()));
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    private ServiceException defaultExceptionHandler(Throwable exception, Invocation invocation) {
        logger.error("[defaultExceptionHandler][service({}) method({}) params({}) 执行异常]",
                invocation.getServiceName(), invocation.getServiceName(), invocation.getArguments(), exception);
        return ServiceExceptionUtil.exception0(GlobalErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(),
                GlobalErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage())
                .setDetailMessage(this.buildDetailMessage(exception, invocation));
    }

    private String buildDetailMessage(Throwable exception, Invocation invocation) {
        return String.format("Service(%s) Method(%s) 发生异常(%s)",
                invocation.getServiceName(), invocation.getMethodName(), ExceptionUtil.getRootCauseMessage(exception));
    }

}
