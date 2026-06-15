import { createRouter, createWebHistory } from 'vue-router'
import Dashboard from '@/views/dashboard/index.vue'
import Login from '@/views/login/index.vue'

const routes = [
  { path: '/login', name: 'Login', component: Login },
  { path: '/', name: 'Root', redirect: '/dashboard' },
  { path: '/dashboard', name: 'Dashboard', component: Dashboard, meta: { requiresAuth: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.meta.requiresAuth && !token) {
    return next('/login')
  }
  next()
})

export default router
