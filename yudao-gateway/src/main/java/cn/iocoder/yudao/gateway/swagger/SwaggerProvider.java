package cn.iocoder.yudao.gateway.swagger;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Swagger 资源的 Provider 实现类
 *
 * @author zxliu
 * @since 2022-10-25 11:23
 */
@Component
@Primary
@Slf4j
public class SwaggerProvider implements SwaggerResourcesProvider {

    @Resource
    private GatewayProperties gatewayProperties;

    /**
     * 获得 SwaggerResource 列表
     *
     * @return SwaggerResource 列表
     */
    @Override
    public List<SwaggerResource> get() {
        // 将 RouteDefinition 转换成 SwaggerResource
        List<SwaggerResource> resources = new ArrayList<>();
        Set<String> serviceNames = new HashSet<>(); // 已处理的服务名，避免重复
        gatewayProperties.getRoutes().forEach(route -> {
            // 已存在的服务，直接忽略
            String serviceName = route.getUri().getHost();
            if (StrUtil.isEmpty(serviceName)) {
                return;
            }
            if (!serviceNames.add(serviceName)) {
                return;
            }

            // 获得 Path PredicateDefinition
            String path = getRoutePath(route);
            if (path == null) {
                return;
            }

            // 重要：构建最终的 SwaggerResource 对象
            resources.add(buildSwaggerResource(serviceName, path));
        });
        return resources;
    }

    private SwaggerResource buildSwaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("3.0.3");
        return swaggerResource;
    }

    /**
     * 获得路由的 Path
     *
     * ① 输入：
     *  predicates:
     *      - Path=/admin-api/system/**
     * ② 输出：
     *  /admin-api/system/v3/api-docs
     *
     * @param route 路由
     * @return 路由
     */
    private String getRoutePath(RouteDefinition route) {
        PredicateDefinition pathDefinition = CollUtil.findOne(route.getPredicates(),
                predicateDefinition -> "Path".equals(predicateDefinition.getName()));
        if (pathDefinition == null) {
            log.info("[get][Route({}) 没有 Path 条件，忽略接口文档]", route.getId());
            return null;
        }
        String path = pathDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0");
        if (StrUtil.isEmpty(path)) {
            log.info("[get][Route({}) Path 的值为空，忽略接口文档]", route.getId());
            return null;
        }
        return path.replace("/**", "/v3/api-docs");
    }

}
