package cn.iocoder.yudao.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class SpringDocConfiguration {
    private static final String SERVER_NAME_SUFFIX = "-api";

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        for (RouteDefinition definition : definitions) {
            log.info("id: " + definition.getId() + "  " + definition.getUri().toString());
        }
        definitions.stream()
                .filter(routeDefinition -> routeDefinition.getId().matches(".*"+SERVER_NAME_SUFFIX))
                .forEach(routeDefinition -> {
                    String name = routeDefinition.getId().replaceAll(SERVER_NAME_SUFFIX, "");
                    GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
                });
        return groups;
    }
}
