package cn.iocoder.mall.searchservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration(proxyBeanMethods = false)
@EnableElasticsearchRepositories(basePackages = "cn.iocoder.mall.search.biz.dao")
public class ElasticsearchConfiguration {
}
