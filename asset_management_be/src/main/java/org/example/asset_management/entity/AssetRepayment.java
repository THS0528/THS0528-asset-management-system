package org.example.asset_management.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/22 09:59:43
 * @description: 资产借还实体类
 */
@Data
public class AssetRepayment {
    private String borrowedCode;
    private Integer assetId;
    private Date loanDate;
    private Integer sectionId;
    private String cause;
    private Date backDate;
    private String backRemark;
}
