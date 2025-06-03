## Docker 部署教程

1. /xxx/xxx/config目录下创建 application.yml(mj配置项)、banned-words.txt(可选，覆盖默认的敏感词文件)；参考src/main/resources下的文件
2. 启动容器，映射config目录
```shell
docker run -d --name midjourney-proxy \
 -p 8080:8080 \
 -v /xxx/xxx/config:/home/spring/config \
 novicezk/midjourney-proxy:2.6.3
```
3. 访问 `http://ip:port/mj` 查看API文档

附: 不映射config目录方式，直接在启动命令中设置参数
```shell
docker run -d --name midjourney-proxy \
 -p 8080:8080 \
 -e mj.discord.guild-id=xxx \
 -e mj.discord.channel-id=xxx \
 -e mj.discord.user-token=xxx \
 novicezk/midjourney-proxy:2.6.3
```
