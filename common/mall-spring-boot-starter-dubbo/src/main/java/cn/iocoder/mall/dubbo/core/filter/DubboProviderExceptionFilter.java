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
                if (exception instanceof ConstraintViolationException) {
                    exception = this.constraintViolationExceptionHandler((ConstraintViolationException) exception);
                }
                // 根据不同的方法 schema 返回结果
                // 第一种情况，则根据返回参数类型是否是 CommonResult 的情况，则将 ServiceException 转换成 CommonResult
                if (isReturnCommonResult(invocation)) {
                    // 清空异常
                    appResponse.setException(null);
                    // 设置结果
                    CommonResult exceptionResult = new CommonResult();
                    appResponse.setValue(exceptionResult);
                    // 处理非 ServiceException 业务异常，转换成 ServiceException 业务异常
                    if (!(exception instanceof ServiceException)) {
                        logger.error("[onResponse][service({}) method({}) params({}) 执行异常]",
                                invocation.getServiceName(), invocation.getServiceName(), invocation.getArguments(), exception);
                        //
                    }
                }

                // 1. 处理 ServiceException 异常的情况
                if (exception instanceof ServiceException) {
                    ServiceException serviceException = (ServiceException) exception;
                    // 则根据返回参数类型是否是 CommonResult 的情况，则将 ServiceException 转换成 CommonResult
                    if (isReturnCommonResult(invocation)) {
                        // 通用返回
                        CommonResult exceptionResult = new CommonResult();
                        exceptionResult.setCode(serviceException.getCode());
                        exceptionResult.setMessage(serviceException.getMessage());
                        appResponse.setValue(exceptionResult);
                        // 清空异常
                        appResponse.setException(null);
                    // 如果不是 CommonResult 的情况，则将 ServiceException 转换成 DubboInvokeException 避免可能存在的反序列化问题
                    } else {
                        RpcContext context = RpcContext.getContext();
                        appResponse.setException(new DubboInvokeException(exception.getMessage(), context.getLocalHost(), context.getLocalHostName()));
                    }
                // 2. 处理非 ServiceException 异常的情况
                } else {
                    RpcContext context = RpcContext.getContext();
                    appResponse.setException(new DubboInvokeException(exception.getMessage(), context.getLocalHost(), context.getLocalHostName()));

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

    private ServiceException defaultExceptionHandler() {

    }

}
