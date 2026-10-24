# 分布式事务（可选接入）

基于 [Apache Seata](https://seata.apache.org/) 2.6.0 AT 模式的可选接入材料。项目默认**不启用**：本目录不是 Maven 模块，不参与默认构建，主工程的依赖、配置、业务表都不受影响。

接入后，各服务的 `master`、`slave` 数据源保持不变、不参与全局事务，只有示例的 `seata_order`（`system-server`）、`seata_account`（`infra-server`）两个数据源由 Dynamic Datasource 包装 Seata 代理。示例 `DemoSeataTradeService#placeOrder` 在 `system-server` 中创建订单，再通过 Feign 调用 `infra-server` 扣减余额，XID 通过 `TX_XID` 请求头传递，两个服务的操作要么都成功，要么都回滚。

Seata Server 的启动、配置、验证及使用限制，请阅读项目文档《分布式事务》，本文只列出材料及分支适用范围：

- 微服务项目 yudao-cloud：<https://cloud.iocoder.cn/distributed-transaction/>
- 单体项目 ruoyi-vue-pro：<https://doc.iocoder.cn/distributed-transaction/>

## 1. 材料清单

| 文件 | 说明 | 目标位置 |
| --- | --- | --- |
| `sql/mysql/demo_seata.sql` | 创建 2 个演示库，各一张示例表 + `undo_log` 表 | 在 MySQL 中执行 |
| `dependencies.xml` | Maven 依赖片段 | `yudao-module-system-server/pom.xml`、`yudao-module-infra-server/pom.xml` 都要添加 |
| `dependencies-jdk25.xml` | 仅 `master-jdk25` 分支按条件补充的 `spring-boot-data-commons` 依赖片段，见下面的《2. 分支适用范围》 | 满足条件的每个使用 Feign 客户端的服务，示例的 `yudao-module-system-server/pom.xml`、`yudao-module-infra-server/pom.xml` 都要检查 |
| `seata.yaml` | 按服务分为两段：数据源及 Seata 客户端的配置片段 | 分别合并到两个服务的 `application-{profile}.yaml` 或 Nacos 配置；`seata: false` 只追加到已经存在的数据源，没有 `slave` 时不要复制 `slave` 段 |
| `src/main/java/.../system/seatademo/` | 订单 DO、Mapper、Service，全局事务入口 `DemoSeataTradeService`，Feign 客户端 `DemoSeataAccountApi`，以及传递 XID 的 `DemoSeataRpcConfiguration` | `yudao-module-system-server/src/main/java` 的同名包 |
| `src/main/java/.../infra/seatademo/` | 账户 DO、Mapper、Service，以及 RPC 接口 `DemoSeataAccountRpcController` | `yudao-module-infra-server/src/main/java` 的同名包 |

示例的包名分别在 `system-server`、`infra-server` 的 `yudao.info.base-package` 下，才能被 Spring、MyBatis 扫描到。不修改 `yudao-module-infra-api` 模块、已有的 `RpcConfiguration` 及安全配置。

单体项目 ruoyi-vue-pro 的 `script/seata/` 目录使用同样的 SQL、依赖片段，以及订单、账户的 DO、Mapper、Service；差异是单体项目在同一个进程中直接调用账户 Service，没有 Feign 相关的类，依赖片段添加到全局事务入口所在的 `yudao-module-system/pom.xml`。

## 2. 分支适用范围

目前只有 `master-jdk17` 分支提供本目录的材料；`master`、`master-jdk25` 分支请先从 `master-jdk17` 分支的 `script/seata/` 获取，同步到对应分支后，也可以直接从当前分支获取。

本目录的材料适用于 `master`（JDK 8 + Spring Boot 2.7.18）、`master-jdk17`（Spring Boot 3.5.15）、`master-jdk25`（Spring Boot 4.1.2）三个分支，接入步骤相同（`master-jdk25` 按条件补充 `dependencies-jdk25.xml`），兼容要点如下：

| 要点 | 说明 |
| --- | --- |
| Seata 坐标 | 只使用 `org.apache.seata:seata-spring-boot-starter:2.6.0`，它自带 Dynamic Datasource 4.6.0 需要的 `io.seata` 兼容类；不要引入 `spring-cloud-starter-alibaba-seata`、`io.seata:*` 坐标，也不要使用 Spring Cloud Alibaba BOM 管理的 Seata 版本 |
| Druid | 排除 Seata 传递的 druid 1.2.25，继续使用项目 Druid starter 提供的版本 |
| Jackson 2 | Seata 的 undo_log 序列化需要 `jackson-databind`、`jackson-datatype-jsr310`；`master-jdk25` 的 Spring Boot 4 默认使用 Jackson 3，必须添加，其它分支添加后版本不变 |
| Feign Encoder | 仅 `master-jdk25`（OpenFeign 5.0.2）：使用 Feign 客户端的服务，启动时的 classpath 中有 `org.springframework.data.domain.Pageable`、却没有 `spring-boot-data-commons` 模块的 `DataWebProperties` 时，OpenFeign 不会创建任何 `Encoder`，启动报 `No bean found of type interface feign.codec.Encoder for xxx`（例如说 `demoSeataAccountApi`、`system-server`），此时为该服务添加 `dependencies-jdk25.xml` 中的依赖。不只是发起方 `system-server`：参与方 `infra-server` 也通过多租户组件注册了 `TenantCommonApi` 等 Feign 客户端，满足条件时同样需要添加。已经有该模块时不需要重复添加，单体项目及 `master`、`master-jdk17` 分支不使用 |
| XID 传递 | 发起方使用示例的 Feign 拦截器添加 `TX_XID` 请求头；参与方使用 Seata 自带的 HTTP 拦截器绑定，不需要额外代码 |
| 示例代码 | 只使用 Java 8 语法，使用构造方法注入，不引用 `javax`、`jakarta` 包，三个分支无需修改 |

`@GlobalTransactional` 注解的包名是 `org.apache.seata.spring.annotation`。全局事务入口必须是组件扫描的 `@Service` 等 Bean，Seata 2.6 不会增强 `@Bean` 方法定义的 Bean。
