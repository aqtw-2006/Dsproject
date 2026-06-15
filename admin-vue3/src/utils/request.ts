import axios from 'axios'
import router from '@/router'

const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
  timeout: 8000
})

// 请求拦截器：自动带 token
request.interceptors.request.use(config => {
  const token = localStorage.getItem('token')
  if (token) config.headers.Authorization = `Bearer ${token}`
  return config
}, error => Promise.reject(error))

// 响应拦截器
request.interceptors.response.use(response => {
  const res = response.data
  if (res.code === 200) return res.data
  if (res.code === 401) {
    // token 过期或未登录
    localStorage.removeItem('token')
    router.push('/login')
    return Promise.reject(new Error(res.message || 'Unauthorized'))
  }
  return Promise.reject(new Error(res.message || 'Error'))
}, error => {
  if (error.response?.status === 401) {
    localStorage.removeItem('token')
    router.push('/login')
  }
  return Promise.reject(error)
})

export default request
