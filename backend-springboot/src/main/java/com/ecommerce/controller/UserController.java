package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import com.ecommerce.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        String phone = body.get("phone");
        return userService.register(username, password, email, phone);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        return userService.login(username, password);
    }

    @GetMapping("/info")
    public Result<?> info(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return userService.getUserInfo(userId);
    }

    @PostMapping("/changePassword")
    public Result<?> changePassword(HttpServletRequest request, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Integer userId = (Integer) request.getAttribute("userId");
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    @PostMapping("/refreshToken")
    public Result<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        return userService.refreshToken(token);
    }
}
