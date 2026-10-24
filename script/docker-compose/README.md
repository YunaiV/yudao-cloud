# Docker Compose 部署（微服务版）

目标：用 Docker Compose V2 快速搭建一套可登录的体验环境，了解各组件之间的依赖关系。

包含：`gateway` 网关、`system-server`、`infra-server`、前端 Nginx、MySQL、Redis，以及可选的内置 Nacos。仅用于入门体验与测试，不是生产高可用方案；其他业务服务、SkyWalking、XXL-Job 等不在本编排中。

## 文件列表

```text
script/docker-compose
├── README.md
├── docker-compose.yml       <-- 编排文件，相对路径均相对于本目录
├── .env.example             <-- 环境变量示例，复制为 .env 后修改
├── .gitignore               <-- 忽略 .env、web/dist/
├── nginx
│   └── default.conf         <-- 静态资源、history 回退、/admin-api、/app-api、/infra/ws 反向代理到 gateway
└── web
    └── dist                 <-- 需自行放入前端打包产物（不随仓库提供）
```

| 服务 | 镜像 | 说明 |
| --- | --- | --- |
| `mysql` | `mysql:8.4` | 首次启动导入 `sql/mysql/ruoyi-vue-pro.sql`，数据卷 `yudao-cloud_mysql-data` |
| `redis` | `redis:7.4-alpine` | 开启密码与 AOF，数据卷 `yudao-cloud_redis-data` |
| `nacos` | `nacos/nacos-server:v3.0.3` | 可选（profile `nacos`），单机 + 嵌入式存储，数据卷 `yudao-cloud_nacos-data` |
| `gateway` | `yudao-gateway:${IMAGE_TAG}` | 由 `yudao-gateway/Dockerfile` 构建，端口 48080，Nacos 服务名 `gateway-server` |
| `system-server` | `yudao-module-system-server:${IMAGE_TAG}` | 由模块 Dockerfile 构建，端口 48081，Nacos 服务名 `system-server` |
| `infra-server` | `yudao-module-infra-server:${IMAGE_TAG}` | 由模块 Dockerfile 构建，端口 48082，Nacos 服务名 `infra-server` |
| `web` | `nginx:1.28-alpine` | 对宿主机开放 `${WEB_PORT}`（默认 8080） |

请求链路：浏览器 → `web`（Nginx）→ `gateway` → 通过 Nacos 发现 `system-server`、`infra-server`。`gateway` 只依赖 Nacos，不连接 MySQL、Redis。

容器之间通过服务名访问，服务注册到 Nacos 的是容器在 Compose 网络中的 IP，网关可以直接访问。MySQL、Redis、Nacos、网关默认不映射到宿主机，不会与本机已有的 3306、6379、8848、48080 冲突；需要时取消 `docker-compose.yml` 中对应 `ports` 的注释。

## 前置条件

- Docker Engine + Docker Compose V2.20.2 及以上（`docker compose version` 可查看）。Compose V2.20.2 起支持 `depends_on.required`，清空 `COMPOSE_PROFILES` 复用已部署的 Nacos 时依赖此功能；V2.20.0 在这种情况下启动会报 `no such service: nacos`
- Docker 实际剩余可用内存建议 4GB 以上：Docker Desktop 以分配给 Docker 虚拟机的内存为准（不是宿主机总内存），并扣除已运行容器的占用；内存不足时容器可能被 OOM 终止，可用 `docker info` 查看 `Total Memory`、`docker stats` 查看已有占用
- JDK 17、Maven（与源码根 `pom.xml` 的 `java.version` 一致）
- 已按前端项目的部署说明完成打包

## 1. 打包后端

在**源码根目录**执行：

```shell
mvn clean package -pl yudao-gateway,yudao-module-system/yudao-module-system-server,yudao-module-infra/yudao-module-infra-server -am -Dmaven.test.skip=true
```

产物为 `yudao-gateway/target/yudao-gateway.jar`、`yudao-module-system/yudao-module-system-server/target/yudao-module-system-server.jar`、`yudao-module-infra/yudao-module-infra-server/target/yudao-module-infra-server.jar`，`docker compose build` 时由各模块的 Dockerfile 复制进镜像。

## 2. 准备前端产物

前端是独立仓库，这里不构建前端源码。打包前确认前端生产环境配置：

- 浏览器访问地址使用 Nginx 的同源地址，例如 `http://localhost:8080`，不要写 `gateway`、`127.0.0.1:48080` 等容器名或后端端口
- API 路径为 `/admin-api`：`yudao-ui-admin-vue3` 为 `.env.prod` 的 `VITE_BASE_URL` + `VITE_API_URL`；`yudao-ui-admin-vben` 为 `apps/web-*/.env.production` 的 `VITE_BASE_URL`、`VITE_GLOB_API_URL`
- 前端 `VITE_APP_CAPTCHA_ENABLE` 与 `.env` 中的 `YUDAO_CAPTCHA_ENABLE` 一致

将打包产物目录中的内容（包含 `index.html`）复制到 `script/docker-compose/web/dist/`，在**源码根目录**执行：

```shell
mkdir -p script/docker-compose/web/dist
cp -R /path/to/前端打包产物目录/. script/docker-compose/web/dist/
```

例如 `yudao-ui-admin-vue3` 执行 `pnpm build:prod` 后为 `dist-prod`；`yudao-ui-admin-vben` 执行 `pnpm build:antd` 后为 `apps/web-antd/dist`。

## 3. 配置环境变量

```shell
cd script/docker-compose
cp .env.example .env
```

按需修改 `.env`，至少替换 `MYSQL_ROOT_PASSWORD`、`MYSQL_PASSWORD`、`REDIS_PASSWORD` 三个示例密码；使用内置 Nacos 时同时替换 `NACOS_AUTH_TOKEN`、`NACOS_AUTH_IDENTITY_VALUE`。

| 变量 | 默认值 | 说明 |
| --- | --- | --- |
| `WEB_PORT` | `8080` | 前端访问端口 |
| `IMAGE_TAG` | `local` | 后端镜像 tag |
| `MYSQL_DATABASE` | `ruoyi-vue-pro` | 数据库名 |
| `MYSQL_ROOT_PASSWORD` | 示例值 | MySQL root 密码，仅供初始化与运维使用 |
| `MYSQL_USER` / `MYSQL_PASSWORD` | `yudao` / 示例值 | 应用使用的账号，只拥有 `MYSQL_DATABASE` 库权限 |
| `REDIS_PASSWORD` | 示例值 | Redis 密码 |
| `SPRING_PROFILES_ACTIVE` | `dev` | 后端 profile，同时决定 Nacos Data ID 后缀 |
| `JAVA_OPTS` | `-Xms512m -Xmx512m` | JVM 参数，三个 Java 服务共用 |
| `YUDAO_CAPTCHA_ENABLE` | `true` | 是否开启登录验证码 |
| `COMPOSE_PROFILES` | `nacos` | 为 `nacos` 时启动内置 Nacos；复用已部署的 Nacos 时清空 |
| `NACOS_SERVER_ADDR` | `nacos:8848` | Nacos 地址，必须是**容器内**可访问的地址 |
| `NACOS_NAMESPACE` | `public` | 命名空间 ID，注册中心与配置中心共用 |
| `NACOS_GROUP` | `DEFAULT_GROUP` | 分组，注册中心与配置中心共用 |
| `NACOS_USERNAME` / `NACOS_PASSWORD` | 空 | Nacos 开启鉴权时填写 |
| `NACOS_AUTH_TOKEN` / `NACOS_AUTH_IDENTITY_KEY` / `NACOS_AUTH_IDENTITY_VALUE` | 示例值 | 内置 Nacos 3.x 启动必填参数 |

`docker-compose.yml` 通过环境变量覆盖 `application-dev.yaml` 中的以下配置，其余配置仍使用 `application.yaml`、`application-dev.yaml`：

- 数据库主库 `master`、从库 `slave`，Redis：指向 Compose 内的 `mysql`、`redis`
- Nacos 地址、账号、命名空间、分组：`spring.cloud.nacos.*`
- `xxl.job.enabled=false`：本编排不提供 XXL-Job 调度中心
- `yudao.demo=false`：`application-dev.yaml` 默认开启的演示模式会禁止写操作

## 4. Nacos 说明

**内置 Nacos（默认）**：`COMPOSE_PROFILES=nacos`，Java 服务等待 Nacos 的 8848、9848 端口可连接后再启动。内置 Nacos 未开启客户端访问鉴权，且端口默认不对宿主机开放，只适合本机体验。首次访问控制台（取消 `ports` 注释后打开 http://127.0.0.1:18080）需要初始化 `nacos` 用户的密码。

**复用已部署的 Nacos**：建议使用 Nacos 3.x（与项目的 `nacos-client` 3.0.3 对应），在 `.env` 中：

1. 清空 `COMPOSE_PROFILES`（`COMPOSE_PROFILES=`），不再启动内置 Nacos。
2. `NACOS_SERVER_ADDR` 填容器内可访问的地址。容器内的 `127.0.0.1` 指向容器自身；Nacos 部署在本机时使用 `host.docker.internal:8848`（已为 Java 服务配置 `host-gateway`）。客户端同时使用 HTTP 端口和 gRPC 端口（HTTP 端口 + 1000，默认 9848），两个端口都要放通。
3. `NACOS_NAMESPACE` 填命名空间 **ID**，该命名空间需已存在；`NACOS_GROUP` 填分组。
4. Nacos 开启鉴权时，填写 `NACOS_USERNAME`、`NACOS_PASSWORD`。
5. 建议使用独立的命名空间或分组。若同一命名空间、分组下还有本机 IDE 启动的同名服务，网关可能把请求转发到容器无法访问的实例。

**配置优先级**：各服务会加载 Nacos 中命名空间 `NACOS_NAMESPACE`、分组 `NACOS_GROUP` 下的 Data ID `${spring.application.name}-${SPRING_PROFILES_ACTIVE}.yaml`（例如 `gateway-server-dev.yaml`、`system-server-dev.yaml`、`infra-server-dev.yaml`），不存在时忽略。优先级从高到低为：`docker-compose.yml` 中的环境变量 > Nacos Data ID > jar 内的 `application-dev.yaml` > `application.yaml`。因此 Data ID 中的数据库、Redis、Nacos 配置会被上述环境变量覆盖，其他配置项会覆盖 jar 内配置。

## 5. 构建并启动

以下命令均在 `script/docker-compose` 目录执行：

```shell
# 检查配置（变量缺失会直接报错）
docker compose config

# 构建后端镜像并启动
docker compose up -d --build

# 查看状态与日志
docker compose ps
docker compose logs -f gateway system-server infra-server
```

首次启动时 MySQL 需要导入 SQL，`system-server`、`infra-server` 会等到 MySQL、Redis（以及内置 Nacos）健康检查通过后再启动。三个服务日志分别出现 `Started GatewayServerApplication`、`Started SystemServerApplication`、`Started InfraServerApplication`，并在 Nacos 中注册后访问：

- 管理后台：http://localhost:8080 （`WEB_PORT` 修改后对应调整）

服务刚启动时网关可能短暂返回 503（尚未从 Nacos 拉取到实例），稍等后刷新即可。

## 6. 更新

```shell
# 后端：回到源码根目录重新打包，再回到本目录重建、重启
cd ../..
mvn clean package -pl yudao-gateway,yudao-module-system/yudao-module-system-server,yudao-module-infra/yudao-module-infra-server -am -Dmaven.test.skip=true
cd script/docker-compose
docker compose up -d --build gateway system-server infra-server

# 前端：清空 web/dist/ 内的文件（保留 dist 目录本身）后复制新产物，再重启 web；修改 nginx/default.conf 后同样执行
rm -rf web/dist/*
cp -R /path/to/前端打包产物目录/. web/dist/
docker compose restart web
```

只更新某个服务时，打包与 `docker compose up -d --build` 只写对应模块即可。如需回滚，可在打包前修改 `.env` 的 `IMAGE_TAG`，保留旧 tag 的镜像。

## 7. 停止与数据

```shell
# 停止并删除容器，保留数据卷
docker compose down
```

**不要执行 `docker compose down -v`**，它会删除 `yudao-cloud_mysql-data`、`yudao-cloud_redis-data`、`yudao-cloud_nacos-data` 数据卷，数据库内容将全部丢失。

## 数据库初始化说明

- SQL 只在 `mysql-data` 数据卷为空（首次启动）时执行一次；之后修改 SQL 文件、`.env` 中的 `MYSQL_*` 账号密码，都不会作用于已有数据库。
- 首次导入中途失败（例如容器被强制停止），数据卷会处于半初始化状态，需要确认数据可丢弃后执行 `docker compose down`、`docker volume rm yudao-cloud_mysql-data` 再重新启动。
- 已有数据卷升级到新版本时，SQL 不会自动执行。请先备份，再手动执行对应的升级 SQL，例如：

```shell
docker compose exec -T mysql sh -c 'exec mysql -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE"' < /path/to/upgrade.sql
```

- 使用外部 MySQL 时：自行导入 `sql/mysql/ruoyi-vue-pro.sql`，删除 `docker-compose.yml` 中的 `mysql` 服务及各服务对它的 `depends_on`，并把 `x-app-env` 中的 `SPRING_DATASOURCE_DYNAMIC_DATASOURCE_MASTER_*`、`SLAVE_*` 改为外部地址；容器内的 `127.0.0.1` 指向容器自身，不是宿主机。

## 其他分支同步替换点

本目录按 JDK 17 + Spring Boot 3.x 分支编写。同步到其他分支时，只需替换：

| 分支 | 后端 Dockerfile 基础镜像 | Redis 环境变量前缀 |
| --- | --- | --- |
| JDK 8 + Spring Boot 2.x | `eclipse-temurin:8-jre` | `SPRING_REDIS_*`（`spring.redis.*`） |
| JDK 17 + Spring Boot 3.x | `eclipse-temurin:17-jre` | `SPRING_DATA_REDIS_*`（`spring.data.redis.*`） |
| JDK 25 + Spring Boot 4.x | `eclipse-temurin:25-jre` | `SPRING_DATA_REDIS_*`（`spring.data.redis.*`） |

并核对该分支各服务 `application-*.yaml` 的数据源、Redis、Nacos 配置项，`spring-cloud-alibaba` 对应的 `nacos-client` 版本（决定 Nacos 镜像版本），以及 `sql/mysql` 下的 SQL 文件名。
