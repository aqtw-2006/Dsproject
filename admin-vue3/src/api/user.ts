import request from '@/utils/request'

export const apiLogin = (data: {username:string,password:string}) => {
  return request.post('/user/login', data)
}

export const apiGetUserInfo = () => {
  return request.get('/user/info')
}
