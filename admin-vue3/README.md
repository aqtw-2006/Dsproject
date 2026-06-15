# admin-vue3 模块说明

- API 基础地址通过环境变量 VITE_API_BASE_URL 配置（.env.development 示例已提供）。
- 请求封装在 src/utils/request.ts，自动携带 token，并在 401 时清除 token 并跳转登录。

启动：

npm install
npm run dev
