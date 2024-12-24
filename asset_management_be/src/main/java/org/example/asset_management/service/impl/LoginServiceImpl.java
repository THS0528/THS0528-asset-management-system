package org.example.asset_management.service.impl;

import org.example.asset_management.dao.UserMapper;
import org.example.asset_management.entity.User;
import org.example.asset_management.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @author THS
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;

    public LoginServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @return org.example.asset_management.entity.User
     * @author THS.
     * @date 2024/4/15 19:59
     * @description: 登陆验证
     **/
    @Override
    public boolean login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
    /**
     * @author THS.
     * @param username 用户名
     * @return org.example.asset_management.entity.User
     * @date 2024/4/15 20:20
     * @description: 登陆业务逻辑实现类
     **/
    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
