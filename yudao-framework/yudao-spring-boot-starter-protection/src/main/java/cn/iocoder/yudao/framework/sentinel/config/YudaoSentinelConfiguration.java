package cn.iocoder.yudao.framework.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import cn.iocoder.yudao.framework.sentinel.core.web.SentinelWebConfiguration;

import java.util.List;

/**
 * Sentinel 自动配置类
 *
 * @author 芋道源码
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "yudao.sentinel", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(YudaoSentinelProperties.class)
@Import(SentinelWebConfiguration.class)
@Slf4j
public class YudaoSentinelConfiguration {

    /**
     * 注册 SentinelResourceAspect 用于支持 @SentinelResource 注解
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    /**
     * 初始化 Sentinel 数据源
     */
    @Bean
    @ConditionalOnProperty(prefix = "yudao.sentinel.datasource.nacos", name = "enabled", havingValue = "true")
    public InitFunc sentinelDataSourceInitFunc(YudaoSentinelProperties sentinelProperties) {
        return () -> {
            YudaoSentinelProperties.NacosConfig nacosConfig = sentinelProperties.getDatasource().getNacos();
            
            // 流控规则数据源
            if (nacosConfig.getFlowRuleDataId() != null && !nacosConfig.getFlowRuleDataId().isEmpty()) {
                NacosDataSource<List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(
                        nacosConfig.getServerAddr(),
                        nacosConfig.getGroupId(),
                        nacosConfig.getFlowRuleDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
                
                FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
                WritableDataSourceRegistry.registerFlowDataSource(flowRuleDataSource);
                log.info("[sentinelDataSourceInitFunc][注册流控规则数据源完成]");
            }
            
            // 熔断规则数据源
            if (nacosConfig.getDegradeRuleDataId() != null && !nacosConfig.getDegradeRuleDataId().isEmpty()) {
                NacosDataSource<List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(
                        nacosConfig.getServerAddr(),
                        nacosConfig.getGroupId(),
                        nacosConfig.getDegradeRuleDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {}));
                
                DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
                WritableDataSourceRegistry.registerDegradeDataSource(degradeRuleDataSource);
                log.info("[sentinelDataSourceInitFunc][注册熔断规则数据源完成]");
            }
        };
    }
}