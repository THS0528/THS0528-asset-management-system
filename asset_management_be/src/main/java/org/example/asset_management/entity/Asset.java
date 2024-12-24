package org.example.asset_management.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/21 22:20:24
 * @description:
 */
@Data
public class Asset {
    private Integer assetId;
    private String assetName;
    private String assetCode;
    private Integer classId;
    private Integer brandId;
    private Integer supplierId;
    private Integer locationId;
    private Date date;
    private String status;
}
