<template>
  <view class="home">
    <view class="banner">欢迎来到电商平台</view>
    <view class="list">
      <view v-for="p in products" :key="p.id" @click="toDetail(p.id)" class="product">
        <image :src="p.mainImage" mode="aspectFill" />
        <view class="title">{{ p.productName }}</view>
        <view class="price">¥{{ p.price }}</view>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getProducts } from '@/api/product'
import { useRouter } from 'vue-router'
const products = ref([])
const router = useRouter()
onMounted(async ()=>{ products.value = await getProducts() })
const toDetail = (id:number)=>{ uni.navigateTo({ url: `/pages/home/productDetail?id=${id}` }) }
</script>

<style scoped>
.home{padding:10px}
.product{display:flex;flex-direction:column;margin-bottom:12px}
.title{font-size:14px}
.price{color:#ff6b6b}
</style>
