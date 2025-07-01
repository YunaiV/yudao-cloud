package cn.iocoder.yudao.framework.sentinel.core.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.block.system.SystemBlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的 Sentinel 异常处理器
 *
 * @author 芋道源码
 */
@Slf4j
public class DefaultBlockExceptionHandler {

    /**
     * 处理流控异常
     */
    public static String handleFlowException(FlowException ex) {
        log.warn("[handleFlowException][触发流控规则，资源: {}]", ex.getResourceName());
        return "系统繁忙，请稍后再试";
    }

    /**
     * 处理熔断异常
     */
    public static String handleDegradeException(DegradeException ex) {
        log.warn("[handleDegradeException][触发熔断规则，资源: {}]", ex.getResourceName());
        return "服务暂时不可用，请稍后再试";
    }

    /**
     * 处理热点参数异常
     */
    public static String handleParamFlowException(ParamFlowException ex) {
        log.warn("[handleParamFlowException][触发热点参数规则，资源: {}]", ex.getResourceName());
        return "访问过于频繁，请稍后再试";
    }

    /**
     * 处理系统保护异常
     */
    public static String handleSystemBlockException(SystemBlockException ex) {
        log.warn("[handleSystemBlockException][触发系统保护规则，资源: {}]", ex.getResourceName());
        return "系统负载过高，请稍后再试";
    }

    /**
     * 处理授权异常
     */
    public static String handleAuthorityException(AuthorityException ex) {
        log.warn("[handleAuthorityException][触发授权规则，资源: {}]", ex.getResourceName());
        return "访问被拒绝";
    }

    /**
     * 处理通用的 BlockException
     */
    public static String handleBlockException(BlockException ex) {
        log.warn("[handleBlockException][触发 Sentinel 规则，资源: {}, 异常类型: {}]", 
                ex.getResourceName(), ex.getClass().getSimpleName());
        
        if (ex instanceof FlowException) {
            return handleFlowException((FlowException) ex);
        } else if (ex instanceof DegradeException) {
            return handleDegradeException((DegradeException) ex);
        } else if (ex instanceof ParamFlowException) {
            return handleParamFlowException((ParamFlowException) ex);
        } else if (ex instanceof SystemBlockException) {
            return handleSystemBlockException((SystemBlockException) ex);
        } else if (ex instanceof AuthorityException) {
            return handleAuthorityException((AuthorityException) ex);
        } else {
            return "系统繁忙，请稍后再试";
        }
    }

    /**
     * 通用的 fallback 处理方法
     */
    public static String commonFallback(Throwable ex) {
        log.error("[commonFallback][业务异常]", ex);
        return "系统异常，请稍后再试";
    }
}