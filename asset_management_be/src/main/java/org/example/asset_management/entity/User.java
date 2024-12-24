package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS
 */
@Data
public class User {
    private Integer userId;
    private String username;
    private String password;

    public String toJson() {
        return "{\"userId\":\"" + userId +
                "\",\"username\":\"" + username +
                "\",\"password\":\"" + password + "\"}";
    }
}

