import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({ token: uni.getStorageSync('token') || '', userInfo: null as any }),
  actions: {
    setToken(t:string){ this.token = t; uni.setStorageSync('token', t) },
    logout(){ this.token=''; this.userInfo=null; uni.removeStorageSync('token') }
  }
})
