# 部署说明

## 环境要求

- Java 17+
- Node.js 18+
- MySQL 8.0+
- Redis 6.0+
- Maven 3.8+
- npm 9+

## 一键启动脚本

### Linux/Mac - startup.sh

```bash
#!/bin/bash

echo "========== 电商平台启动脚本 =========="

# 后端启动
echo "1. 启动后端服务..."
cd backend-springboot
mvn clean install
mvn spring-boot:run &
BACKEND_PID=$!
echo "后端服务已启动 (PID: $BACKEND_PID)"

sleep 5

# PC管理后台
echo "2. 启动PC管理后台..."
cd ../admin-vue3
npm install
npm run dev &
ADMIN_PID=$!
echo "PC后台已启动 (PID: $ADMIN_PID)"

sleep 3

# 移动端
echo "3. 启动移动端..."
cd ../mobile-uni
npm install
npm run dev &
MOBILE_PID=$!
echo "移动端已启动 (PID: $MOBILE_PID)"

echo "========== 所有服务已启动 =========="
echo "后端地址: http://localhost:8080/api"
echo "PC后台: http://localhost:5173"
echo "移动端: http://localhost:8081"
echo ""
echo "PID列表:"
echo "后端: $BACKEND_PID"
echo "PC后台: $ADMIN_PID"
echo "移动端: $MOBILE_PID"
```

## 详细步骤

### 第一步：准备环境

```bash
# 检查 Java 版本
java -version

# 检查 Node 版本
node -v
npm -v

# 启动 MySQL
mysql --version

# 启动 Redis
redis-cli ping
```

### 第二步：初始化数据库

```bash
# 连接MySQL
mysql -u root -p

# 在MySQL命令行执行
CREATE DATABASE ecommerce_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 导入初始化脚本
mysql -u root -p ecommerce_db < backend-springboot/src/main/resources/sql/init.sql
```

### 第三步：启动后端服务

```bash
cd backend-springboot

# 方法一：使用 Maven
mvn clean install
mvn spring-boot:run

# 方法二：生成 JAR 并运行
mvn clean package
java -jar target/backend-springboot-1.0.0.jar
```

验证后端启动：访问 http://localhost:8080/api/user/info

### 第四步：启动 PC 管理后台

```bash
cd admin-vue3
npm install
npm run dev
```

访问 http://localhost:5173

**默认登录凭证：**
- 用户名: admin
- 密码: admin

### 第五步：启动移动端

```bash
cd mobile-uni
npm install
npm run dev
```

## Docker 部署

### 启动 Docker 容器

```bash
docker-compose up -d
docker-compose down
```

## 常见问题排查

### 启动失败

1. **检查端口占用**
   ```bash
   lsof -i :8080
   ```

2. **检查数据库连接**
   ```bash
   mysql -h localhost -u root -p -e "SELECT 1;"
   ```

3. **查看日志文件**
   ```bash
   tail -100 /var/log/ecommerce/app.log
   ```
