const API_BASE = process.env.API_BASE || 'http://localhost:8080/api'

export function request(options:any) {
  const token = uni.getStorageSync('token') || ''
  return new Promise((resolve, reject) => {
    uni.request({
      url: API_BASE + options.url,
      method: options.method || 'GET',
      data: options.data || {},
      header: { 'Content-Type': 'application/json', 'Authorization': token ? `Bearer ${token}` : '' },
      success: (res:any) => {
        const body = res.data
        if (!body) return reject(new Error('Empty response'))
        if (body.code === 200) return resolve(body.data)
        if (body.code === 401) {
          uni.removeStorageSync('token')
          uni.navigateTo({ url: '/pages/login/index' })
          return reject(new Error(body.message || 'Unauthorized'))
        }
        uni.showToast({ title: body.message || '请求失败', icon: 'none' })
        return reject(new Error(body.message || 'Error'))
      },
      fail: (err) => {
        uni.showToast({ title: '请求失败', icon: 'none' })
        reject(err)
      }
    })
  })
}

export default {
  get(url:string, data?:any){ return request({ url, method: 'GET', data }) },
  post(url:string, data?:any){ return request({ url, method: 'POST', data }) },
  put(url:string, data?:any){ return request({ url, method: 'PUT', data }) },
  delete(url:string, data?:any){ return request({ url, method: 'DELETE', data }) }
}
