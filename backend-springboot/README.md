# 后端模块说明

本目录为后端 Spring Boot 服务（backend-springboot）。

敏感配置说明：
- 所有敏感信息（数据库 URL/用户名/密码、Redis、JWT secret）在 `src/main/resources/application.yml` 中使用占位符（可通过环境变量覆盖）。
- 请在部署前设置对应环境变量或修改 `application.yml` 中的默认占位值。

常用命令：

# 编译
mvn clean install

# 运行
mvn spring-boot:run

数据库初始化：

mysql -u root -p < src/main/resources/sql/init.sql
