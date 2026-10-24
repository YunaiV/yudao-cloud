package cn.iocoder.yudao.module.system.seatademo;

import lombok.RequiredArgsConstructor;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

/**
 * 分布式事务示例下单 Service，即全局事务的入口（发起方）
 *
 * 微服务项目中，订单在 system-server 的 seata_order 数据源，账户在 infra-server 的 seata_account 数据源，通过 Feign 调用账户服务
 *
 * @author 芋道源码
 */
@Service // 使用 @Service 组件扫描注册：Seata 2.6 不会增强 @Bean 方法定义的 Bean，@GlobalTransactional 会失效
@RequiredArgsConstructor // 使用构造方法注入，避免 JDK 8 与 JDK 17+ 分支的 @Resource 包名不同
public class DemoSeataTradeService {

    private final DemoSeataOrderService demoSeataOrderService;
    private final DemoSeataAccountApi demoSeataAccountApi;

    /**
     * 下单：创建订单 + 扣减余额，两个服务的操作要么都成功，要么都回滚
     *
     * 注意，这里只添加 @GlobalTransactional，【不要】添加 @Transactional：
     * 本地事务会在开始时固定一个数据源的连接，导致后续的 @DS 切换失效，分支的事务由各自的 Service 开启
     *
     * @param orderId 订单编号，由调用方生成，作为幂等主键
     * @param userId  用户编号
     * @param amount  订单金额，单位：分
     */
    @GlobalTransactional(name = "demo-seata-place-order", rollbackFor = Exception.class)
    public void placeOrder(Long orderId, Long userId, Integer amount) {
        // 1. 创建订单：分支一，本地事务提交后，订单已经写入 seata_order 库，同时记录 undo_log
        demoSeataOrderService.createOrder(orderId, userId, amount);
        // 2. 扣减余额：分支二，由 infra-server 在 seata_account 库执行；XID 通过 Feign 请求头传递
        // 必须调用 getCheckedData()：账户服务的业务异常会被转换成 CommonResult 错误码返回，不检查就不会抛出异常，全局事务会被提交
        demoSeataAccountApi.deductBalance(userId, amount).getCheckedData();
    }

}
