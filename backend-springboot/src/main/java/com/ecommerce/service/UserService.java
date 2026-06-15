package com.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.entity.User;
import com.ecommerce.util.Result;

public interface UserService extends IService<User> {
    Result<?> register(String username, String password, String email, String phone);
    Result<?> login(String username, String password);
    Result<?> getUserInfo(Integer userId);
    Result<?> changePassword(Integer userId, String oldPassword, String newPassword);
    Result<?> refreshToken(String token);
}
