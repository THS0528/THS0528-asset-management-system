package org.example.asset_management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.asset_management.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author THS
 */
@Mapper
public interface UserMapper {

    /**
     * @author THS.
     * @param username 用户名
     * @return org.example.asset_management.entity.User
     * @date 2024/4/15 19:57
     * @description: 通过用户名查找用户
     **/
    @Select("SELECT * FROM user WHERE user.USER_NAME = #{username}")
    User getUserByUsername(String username);

    /**
     * @author THS.
     * @param username 用户名
     * @return java.lang.String
     * @date 2024/4/15 20:23
     * @description: 通过id查找用户信息
     **/
    @Select("SELECT * FROM user WHERE user.USER_NAME = #{username}")
    User getUser(String username);
    /**
     * @author THS.
     * @param userId 用户id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/16 23:02
     * @description: 根据用户id查询用户名，用户角色
     **/
    @Select("SELECT us.USER_NAME, ro.ROLE_NAME, ro.ROLE_ID  FROM user us JOIN user_role ur on us.USER_ID = ur.USER_ID JOIN role ro ON ur.ROLE_ID = ro.ROLE_ID WHERE us.USER_ID = #{userId}")
    List<Map<String, Object>> getUserList(int userId);
    /**
     * @author THS.
     * @param roleId 角色id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/16 23:11
     * @description: 根据角色id查询角色权限
     **/
    @Select("SELECT pe.PERMISSION_NAME,pe.PERMISSION_PATH FROM permission pe JOIN role_permission rp ON pe.PERMISSION_ID = rp.PERMISSION_ID WHERE rp.ROLE_ID = #{roleId}")
    List<Map<String, Object>> getPermission(int roleId);
}
