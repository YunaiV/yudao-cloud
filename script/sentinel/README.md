# 服务保障（可选接入）

基于 [Sentinel](https://sentinelguard.io/zh-cn/docs/introduction.html) + [Spring Cloud Alibaba](https://sca.aliyun.com/) 的可选接入材料，演示网关限流、服务限流、Feign 熔断降级，规则持久化在 Nacos 中。项目默认**不启用**：本目录不是 Maven 模块，不参与默认构建，主工程的依赖、配置、业务代码都不受影响。

项目的 `yudao-spring-boot-starter-protection` 组件本身**不包含** Sentinel，它提供的是基于 Redis（Redisson）的 `@RateLimiter` 请求限流、`@Idempotent` 幂等、Lock4j 分布式锁，接入本材料后保持不变，可以和 Sentinel 同时使用。

示例只接入 `gateway-server`、`system-server` 两个服务，`infra-server` 只提供被调用的 RPC 接口：

- `gateway-server`：按自定义 API 分组 `system-sentinel-demo-api`、路由 `system-admin-api` 限流，被阻断时返回 HTTP 429/503 + `CommonResult` 格式的 JSON。
- `system-server`：按 URL 资源 `/admin-api/system/sentinel-demo/flow` 限流；按 Feign 资源 `GET:http://infra-server/rpc-api/infra/sentinel-demo/echo` 的慢调用比例熔断，熔断期间由示例 Feign 客户端**显式配置**的 `fallbackFactory` 返回错误码。项目已有的 Feign 客户端不配置 fallback，行为不变。

接入步骤、验证方式及使用限制，请阅读项目文档《服务保障》，本文只列出材料及分支适用范围：

- 微服务项目 yudao-cloud：<https://cloud.iocoder.cn/server-protection/>
- 单体项目 ruoyi-vue-pro：<https://doc.iocoder.cn/server-protection/>

## 1. 材料清单

| 文件 | 说明 | 目标位置 |
| --- | --- | --- |
| `dependencies.xml` | Maven 依赖片段：第 1 段 Sentinel + Nacos 数据源，第 2 段 Spring Cloud Gateway 适配 | 第 1 段添加到 `yudao-gateway/pom.xml`、`yudao-module-system-server/pom.xml`；第 2 段只添加到 `yudao-gateway/pom.xml` |
| `dependencies-jdk25.xml` | 仅 `master-jdk25` 分支按条件补充的 `spring-boot-data-commons` 依赖片段，见下面的《2. 分支适用范围》 | 满足条件时，添加到对应服务（例如说 `system-server`、`infra-server`）的 `pom.xml`，`gateway-server` 不适用 |
| `sentinel.yaml` | 按服务分为两段：Dashboard、Nacos 规则数据源，以及 `system-server` 的 `feign.sentinel.enabled` | 分别合并到两个服务的 `application-{profile}.yaml` 或 Nacos 配置 |
| `nacos/*.json` | 4 个规则文件，文件名就是 Nacos 中的 Data ID（含 `.json` 后缀），Group 为 `SENTINEL_GROUP` | 在 Nacos 服务自己的命名空间（默认 `dev`）中创建同名配置，内容为文件内容 |
| `src/main/java/.../gateway/sentineldemo/` | `DemoSentinelGatewayConfiguration`：网关被阻断时的 `BlockRequestHandler` | `yudao-gateway/src/main/java` 的同名包 |
| `src/main/java/.../system/sentineldemo/` | 示例 Feign 客户端 `DemoSentinelInfraApi` 及其 `DemoSentinelInfraApiFallbackFactory`，配置类 `DemoSentinelConfiguration`（含 URL 资源被阻断时的 `BlockExceptionHandler`），示例接口 `controller/admin/DemoSentinelController` | `yudao-module-system-server/src/main/java` 的同名包 |
| `src/main/java/.../infra/sentineldemo/` | 被调用的 RPC 接口 `DemoSentinelRpcController`，模拟慢调用、业务错误 | `yudao-module-infra-server/src/main/java` 的同名包 |

示例的包名分别在 `gateway-server`（`cn.iocoder.yudao.gateway`）、`system-server`、`infra-server` 的扫描范围内；`DemoSentinelController` 放在 `controller.admin` 子包下，才会添加 `/admin-api` 前缀。不修改 `yudao-module-infra-api` 模块、已有的 `RpcConfiguration`、Feign 客户端及安全配置。

规则 JSON 是严格的 JSON，不能包含注释，字段说明见项目文档。资源名与示例一一对应：

| 服务 | Data ID | 资源名 |
| --- | --- | --- |
| `gateway-server` | `gateway-server-gw-api-group-rules.json` | API 分组 `system-sentinel-demo-api`：精确匹配 `/admin-api/system/sentinel-demo/gateway-flow` |
| `gateway-server` | `gateway-server-gw-flow-rules.json` | `system-sentinel-demo-api`（API 分组，QPS 2）、`system-admin-api`（routeId，QPS 200） |
| `system-server` | `system-server-flow-rules.json` | `/admin-api/system/sentinel-demo/flow`（URL，QPS 2） |
| `system-server` | `system-server-degrade-rules.json` | `GET:http://infra-server/rpc-api/infra/sentinel-demo/echo`（Feign，慢调用比例） |

## 2. 分支适用范围

目前只有 `master-jdk17` 分支提供本目录的材料；`master`、`master-jdk25` 分支请先从 `master-jdk17` 分支的 `script/sentinel/` 获取，同步到对应分支后，也可以直接从当前分支获取。

本目录的材料按 `master`（JDK 8 + Spring Boot 2.7.18）、`master-jdk17`（Spring Boot 3.5.15）、`master-jdk25`（Spring Boot 4.1.2）三个分支的 API 编写，接入步骤相同，兼容要点如下：

| 要点 | 说明 |
| --- | --- |
| Sentinel 版本 | 依赖不写版本，由项目已有的 Spring Cloud Alibaba BOM 管理：`master` 是 2021.0.6.2（Sentinel 1.8.6），`master-jdk17` 是 2025.0.0.0（Sentinel 1.8.9），`master-jdk25` 是 2025.1.0.0（Sentinel 1.8.9）。不建议单独覆盖 Sentinel 的版本 |
| 网关适配 | `spring-cloud-alibaba-sentinel-gateway` 按分支传递对应的适配模块，`BlockRequestHandler` 的包名相同，`DemoSentinelGatewayConfiguration` 三个分支无需修改 |
| URL 资源的 `BlockExceptionHandler` | `master-jdk17`、`master-jdk25` 是 `com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler`，有 `resourceName` 参数；`master` 是 `com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler`，没有该参数。`master` 分支需要修改 `DemoSentinelConfiguration` 的这个 import，lambda 改为 `(request, response, ex) -> {...}`，其它代码不变 |
| Feign fallback | `master` 的 Spring Cloud Alibaba 2021.0.6.2 会把 fallback 方法中抛出的异常包装成 `AssertionError`，所以示例的 `fallbackFactory` 只返回 `CommonResult` 错误码、不抛出异常，三个分支的行为一致 |
| Feign Encoder | 仅 `master-jdk25`（OpenFeign 5.0.2）：适用于每个使用 Feign 的服务，包括已有、新增的 Feign 客户端，例如说 `system-server`（新增 `DemoSentinelInfraApi`）、`infra-server`（已有 `TenantCommonApi` 等）；`gateway-server` 不使用 Feign，不适用。该服务启动时的 classpath 中有 `org.springframework.data.domain.Pageable`、却没有 `spring-boot-data-commons` 模块的 `DataWebProperties` 时，OpenFeign 不会创建任何 `Encoder`，启动报 `No bean found of type interface feign.codec.Encoder for xxx`，此时才为该服务添加 `dependencies-jdk25.xml` 中的依赖，不要无条件添加。已经有该模块时不需要重复添加，`master`、`master-jdk17` 分支不使用 |
| 网关应用类型 | 不需要手动设置 `csp.sentinel.app.type`：三个分支的 `spring-cloud-alibaba-sentinel-gateway` 启动时都自动设置为 `11`（Spring Cloud Gateway），Dashboard 按网关应用展示。网关显示为普通应用时，核对是否接入了该适配器、Dashboard 的版本（1.8.x）与连通性，以及 Dashboard 中实际注册的应用类型、是否遗留了同名应用的旧机器信息，详见项目文档 |
| Spring Boot 4 | `master-jdk25` 的 Spring Cloud Alibaba 2025.1.0.0 使用 Jackson 3 解析规则；它的 Sentinel starter 将 `spring-boot-health` 声明为 `provided`，项目的 monitor 组件已经引入 Actuator，不需要额外添加。Sentinel 官方没有声明对 Spring Cloud Gateway 5、Spring Framework 7 的适配，接入后以实际的启动、请求验证为准 |
| 示例代码 | 只使用 Java 8 语法，使用构造方法注入，都不直接引用 `javax`、`jakarta` 包；只有 `DemoSentinelConfiguration` 的 `BlockExceptionHandler` 需要按上面的说明修改 |
