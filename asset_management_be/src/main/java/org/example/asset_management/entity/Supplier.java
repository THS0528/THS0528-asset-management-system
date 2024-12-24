package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/20 18:30:41
 * @description: 供应商实体类
 */
@Data
public class Supplier {
    Integer supplierId;
    String supplierName;
    String supplierType;
    String linkman;
    String mp_number;
    String location;
    Integer status;

}
