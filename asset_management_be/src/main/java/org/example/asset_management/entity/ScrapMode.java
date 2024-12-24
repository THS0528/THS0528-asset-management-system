package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/18 23:19:04
 * @description: 报废方式实体类
 */
@Data
public class ScrapMode {
    private Integer scrapModeId;
    private String scrapModeCode;
    private String scrapModeName;
    private Integer status;
}
