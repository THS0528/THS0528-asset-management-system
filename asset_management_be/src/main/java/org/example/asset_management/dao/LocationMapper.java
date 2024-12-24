package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/21 19:02:17
 * @description:
 */
@Mapper
public interface LocationMapper {
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 19:03
     * @description: 查询所有存放位置信息
     **/
    @Select("select * from location")
    List<Map<String, Object>> getLocation();
    /**
     * @author THS.
     * @param locationName 存放地点名称
     * @param locationType 存放地点类型
     * @param locationInstructions 存放地点说明
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 19:49
     * @description: 根据条件查询存放位置信息
     **/
    @MapKey("id")
    List<Map<String, Object>> getLocationByCondition(String locationName, String locationType, String locationInstructions, Integer status);
    /**
     * @author THS.
     * @param locationName 存放地点名称
     * @param locationType 存放地点类型
     * @param locationInstructions 存放地点说明
     * @param status 状态
     * @return int
     * @date 2024/4/21 19:49
     * @description: 更新存放位置信息
     **/
    @Insert("insert into location(LOCATION_NAME,LOCATION_TYPE,LOCATION_DESCRIPTION,STATUS) values(#{locationName},#{locationType},#{locationInstructions},#{status})")
    int insertLocation(String locationName, String locationType, String locationInstructions, Integer status);
    /**
     * @author THS.
     * @param locationId 存放地址id
     * @param locationName 存放地址名称
     * @param locationType 存放地址类型
     * @param locationInstructions 存放地点说明
     * @return int
     * @date 2024/4/21 19:53
     * @description: 更新存放位置信息
     **/
    @Update("update location set LOCATION_NAME = #{locationName},LOCATION_TYPE = #{locationType},LOCATION_DESCRIPTION = #{locationInstructions} where LOCATION_ID = #{locationId}")
    int updateLocation(Integer locationId, String locationName, String locationType, String locationInstructions);
    /**
     * @author THS.
     * @param locationId 地址id
     * @param status 状态
     * @return int
     * @date 2024/4/21 19:54
     * @description:
     **/
    @Update("update location set STATUS = #{status} where LOCATION_ID = #{locationId}")
    int updateLocationStatus(Integer locationId, Integer status);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:58
     * @description: 查询所有未停用的存放位置信息
     **/
    @Select("SELECT LOCATION_ID,LOCATION_NAME FROM location WHERE STATUS = '1'")
    List<Map<String, Object>> getLocationOptions();

}
