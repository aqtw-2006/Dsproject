# 电商平台 - SpringBoot3 + Vue3 + uni-app

一套完整的前后端分离电商平台解决方案。

## 项目特性

- ✅ 后端：SpringBoot 3.3 + MyBatis-Plus + MySQL8 + Redis
- ✅ PC管理后台：Vue3 + Vite + Element Plus + Pinia
- ✅ 移动端：uni-app（Vue3版）+ uView UI，支持H5和微信小程序双端
- ✅ JWT 认证 + Token 刷新机制
- ✅ 全局异常处理 + 统一返回结果
- ✅ 跨域配置 + 请求拦截器
- ✅ 完整的电商业务模块

## 项目结构

```
ecommerce-platform/
├── backend-springboot/   # 后端服务
├── admin-vue3/          # PC管理后台
├── mobile-uni/          # 移动端应用
├── README.md            # 项目说明
└── DEPLOYMENT.md        # 部署说明
```

## 快速启动

### 1. 后端启动

```bash
cd backend-springboot

# 导入 MySQL 数据库脚本
mysql -u root -p < src/main/resources/sql/init.sql

# 编译和运行
mvn clean install
mvn spring-boot:run
```

后端服务将运行在 `http://localhost:8080/api`

### 2. PC 管理后台

```bash
cd admin-vue3

# 安装依赖
npm install

# 开发模式
npm run dev

# 生产打包
npm run build
```

访问 `http://localhost:5173`

### 3. 移动端

```bash
cd mobile-uni

# 安装依赖
npm install

# H5 开发
npm run dev

# 编译为 H5
npm run h5

# 编译为微信小程序
npm run mp-weixin
```

## 核心业务模块

### 用户模块
- 注册/登录（JWT）
- 个人信息管理
- 收货地址管理
- 密码修改

### 商品模块
- 商品分类管理
- 商品CRUD
- 库存管理

### 购物车模块
- 添加/删除商品
- 修改数量
- 全选/取消全选

### 订单模块
- 订单创建
- 订单支付（模拟）
- 订单列表查询
- 订单状态管理

### 优惠券模块
- 优惠券领取
- 优惠券核销

### 后台管理
- 管理员登录
- 用户管理
- 商品管理
- 订单管理

## 技术栈详细

| 层级 | 技术 | 版本 |
|------|------|------|
| 后端框架 | Spring Boot | 3.3.0 |
| ORM | MyBatis Plus | 3.5.5 |
| 数据库 | MySQL | 8.0.33 |
| 缓存 | Redis | - |
| 认证 | JWT | 0.12.3 |
| PC端 | Vue | 3.4.0 |
| PC构建 | Vite | 5.0.8 |
| UI库 | Element Plus | 2.4.4 |
| 状态管理 | Pinia | 2.1.6 |
| 移动端 | uni-app | - |
| 移动端UI | uView | 3.1.8 |

## 部署指南

详见 [DEPLOYMENT.md](./DEPLOYMENT.md)

## 许可证

MIT License
