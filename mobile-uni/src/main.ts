import { createSSRApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import uView from 'uview-plus'

export function createApp() {
  const app = createSSRApp(App)
  const pinia = createPinia()
  app.use(pinia)
  app.use(uView)
  return { app, pinia }
}
