package cn.iocoder.yudao.module.system.seatademo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 分布式事务示例：调用 infra-server 账户 RPC 接口的 Feign 客户端
 *
 * 示例为了不修改 yudao-module-infra-api 模块，将接口放在发起方；自己的业务，建议和项目其它 RPC 一样，放到被调用方的 -api 模块
 *
 * @author 芋道源码
 */
@FeignClient(name = ApiConstants.NAME, contextId = "demoSeataAccountApi") // 独立的 contextId，避免与 infra-server 已有的 Feign 客户端冲突
public interface DemoSeataAccountApi {

    /**
     * 与 infra-server 中 DemoSeataAccountRpcController 的 PREFIX 保持一致
     */
    String PREFIX = ApiConstants.PREFIX + "/seata-demo/account";

    @PostMapping(PREFIX + "/deduct-balance")
    CommonResult<Boolean> deductBalance(@RequestParam("userId") Long userId,
                                        @RequestParam("amount") Integer amount);

}
