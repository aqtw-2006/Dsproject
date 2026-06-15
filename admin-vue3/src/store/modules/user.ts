import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '' ,
    userInfo: null as any
  }),
  actions: {
    setToken(t: string) {
      this.token = t
      localStorage.setItem('token', t)
    },
    logout() {
      this.token = ''
      this.userInfo = null
      localStorage.removeItem('token')
    }
  }
})
