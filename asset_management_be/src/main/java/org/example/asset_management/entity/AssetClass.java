package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 19:37:14
 * @description: 资产类别实体类
 */
@Data
public class AssetClass {
    private Integer classId;
    private String classCode;
    private String className;
    private Integer userId;
    private Integer status;


}
