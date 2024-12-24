package org.example.asset_management.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/22 10:24:28
 * @description:
 */
@Data
public class AssetScrap {
    private Integer id;
    private Integer assetId;
    private String scrapId;
    private Date date;
    private String cause;
    private String result;
    private String applicant;
}
