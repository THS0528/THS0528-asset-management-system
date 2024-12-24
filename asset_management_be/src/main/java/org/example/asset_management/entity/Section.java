package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/18 23:16:31
 * @description: 部门实体类
 */
@Data
public class Section {
    private Integer sectionId;
    private String sectionName;
    private String sectionCode;
    private Integer status;
}
