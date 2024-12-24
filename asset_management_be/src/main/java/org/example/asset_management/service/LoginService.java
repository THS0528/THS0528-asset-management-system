package org.example.asset_management.service;

import org.example.asset_management.entity.User;

/**
 * @author THS
 * @version 1.0
 * @date 2024/4/15 15:01
 * @description: 登录业务层
 */
public interface LoginService {
    /**
     * @author THS.
     * @param username 用户名
     * @param password 密码
     * @return org.example.asset_management.entity.User
     * @date 2024/4/15 19:59
     * @description: 登陆验证
     **/
    boolean login(String username, String password);
    User getUser(String username);

}
