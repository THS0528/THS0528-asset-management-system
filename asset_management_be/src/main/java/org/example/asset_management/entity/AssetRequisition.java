package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/22 10:15:21
 * @description: 资产申购实体类
 */
@Data
public class AssetRequisition {
    private Integer id;
    private String assetName;
    private Integer classId;
    private Integer brandId;
    private Integer supplierId;
    private String applicant;
    private String argument;
    private String budget;
    private String result;
}
