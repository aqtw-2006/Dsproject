<template>
  <el-pagination :current-page.sync="page" :page-size="pageSize" :total="total" @current-change="onChange" />
</template>

<script setup lang="ts">
import { ref } from 'vue'
const props = defineProps({ total: { type: Number, default: 0 } })
const emit = defineEmits(['change'])
const page = ref(1)
const pageSize = ref(10)
const onChange = (val:number) => { emit('change', { page: val, pageSize: pageSize.value }) }
</script>
