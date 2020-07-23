package cn.iocoder.mall.dubbo.core.filter;

import cn.iocoder.common.framework.exception.GlobalException;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Type;

import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static cn.iocoder.common.framework.exception.enums.GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;

@Activate(group = CommonConstants.PROVIDER) // TODO 优化点：设置下顺序
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
                // 1. 转换异常
                Throwable exception = appResponse.getException();
                // 1.1 参数校验异常
                if (exception instanceof ConstraintViolationException) {
                    exception = this.constraintViolationExceptionHandler((ConstraintViolationException) exception);
                // 1. ServiceException 业务异常，因为不会有序列化问题，所以无需处理
                } else if (exception instanceof ServiceException) {
                // 1.3 其它异常，转换成 GlobalException 全局异常，避免可能存在的反序列化问题
                } else {
                    exception = this.defaultExceptionHandler(exception, invocation);
                    assert exception != null;
                }
                // 2. 根据不同的方法 schema 返回结果
                // 2.1 如果是 ServiceException 异常，并且返回参数类型是 CommonResult 的情况，则将转换成 CommonResult 返回
                if (isReturnCommonResult(invocation) && exception instanceof ServiceException) {
                    appResponse.setException(null); // 一定要清空异常
                    appResponse.setValue(CommonResult.error((ServiceException) exception));
                // 2.2 如果是 GlobalException 全局异常，则直接抛出
                } else {
                    // TODO 优化点：尝试修改成 RpcException
                    appResponse.setException(exception);
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
    private GlobalException constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return new GlobalException(BAD_REQUEST.getCode(),
                String.format("请求参数不正确:%s", constraintViolation.getMessage()));
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    private GlobalException defaultExceptionHandler(Throwable exception, Invocation invocation) {
        logger.error("[defaultExceptionHandler][service({}) method({}) params({}) 执行异常]",
                invocation.getTargetServiceUniqueName(), invocation.getMethodName(), invocation.getArguments(), exception);
        // 如果已经是 GlobalException 全局异常，直接返回即可
        if (exception instanceof GlobalException) {
            return (GlobalException) exception;
        }
        return new GlobalException(INTERNAL_SERVER_ERROR)
                .setDetailMessage(this.buildDetailMessage(exception, invocation));
    }

    private String buildDetailMessage(Throwable exception, Invocation invocation) {
        return String.format("Service(%s) Method(%s) 发生异常(%s)",
                invocation.getTargetServiceUniqueName(), invocation.getMethodName(), ExceptionUtil.getRootCauseMessage(exception));
    }

}
