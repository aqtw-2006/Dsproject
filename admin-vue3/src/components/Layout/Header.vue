<template>
  <div class="header">
    <div class="logo">E-commerce Admin</div>
    <div class="actions">
      <el-button type="text" @click="logout">退出</el-button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
const logout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<style scoped>
.header{display:flex;justify-content:space-between;padding:12px 20px;background:#fff}
</style>
