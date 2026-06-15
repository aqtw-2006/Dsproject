package com.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.entity.User;
import com.ecommerce.exception.BizException;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.service.UserService;
import com.ecommerce.util.JwtUtil;
import com.ecommerce.util.MD5Util;
import com.ecommerce.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result<?> register(String username, String password, String email, String phone) {
        User exist = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (exist != null) {
            throw new BizException(400, "用户名已存在");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.encrypt(password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setUserType(0);
        user.setStatus(0);
        user.setNickname(username);
        if (userMapper.insert(user) > 0) {
            return Result.success("注册成功");
        }
        throw new BizException(500, "注册失败");
    }

    @Override
    public Result<?> login(String username, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            throw new BizException(401, "用户不存在");
        }
        if (!MD5Util.verify(password, user.getPassword())) {
            throw new BizException(401, "密码错误");
        }
        if (user.getStatus() != null && user.getStatus() == 1) {
            throw new BizException(401, "用户已禁用");
        }
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("userId", user.getId());
        data.put("username", user.getUsername());
        return Result.success("登录成功", data);
    }

    @Override
    public Result<?> getUserInfo(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BizException(404, "用户不存在");
        }
        return Result.success(user);
    }

    @Override
    public Result<?> changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BizException(404, "用户不存在");
        }
        if (!MD5Util.verify(oldPassword, user.getPassword())) {
            throw new BizException(401, "旧密码错误");
        }
        user.setPassword(MD5Util.encrypt(newPassword));
        if (userMapper.updateById(user) > 0) {
            return Result.success("密码修改成功");
        }
        throw new BizException(500, "密码修改失败");
    }

    @Override
    public Result<?> refreshToken(String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new BizException(401, "Token 无效");
        }
        Integer userId = jwtUtil.getUserIdFromToken(token);
        String username = jwtUtil.getUsernameFromToken(token);
        String newToken = jwtUtil.generateToken(userId, username);
        Map<String, Object> data = new HashMap<>();
        data.put("token", newToken);
        return Result.success(data);
    }
}
