[toc]

# 前言

基于微服务的思想，构建在 B2C 电商场景下的项目实战。

* 「Talk is cheap. Show me the code」（屁话少说，放码过来）
    > 我们看过很多技术文章，却依然不知道微服务该咋整。

* 整体的功能如下图：![功能图](http://static.iocoder.cn/mall%20%E5%8A%9F%E8%83%BD%E5%9B%BE-min.png)
    > 功能图，和实际后端模块拆分，并不是绝对对应。

* 交流群：[传送门](http://www.iocoder.cn/mall-user-group/?vip&gitee)
    > 一起交流，Get 知识。

* 我们迫切希望更多的参与进来，可以加入「交流群」，一起骚聊。

# 演示

## H5 商城

[体验传送门](http://h5.shop.iocoder.cn:18099)

TODO 此处应有一个演示的装逼 GIF 图。

## 管理后台

[体验传送门](http://admin.shop.iocoder.cn:18099)

TODO 暂时不提供管理后台的账号密码，等后面提供。

TODO 此处应有一个演示的装逼 GIF 图。

## TODO

提供其他演示环境。例如说 skywalking、sentinel 等等。

# 技术

## 搭建环境

[搭建调试环境](https://gitee.com/zhijiantianya/onemall/blob/master/docs/setup/quick-start.md)

## 架构图

TODO 此处应有一个架构图的装逼 JPG 图。

## 项目结构

| 模块 | 名称 | 端口 |
| --- | --- | --- |
| `admin-web` | 【前端】管理后台 | HTTP 8080 |
| `mobile-web` | 【前端】商城 H5 | HTTP 8000 |
| `admin-application` | 管理员 HTTP 服务 | HTTP 18083 |
| `user-application` | 用户 HTTP 服务 | HTTP 18082 |
| `product-application` | 商品 HTTP 服务 | HTTP 18081 |
| `pay-application` | 支付 HTTP 服务 | HTTP 18084 |
| `promotion-application` | 促销 HTTP 服务 | HTTP 18085 |
| `search-application` | 搜索 HTTP 服务 | HTTP 18086 |
| `order-application` | 订单 HTTP 服务 | HTTP 18088 |

-------

后端项目，目前的项目结构如下：

```Java
[-] xxx
  ├──[-] xxx-application // 提供对外 HTTP API 。
  ├──[-] xxx-service-api // 提供 Dubbo 服务 API 。
  ├──[-] xxx-service-impl // 提供 Dubbo 服务 Service 实现。
```

考虑到大多数公司，无需拆分的特别细，并且过多 JVM 带来的服务器成本。所以目前的设定是：

* `xxx-service-impl` 内嵌在 `xxx-application` 中运行。
* MQ 消费者、定时器执行器，内嵌在 `xxx-service-impl` 中运行。

也就是说，一个 `xxx-application` 启动后，该模块就完整启动了。

## 技术栈

### 后端

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Spring Boot](https://spring.io/projects/spring-boot) | 应用开发框架 |   2.1.4 |
| [MySQL](https://www.mysql.com/cn/) | 数据库服务器 | 5.6 |
| [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html) | 数据持久层框架 | 3.5.0 |
| [Redis](https://redis.io/) | key-value 数据库 | 暂未引入，等压测后，部分模块 |
| [Redisson](https://github.com/redisson/redisson) | Redis 客户端 | 暂未引入，等压测后，部分模块 |
| [Elasticsearch](https://www.elastic.co/cn/) | 分布式搜索引擎 | 6.7.1 |
| [Dubbo](http://dubbo.apache.org/) | 分布式 RPC 服务框架 | 2.6.5 |
| [RocketMQ](http://dubbo.apache.org/) | 消息中间件 | 4.3.2 |
| [SkyWalking](http://skywalking.apache.org/) | 分布式应用追踪系统 | 6.0.0 |
| [Zookeeper](http://zookeeper.apache.org/) | 分布式系统协调 | 3.4.9 作为注册中心 |
| [XXL-Job](http://www.xuxueli.com/xxl-job/) | 分布式任务调度平台 | 2.0.1 |

未来考虑引入

* [ ] 配置中心 Nacos
* [ ] 服务保障 Sentinel
* [ ] 分布式事务 Seata
* [ ] 数据库连接池 Druid
* [ ] 网关 Soul

### 前端

商城 H5 和管理后台，分别采用了 Vue 和 React ，基于其适合的场景考虑。具体的，可以看看 [《为什么 React 比 Vue 更适合大型应用？》](https://www.zhihu.com/question/314761485/answer/615318460) 的讨论。

**商城 H5**

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Vue](https://cn.vuejs.org/index.html) | JavaScript 框架 | 2.5.17 |
| [Vant](https://youzan.github.io/vant/#/zh-CN/intro) | Vue UI 组件库 | 3.13.0 |

**管理后台**

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [React](https://reactjs.org/) | JavaScript 框架  | 16.7.0 |
| [Ant Design](https://ant.design/docs/react/introduce-cn) | React UI 组件库 | 3.13.0 |

### 其它

* Jenkins 持续集成
* Nginx 服务器
* [ ] Docker 容器
* [ ] Nginx

# 某种结尾

目前成员

* 小范
* 芋艿
