package cn.iocoder.yudao.framework.sentinel.core.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Sentinel Web 配置
 * 
 * 注意：由于 Sentinel 1.8.x 版本与 Jakarta Servlet API 的兼容性问题，
 * 此配置暂时简化。如需完整的 Web 保护功能，建议等待 Sentinel 后续版本的支持。
 *
 * @author 芋道源码
 */
@Configuration
@ConditionalOnWebApplication
@Slf4j
public class SentinelWebConfiguration {

    // TODO: 等待 Sentinel 支持 Jakarta Servlet API 后，可以添加更多 Web 相关配置
    // 目前可以通过 @SentinelResource 注解来保护具体的方法
    
    public SentinelWebConfiguration() {
        log.info("[SentinelWebConfiguration][Sentinel Web 配置已加载，当前使用注解模式进行保护]");
    }
}