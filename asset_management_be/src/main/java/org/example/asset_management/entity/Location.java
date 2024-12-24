package org.example.asset_management.entity;

import lombok.Data;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/21 18:58:47
 * @description:
 */
@Data
public class Location {
    private Integer locationId;
    private String locationName;
    private String locationType;
    private String locationInstructions;
    private Integer status;
}
