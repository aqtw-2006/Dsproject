# mobile-uni 模块说明

移动端基于 uni-app（Vue3 + uView），支持 H5 和微信小程序。

注意事项：
- API 地址通过 manifest.json 的 h5.domain 或运行时环境变量 API_BASE 控制，后端部署时请设置为后端实际地址。
- 请求封装在 src/utils/request.ts，会自动携带 token（从 uni storage）；收到 401 会跳转到登录页。

启动：

npm install
npm run dev    # 开发模式（watch）
npm run h5     # 生成H5
npm run mp-weixin  # 生成微信小程序
