<template>
  <el-menu default-active="1" class="el-menu-vertical-demo" router>
    <el-menu-item index="/dashboard">仪表盘</el-menu-item>
    <el-sub-menu index="/product">
      <template #title>商品管理</template>
      <el-menu-item index="/product/list">商品列表</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup>
</script>

<style scoped>
.el-menu-vertical-demo{width:200px}
</style>
