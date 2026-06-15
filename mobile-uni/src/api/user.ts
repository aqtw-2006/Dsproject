import request from '@/utils/request'
export const login = (data:any) => request.post('/user/login', data)
export const register = (data:any) => request.post('/user/register', data)
export const getUserInfo = () => request.get('/user/info')
