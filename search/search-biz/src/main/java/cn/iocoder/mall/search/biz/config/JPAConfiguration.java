package cn.iocoder.mall.search.biz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "cn.iocoder.mall.search.biz.dao")
public class JPAConfiguration {
}
