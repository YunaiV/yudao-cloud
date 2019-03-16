
# 安装依赖包

cd admin-web

cnpm install

# 开始构建

#npm run build

# 创建 app 运行目录

rm -rf app

mkdir app

# 复制配置文件

cp -rf config/server app/

# npm 构建项目

npm run build:admin-web

# 复制文件到 server 努力

cp -rf dist app/server

# 复制文件到 project 目录执行

if [ ! -d "/work2/project/admin-web/server/dist" ]; then
  rm -rf /work2/project/admin-web/server/dist
fi

cp -rf app/server /work2/project/admin-web

# 安装 server 依赖项
cd /work2/project/admin-web/server

cnpm install

# 启动服务
pm2 restart pm2.json
