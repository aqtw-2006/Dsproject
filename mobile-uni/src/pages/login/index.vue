<template>
  <view class="login">
    <input v-model="username" placeholder="用户名" />
    <input v-model="password" type="password" placeholder="密码" />
    <button @click="onLogin">登录</button>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { login } from '@/api/user'
import { useUserStore } from '@/store/modules/user'
const username = ref('')
const password = ref('')
const store = useUserStore()
const onLogin = async ()=>{
  try{
    const res:any = await login({ username: username.value, password: password.value })
    const token = res.token || res?.token
    if(token){ store.setToken(token); uni.switchTab({ url: '/pages/index/index' }) }
  }catch(e){ console.error(e) }
}
</script>
