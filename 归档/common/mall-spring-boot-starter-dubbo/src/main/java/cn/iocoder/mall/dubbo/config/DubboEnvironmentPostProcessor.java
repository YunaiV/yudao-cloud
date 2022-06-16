package cn.iocoder.mall.dubbo.config;

import cn.iocoder.common.framework.util.OSUtils;
import cn.iocoder.common.framework.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Dubbo 配置项的后置处理器，主要目的如下：
 *
 * 1. 生成 {@link #DUBBO_TAG_PROPERTIES_KEY} 配置项，可用于本地开发环境下的 dubbo.provider.tag 配置项
 */
public class DubboEnvironmentPostProcessor implements EnvironmentPostProcessor {

    /**
     * 默认配置项的 PropertySource 名字
     */
    private static final String PROPERTY_SOURCE_NAME = "mallDubboProperties";

    /**
     * Dubbo 路由标签属性 KEY
     */
    private static final String DUBBO_TAG_PROPERTIES_KEY = "DUBBO_TAG";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 需要修改的配置项
        Map<String, Object> modifyProperties = new HashMap<>();
        // 生成 DUBBO_TAG_PROPERTIES_KEY，使用 hostname
        String dubboTag = OSUtils.getHostName();
        if (!StringUtils.hasText(dubboTag)) {
            dubboTag = StringUtils.uuid(true); // 兜底，强行生成一个
        }
        modifyProperties.put(DUBBO_TAG_PROPERTIES_KEY, dubboTag);
        // 添加到 environment 中，排在最优，最低优先级
        addOrReplace(environment.getPropertySources(), modifyProperties);
    }

    private void addOrReplace(MutablePropertySources propertySources, Map<String, Object> map) {
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        // 情况一，如果存在 defaultProperties 的 PropertySource，则进行 key 的修改
        if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
            PropertySource<?> source = propertySources.get(PROPERTY_SOURCE_NAME);
            if (source instanceof MapPropertySource) {
                MapPropertySource target = (MapPropertySource) source;
                for (String key : map.keySet()) {
                    target.getSource().put(key, map.get(key));
                }
            }
            return;
        }
        // 情况二，不存在 defaultProperties 的 PropertySource，则直接添加到其中
        propertySources.addLast(new MapPropertySource(PROPERTY_SOURCE_NAME, map));
    }

}
