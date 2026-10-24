package cn.iocoder.yudao.module.infra.seatademo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 分布式事务示例账户的 RPC 接口，给 system-server 的 DemoSeataAccountApi 调用
 *
 * 1. 路径在 ApiConstants.PREFIX（/rpc-api/infra）下，与项目其它 RPC 接口一样，只给内网的服务调用，不经过网关对外暴露
 * 2. 请求头中的 TX_XID 由 Seata 自带的 HTTP 拦截器绑定，账户 Service 的本地事务因此成为全局事务的一个分支
 * 3. 不添加 @GlobalTransactional：全局事务只在发起方开启
 *
 * @author 芋道源码
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@RequiredArgsConstructor // 使用构造方法注入，避免 JDK 8 与 JDK 17+ 分支的 @Resource 包名不同
public class DemoSeataAccountRpcController {

    /**
     * 与 system-server 中 DemoSeataAccountApi 的 PREFIX 保持一致
     */
    public static final String PREFIX = ApiConstants.PREFIX + "/seata-demo/account";

    private final DemoSeataAccountService demoSeataAccountService;

    @PostMapping(PREFIX + "/deduct-balance")
    public CommonResult<Boolean> deductBalance(@RequestParam("userId") Long userId,
                                               @RequestParam("amount") Integer amount) {
        // 余额不足时抛出异常，由全局异常处理器转换成 CommonResult 错误码，发起方通过 getCheckedData() 重新抛出
        demoSeataAccountService.deductBalance(userId, amount);
        return success(true);
    }

}
