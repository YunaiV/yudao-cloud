> 艿艿：本文暂时会写的比较简洁，如果有不懂的地方，请来[「交流群」](http://www.iocoder.cn/mall-user-group/?vip&gitee)，艿艿来帮你解决。
>
> 交流群，我们提供了我们自己在使用的开发环境，搭建调试环境会更简便。

# 1. 概述

> 艿艿：本文暂时会写的比较简洁，如果有不懂的地方，请来[「交流群」](http://www.iocoder.cn/mall-user-group/?vip&gitee)，艿艿来帮你解决。

本文，我们希望能带着胖友，快速搭建一个开发/调试环境。总的来说，我们需要安装如下东西：

* 后端
    * JDK 8+
    * Maven
    * IntelliJ IDEA

* 前端
    * NPM

# 2. 源码拉取

使用 IntelliJ IDEA 从 <https://gitee.com/zhijiantianya/onemall> 。拉取完成后，Maven 会下载依赖包，可能会花费一些时间，耐心等待下。

> 艿艿：也不要瞎等，咱继续顺着本文往下走。

# 3. MySQL

① 安装 MySQL 数据库

* Windows ：参考 [《Windows 安装 MySQL》](https://juejin.im/post/5bdab0645188251e753c66f8)
* Mac ：参考 [《Mac 下安装与配置 MySQL》](https://www.jianshu.com/p/a8e4068a7a8a)

② 导入 SQL

将 [docs/sql](https://gitee.com/zhijiantianya/onemall/tree/master/docs/sql) 下的 SQL ，逐个导入到数据库中。

③ 修改项目中的 MySQL 配置

在 IDEA 中，搜索每个 `xxx-service-impl` 项目下的 `application.yaml` 文件，将数据库配置修改成连接你的。如下：

```YAML
spring:
  # datasource
  datasource:
    url: jdbc:mysql://s1.iocoder.cn:3306/mall_product?useSSL=false&useUnicode=true&characterEncoding=UTF-8 # 请修改成你本地的 MySQL url
    driver-class-name: com.mysql.jdbc.Driver
    username: root # 请修改成你本地的 MySQL username
    password: 3WLiVUBEwTbvAfsh # 请修改成你本地的 MySQL password
```

# 4. Zookeeper

① 安装 Zookeeper

* Windows ：参考 [《Windows 下 ZooKeeper 的配置和启动步骤 —— 单机模式》](https://www.jianshu.com/p/66857cbccbd3)
* Mac ：参考 [《Zookeeper 安装及配置（Mac）》](https://www.jianshu.com/p/0ba61bf7149f)

② 修改项目中的 Zookeeper 配置

在 IDEA 中，搜索每个 `xxx-service-impl` 项目下的 `application.yaml` 文件，将 Zookeeper 配置修改成连接你的。如下：

```YAML
# dubbo
dubbo:
  application:
    name: product-service
  registry:
    address: zookeeper://127.0.0.1:2181 # 请修改成你本地的 Zookeeper url
  protocol:
    port: -1
    name: dubbo
  scan:
    base-packages: cn.iocoder.mall.product.service
```

# 5. RocketMQ

① 安装 RocketMQ

* Windows ：参考 [《RocketMQ 入门 —— 安装以及快速入门》](http://www.iocoder.cn/RocketMQ/start/install/?vip&gitee)
* Mac ：参考 [《RocketMQ 入门 —— 安装以及快速入门》](http://www.iocoder.cn/RocketMQ/start/install/?vip&gitee)

② 修改项目中的 RocketMQ 配置

在 IDEA 中，搜索每个 `xxx-service-impl` 项目下的 `application.yaml` 文件，将 RocketMQ 配置修改成连接你的。如下：

```YAML
rocketmq:
  name-server: 127.0.0.1:9876 # 请修改成你本地的 RocketMQ url
  producer:
    group: product-producer-group
```

# 6. XXL-Job

> 艿艿：这个中间件的安装，是可选项。如果不安装，只是定时任务无法执行。

TODO 未完成。建议先跳过。

① 安装 XXL-Job

参考 [《分布式任务调度平台 XXL-JOB》](http://www.xuxueli.com/xxl-job/#/) 官方文档。

② 修改项目中的 XXL-Job 配置

在 IDEA 中，搜索每个 `xxx-service-impl` 项目下的 `application-dev.yaml` 文件，将 XXL-Job 配置修改成连接你的。如下：

```YAML
# xxl-job
xxl:
  job:
    admin:
      addresses: http://127.0.0.1:18079/ # 请修改成你本地的 XXL-Job url
    executor:
      appname: pay-job-executor
      ip:
      port: 0
      logpath: /Users/yunai/logs/xxl-job/ # 请修改成你希望存放日志的目录
      logretentiondays: 1
    accessToken:
```

③ 配置项目中的每个作业

TODO 芋艿，需要完善

# 7. Elasticsearch

① 安装 Elasticsearch

* Windows ：参考 [《ElasticSearch 入门 第一篇：Windows 下安装ElasticSearch》](http://www.cnblogs.com/ljhdo/p/4887557.html)
* Mac ：参考 [《mac 安装 ElasticSearch 笔记》](https://www.jianshu.com/p/81b0b3a60c01)

因为需要中文分词，所以需要安装 [elasticsearch-analysis-ik](https://github.com/medcl/elasticsearch-analysis-ik) 插件。

② 修改项目中的 Elasticsearch 配置

在 IDEA 中，搜索`search-service-impl` 项目下的 `application.yaml` 文件，将 Elasticsearch 配置修改成连接你的。如下：

```YAML
# es
spring:
  data:
    elasticsearch:
      cluster-name: elasticsearch
      cluster-nodes: 180.167.213.26:9300 # 请修改成你本地的 Elasticsearch url
      repositories:
        enable: true
```

# 8. 启动后端项目

在 IDEA 中，右键运行每个 `XXXApplication.java` 。例如说，`admin` 项目是 AdminApplication 。

是否启动成功，请查看 IDEA 输出的日志。

具体的启动顺序，是：

* SystemApplication
* UserApplication
* ProductApplication
* PayApplication
    > 因为支付服务，涉及三方支付平台的配置。所以，需要艿艿后续提供简便的方案。TODO

* PromotionApplication
* OrderApplication
* SearchApplication

# 9. 启动前端项目

① 启动商城 H5 项目

在 `mobile-web` 项目下，执行 `npm start` 。

启动成功后，浏览器访问 <http://127.0.0.1:8000> 。

② 启动管理后台项目

在 `admin-web` 项目下，执行 `npm run start:no-mock` 。

启动成功后，浏览器访问 <http://127.0.0.1:8080> 。

# 10. 数据配置

TODO 芋艿

因为项目该配置完，是没有任何数据的。所以，需要操作对应的功能，添加数据。

# 233. 彩蛋

> 艿艿：本文暂时会写的比较简洁，如果有不懂的地方，请来[「交流群」](http://www.iocoder.cn/mall-user-group/?vip&gitee)，艿艿来帮你解决。
