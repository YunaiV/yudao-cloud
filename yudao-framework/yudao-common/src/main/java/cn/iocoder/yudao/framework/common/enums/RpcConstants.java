package cn.iocoder.yudao.framework.common.enums;

/**
 * RPC 相关的枚举
 *
 * 虽然放在 yudao-spring-boot-starter-rpc 会相对合适，但是每个 API 模块需要使用到，所以暂时只好放在此处
 *
 * @author 芋道源码
 */
public interface RpcConstants {

    /**
     * RPC API 的前缀
     */
    String RPC_API_PREFIX = "/rpc-api";

    /**
     * system 服务名
     *
     * 注意，需要保证和 spring.application.name 保持一致
     */
    String SYSTEM_NAME = "system-server";

    /**
     * system 服务的前缀
     */
    String SYSTEM_PREFIX = RPC_API_PREFIX + "/system";

    /**
     * infra 服务名
     *
     * 注意，需要保证和 spring.application.name 保持一致
     */
    String INFRA_NAME = "infra-server";
    /**
     * infra 服务的前缀
     */
    String INFRA_PREFIX = RPC_API_PREFIX + "/infra";

}
