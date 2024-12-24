package org.example.asset_management.controller;

import org.example.asset_management.entity.User;
import org.example.asset_management.service.impl.LoginServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/15 18:29:56
 * @description: 登录控制层
 */
@RestController
public class LoginController {
    public final LoginServiceImpl loginService;

    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    //前端axios发送post请求默认类型为json，所以这里用实体类接收
    public String login(@RequestBody User user)  {
        System.out.println("请求");
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(
                "username:" + username + " password:" + password
        );
        if (!loginService.login(username,password)) {
            return "{\"code\":0,\"msg\":\"用户名或密码错误\"}";
        }
        return "{\"code\":1,\"msg\":\"登陆成功\",\"data\":" + loginService.getUser(username).toJson() +"}";

    }
}
