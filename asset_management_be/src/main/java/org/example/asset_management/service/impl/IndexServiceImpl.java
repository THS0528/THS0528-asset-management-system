package org.example.asset_management.service.impl;

import com.google.gson.Gson;
import org.example.asset_management.dao.UserMapper;
import org.example.asset_management.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/16 22:47:44
 * @description: 主页业务实现类
 */
@Service
public class IndexServiceImpl implements IndexService {
    private final UserMapper userMapper;

    public IndexServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * @param userId 用户id
     * @return void
     * @author THS.
     * @date 2024/4/16 22:46
     * @description: 获取用户信息（用户名，角色，权限）
     **/
    @Override
    public String getUser(int userId) {
        //查询用户名，角色
        List<Map<String, Object>> userList = userMapper.getUserList(userId);
        int roleId = (int)userList.getFirst().get("ROLE_ID");
        //查询权限
        List<Map<String, Object>> permission = userMapper.getPermission(roleId);
        //permission转成json格式
        Gson gson = new Gson();
        String permissionJson = gson.toJson(permission);
        //以json格式返回用户信息和权限
        return "{\"user\":\"" + userList.getFirst().get("USER_NAME") + "\"," +
                "\"role\":\"" + userList.getFirst().get("ROLE_NAME") + "\"," +
                "\"permission\":" + permissionJson + "}";
    }
}
