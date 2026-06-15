import request from '@/utils/request'
export const getProducts = (params?:any) => request.get('/product/list', params)
export const getProduct = (id:number) => request.get(`/product/${id}`)
