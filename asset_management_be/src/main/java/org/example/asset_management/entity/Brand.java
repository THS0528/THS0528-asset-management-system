package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/18 21:37:22
 * @description: 品牌实体类
 */
@Data
public class Brand {
    Integer brandId;
    String brandCode;
    String brandName;
    Integer userId;
    Integer status;
}
