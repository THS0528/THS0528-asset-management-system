package org.example.asset_management.service;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/16 22:44:44
 * @description:
 */
public interface IndexService {
    /**
     * @author THS.
     * @param userId 用户id
     * @return void
     * @date 2024/4/16 22:46
     * @description: 获取用户信息（用户名，角色，权限）
     **/
    String getUser(int userId);
}
