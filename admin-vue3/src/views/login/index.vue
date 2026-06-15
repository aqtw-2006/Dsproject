<template>
  <div class="login-page">
    <el-card class="login-card">
      <el-form :model="form" @submit.prevent="onSubmit">
        <el-form-item>
          <el-input v-model="form.username" placeholder="用户名" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" type="password" placeholder="密码" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { apiLogin } from '@/api/user'
import { useRouter } from 'vue-router'

const router = useRouter()
const form = reactive({ username: '', password: '' })

const onSubmit = async () => {
  try {
    const res: any = await apiLogin({ username: form.username, password: form.password })
    // res expected contains token and user info
    const token = (res as any).token || res?.token
    if (token) {
      localStorage.setItem('token', token)
      router.push('/dashboard')
    }
  } catch (err) {
    console.error(err)
  }
}
</script>

<style scoped>
.login-page { display:flex; justify-content:center; align-items:center; height:100vh }
.login-card { width:360px }
</style>
