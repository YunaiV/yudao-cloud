/**
 * 在 Nacos 配置发生变化时，Spring Cloud Alibaba Nacos Config 内置的监听器，会监听到配置刷新，最终触发 Gateway 的路由信息刷新。
 *
 * 参见 https://www.iocoder.cn/Spring-Cloud/Spring-Cloud-Gateway/?yudao 博客的「6. 基于配置中心 Nacos 实现动态路由」小节
 *
 * 使用方式：在 Nacos 修改 DataId 为 gateway-server.yaml 的配置，修改 spring.cloud.gateway.routes 配置项
 *
 * @author 芋道源码
 */
package cn.iocoder.yudao.gateway.route.dynamic;
