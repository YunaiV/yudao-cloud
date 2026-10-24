package cn.iocoder.yudao.module.infra.sentineldemo;

import cn.hutool.core.thread.ThreadUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * Sentinel 示例的 RPC 接口，给 system-server 的 DemoSentinelInfraApi 调用
 *
 * 1. 路径在 ApiConstants.PREFIX（/rpc-api/infra）下，与项目其它 RPC 接口一样，只给内网的服务调用，不经过网关对外暴露
 * 2. 只用于模拟慢调用、业务错误，演示调用方 system-server 的熔断降级；infra-server 自己不需要接入 Sentinel
 *
 * @author 芋道源码
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
public class DemoSentinelRpcController {

    /**
     * 与 system-server 中 DemoSentinelInfraApi 的 PREFIX 保持一致
     */
    public static final String PREFIX = ApiConstants.PREFIX + "/sentinel-demo";

    @GetMapping(PREFIX + "/echo")
    public CommonResult<String> echo(@RequestParam("mode") String mode) {
        // 慢调用：超过 system-server 熔断规则中 300 毫秒的最大响应时间
        if ("slow".equals(mode)) {
            ThreadUtil.sleep(500);
            return success("slow");
        }
        // 业务错误：由全局异常处理器转换成 CommonResult 错误码，HTTP 状态码仍是 200，调用方的 Sentinel 不会计为异常
        if ("biz-error".equals(mode)) {
            throw new ServiceException(BAD_REQUEST.getCode(), "模拟 infra-server 的业务错误");
        }
        return success("ok");
    }

}
