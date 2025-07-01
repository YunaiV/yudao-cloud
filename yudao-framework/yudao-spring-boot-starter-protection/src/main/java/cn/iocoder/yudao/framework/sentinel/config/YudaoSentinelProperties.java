package cn.iocoder.yudao.framework.sentinel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Sentinel 配置属性
 *
 * @author 芋道源码
 */
@ConfigurationProperties("yudao.sentinel")
@Data
public class YudaoSentinelProperties {

    /**
     * 是否开启 Sentinel
     */
    private Boolean enabled = true;

    /**
     * 应用名称
     */
    private String appName = "yudao-system";

    /**
     * 数据源配置
     */
    private DataSourceConfig datasource = new DataSourceConfig();

    @Data
    public static class DataSourceConfig {
        /**
         * Nacos 数据源配置
         */
        private NacosConfig nacos = new NacosConfig();
    }

    @Data
    public static class NacosConfig {
        /**
         * 是否启用 Nacos 数据源
         */
        private Boolean enabled = false;

        /**
         * Nacos 服务器地址
         */
        private String serverAddr = "localhost:8848";

        /**
         * 命名空间
         */
        private String namespace = "";

        /**
         * 分组ID
         */
        private String groupId = "SENTINEL_GROUP";

        /**
         * 流控规则 dataId
         */
        private String flowRuleDataId = "";

        /**
         * 熔断规则 dataId
         */
        private String degradeRuleDataId = "";

        /**
         * 系统规则 dataId
         */
        private String systemRuleDataId = "";

        /**
         * 授权规则 dataId
         */
        private String authorityRuleDataId = "";

        /**
         * 热点参数规则 dataId
         */
        private String paramFlowRuleDataId = "";
    }
}