
# 安装依赖包

cd mobile-web

npm install

# 开始构建

#npm run build

# 创建 app 运行目录

rm -rf app

mkdir app

# 复制配置文件

cp -rf server app/

# npm 构建项目

npm run build

# 复制文件到 server 努力

cp -rf dist app/server

# 复制文件到 project 目录执行

if [ ! -d "/work2/project/mobile-web/server/dist" ]; then
  mkdir -pv /work2/project/mobile-web/server/dist
else
  rm -rf /work2/project/mobile-web/server/dist
fi

cp -rf app/server /work2/project/mobile-web

# 安装 server 依赖项
cd /work2/project/mobile-web/server

npm install

# 启动服务
pm2 restart pm2.json
