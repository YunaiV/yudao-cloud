[toc]

> 友情提示：近期在升级和优化该项目，建议先 Star 本项目。主要在做几个事情：
> 
> * 1、微服务技术选型以 Spring Cloud Alibaba 为中心。
> * 2、修改项目分层，并合并部分服务，简化整体服务的复杂性。
> * 3、将管理后台从 React 重构到 Vue 框架。
> 
> 交流群：[传送门](http://www.iocoder.cn/mall-user-group/?vip&gitee)

# 前言

基于微服务的思想，构建在 B2C 电商场景下的项目实战。

* 「Talk is cheap. Show me the code」（屁话少说，放码过来）
    > 我们看过很多技术文章，却依然不知道微服务该咋整。

* 这会是一个认真做的业务开源项目，目前 Java 代码 2w+ 行，不包括注释的情况下。

* 整体的功能如下图：![功能图](http://static.iocoder.cn/mall%20%E5%8A%9F%E8%83%BD%E5%9B%BE-min.png)
    > 功能图，和实际后端模块拆分，并不是绝对对应。
    * [功能列表 - H5 商城](https://gitee.com/zhijiantianya/onemall/blob/master/docs/guides/%E5%8A%9F%E8%83%BD%E5%88%97%E8%A1%A8/%E5%8A%9F%E8%83%BD%E5%88%97%E8%A1%A8-H5%20%E5%95%86%E5%9F%8E.md)
    * [功能列表 - 管理后台](https://gitee.com/zhijiantianya/onemall/blob/master/docs/guides/%E5%8A%9F%E8%83%BD%E5%88%97%E8%A1%A8/%E5%8A%9F%E8%83%BD%E5%88%97%E8%A1%A8-%E7%AE%A1%E7%90%86%E5%90%8E%E5%8F%B0.md)

* 交流群：[传送门](http://www.iocoder.cn/mall-user-group/?vip&gitee)
    > 一起交流，Get 知识。

* 我们迫切希望更多的参与进来，可以加入「交流群」，一起骚聊。
    * [《Onemall 电商开源项目 —— 应用分层》](http://www.iocoder.cn/Onemall/Application-layer/?vip&onemall)

# 演示

> 艿艿：目前的开发者，都是后端出身。所以，一帮没有审美自觉的人，撸出来的前端界面，可能是东半球倒数第二难看。
>
> 迫切希望，有前端能力不错的小伙伴，加入我们，一起来完善「芋道商城」。

## 管理后台

体验传送门：<http://dashboard.shop.iocoder.cn>

![GIF 图-耐心等待](https://raw.githubusercontent.com/YunaiV/Blog/master/Mall/onemall-admin-min.gif)

## H5 商城

体验传送门：<http://h5.shop.iocoder.cn>

*2M 带宽小水管，访问略微有点慢*

![GIF 图-耐心等待](https://raw.githubusercontent.com/YunaiV/Blog/master/Mall/onemall-h5-min.gif)

## 其它演示

下面，我们会提供目前用到的中间件的管理平台。

> 艿艿：考虑到大家可以看到更全的功能，所以一般提供 admin 账号。所以，大家素质使用哟。

**SkyWalking UI**

* 地址：<http://skywalking.shop.iocoder.cn>

> 教程：[《芋道 SkyWalking 安装部署》](http://www.iocoder.cn/SkyWalking/install/?onemall)

**Grafana UI**

* 地址：http://grafana.shop.iocoder.cn:18099
* 演示账号：yudaoyuanma / yudaoyuanma
* 用于展示 Prometheus 收集的 Metrics 指标数据。

**Dubbo Admin**

* 地址：http://dubbo-admin.shop.iocoder.cn:18099
* 管理员账号：无需登陆

**RocketMQ Console**

* 地址：<http://rocketmq.shop.iocoder.cn>

> 教程：[《芋道 RocketMQ 安装部署》](http://www.iocoder.cn/RocketMQ/install/?onemall)

**XXL-Job Console**

* 地址：<http://xxl-job.shop.iocoder.cn>
* 管理员账号：admin / 123456

> 教程：[《芋道 XXL-Job 安装部署》](http://www.iocoder.cn/XXL-JOB/install/?onemall)

**Sentinel Console**

* 地址：<http://sentinel.shop.iocoder.cn>
* 账号：sentinel / sentinel

> 教程：[《芋道 Sentinel 安装部署》](http://www.iocoder.cn/Sentinel/install/?onemall)

# 技术

## 搭建环境

[搭建调试环境](https://gitee.com/zhijiantianya/onemall/blob/master/docs/setup/quick-start.md)

## 架构图

TODO 此处应有一个架构图的装逼 JPG 图。

## 项目结构

| 模块 | 名称 | 端口 | |
| --- | --- | --- | --- |
| [`admin-dashboard-vue`](https://github.com/YunaiV/onemall-web/tree/master/admin-dashboard-vue) | 【前端】管理后台 | HTTP 9527 | |
| [`user-dashboard-vue`](https://github.com/YunaiV/onemall-web/tree/master/user-h5-vue) | 【前端】商城平台 | HTTP 8080 | |
| | | |
| | | |
| `management-web-app`  | 【后端】管理平台 HTTP 服务 | HTTP 18083 | [接口文档](http://api-dashboard.shop.iocoder.cn/management-api/doc.html) |
| `shop-web-app`  | 【后端】商城平台 HTTP 服务 | HTTP 18084 | [接口文档](http://api-h5.shop.iocoder.cn/shop-api/doc.html) |
| | | |
| | | |
| `system-service-project` | 系统 RPC 服务 |  随机 |  
| `user-service-project` | 用户 RPC 服务 | 随机 |  |
| `promotion-service-project` | 营销 RPC 服务 | 随机 |  |
| `pay-service-project` | 支付 RPC 服务 | 随机 |  |
| `trade-service-project` | 交易 RPC 服务 | 随机 |  |
| `product-service-project` | 商品 RPC 服务 | 随机 |  |
| `search-service-project` | 搜索å RPC 服务 | 随机 |  |

-------

后端项目，目前的项目结构如下：

```Java
[-] xxx-web-app // 提供对外 HTTP API。

[-] xxx-service-project
    ├──[-] xxx-service-api // 提供对内 RPC API 。
    ├──[-] xxx-service-app // 提供对内 RPC 实现。
    ├──[-] xxx-service-integration-test // 集成测试。
```


## 技术栈

### 后端

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Spring Boot](https://spring.io/projects/spring-boot) | 应用开发框架 |   2.1.4 |
| [MySQL](https://www.mysql.com/cn/) | 数据库服务器 | 5.6 |
| [Druid](https://github.com/alibaba/druid) | JDBC 连接池、监控组件 | 1.1.16 |
| [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html) | 数据持久层框架 | 3.5.1 |
| [MyBatis-Plus](https://mp.baomidou.com/) | Mybatis 增强工具包 | 3.1.1 |
| [Redis](https://redis.io/) | key-value 数据库 | 暂未引入，等压测后，部分模块 |
| [Redisson](https://github.com/redisson/redisson) | Redis 客户端 | 暂未引入，等压测后，部分模块 |
| [Elasticsearch](https://www.elastic.co/cn/) | 分布式搜索引擎 | 6.7.1 |
| [Dubbo](http://dubbo.apache.org/) | 分布式 RPC 服务框架 | 2.7.1 |
| [RocketMQ](http://dubbo.apache.org/) | 消息中间件 | 4.3.2 |
| [Seata](https://github.com/seata/seata) | 分布式事务中间件 | 0.5.1 |
| [Zookeeper](http://zookeeper.apache.org/) | 分布式系统协调 | 3.4.9 作为注册中心 |
| [XXL-Job](http://www.xuxueli.com/xxl-job/) | 分布式任务调度平台 | 2.0.1 |
| [springfox-swagger2](https://github.com/springfox/springfox/tree/master/springfox-swagger2) | API 文档 | 2.9.2 |
| [swagger-bootstrap-ui](https://gitee.com/xiaoym/swagger-bootstrap-ui) | Swagger 增强 UI 实现 | 1.9.3 |

未来考虑引入

* [ ] 配置中心 Apollo
* [ ] 服务保障 Sentinel
* [ ] 网关 Soul

### 前端

**商城 H5**

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Vue](https://cn.vuejs.org/index.html) | JavaScript 框架 | 2.5.17 |
| [Vant](https://youzan.github.io/vant/#/zh-CN/intro) | Vue UI 组件库 | 3.13.0 |

**管理后台**

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Vue](https://cn.vuejs.org/index.html) | JavaScript 框架 | 2.5.17 |
| [Vue Element Admin](https://ant.design/docs/react/introduce-cn) | 后台前端解决方案 | - |

### 监控

一般来说，监控会有三种方式：

* 1、Tracing ，我们采用 Apache SkyWalking
* 2、Logging ，我们采用 ELK
* 3、Metrics ，我们采用 Prometheus

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [SkyWalking](http://skywalking.apache.org/) | 分布式应用追踪系统 | 6.0.0 |
| [Prometheus](https://prometheus.io/) | 服务监控体系 | 2.9.2 |
| [Alertmanager](https://prometheus.io/docs/alerting/alertmanager/) | 告警管理器 | 0.17.0 |
| [Grafana](https://grafana.com/) | 仪表盘和图形编辑器 | 0.17.0 |

### 其它

* Jenkins 持续集成
* Nginx 服务器
* [ ] Docker 容器
* [ ] Nginx

# 某种结尾

目前成员

* 小范
* 芋艿
