package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/19 10:04:57
 * @description: 报废方式mapper
 */
@Mapper
public interface ScrapModeMapper {
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:33
     * @description: 查询全部报废方式
     **/
    @Select("SELECT SCRAP_ID,SCRAP_CODE,SCRAP_NAME, STATUS, STATUS FROM scrap_mode")
    List<Map<String, Object>> getScrapMode();
    /**
     * @author THS.
     * @param scrapModeCode 保费方式编码
     * @param scrapModeName 报废方式名称
     * @param status 状态
     * @return int
     * @date 2024/4/19 15:33
     * @description: 新增报废方式
     **/
    @Insert("INSERT INTO scrap_mode(SCRAP_CODE,SCRAP_NAME, STATUS) VALUES(#{scrapModeCode},#{scrapModeName}, #{status})")
    int insertScrapMode(String scrapModeCode, String scrapModeName, int status);
    /**
     * @author THS.
     * @param scrapModeCode 报废方式编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:34
     * @description: 查询是否已经由此报废方式编码
     **/
    @Select("SELECT SCRAP_NAME FROM scrap_mode WHERE SCRAP_CODE = #{scrapModeCode}")
    List<Map<String, Object>> getScrapModeByScrapModeCode(String scrapModeCode);
    /**
     * @author THS.
     * @param scrapModeId 报废方式id
     * @param status 状态
     * @return int
     * @date 2024/4/19 15:36
     * @description: 更新报废方式状态
     **/
    @Update("UPDATE scrap_mode SET STATUS = #{status} WHERE SCRAP_ID = #{scrapModeId}")
    int updateScrapModeStatus(Integer scrapModeId, Integer status);
    /**
     * @author THS.
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:37
     * @description: 根据条件查询报废方式
     **/
    @MapKey("id")
    List<Map<String, Object>> getScrapModeByCondition(String scrapModeCode, String scrapModeName, Integer status);
    /**
     * @author THS.
     * @param scrapModeId 报废方式id
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @return int
     * @date 2024/4/19 15:37
     * @description: 更新报废方式
     **/
    @Update("UPDATE scrap_mode SET SCRAP_CODE = #{scrapModeCode},SCRAP_NAME = #{scrapModeName} WHERE SCRAP_ID = #{scrapModeId}")
    int updateScrapModeByScrapModeId(Integer scrapModeId, String scrapModeCode, String scrapModeName);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:37
     * @description: 查询未停用的报废方式
     **/
    @Select("SELECT SCRAP_ID,SCRAP_NAME FROM scrap_mode WHERE STATUS = '1'")
    List<Map<String, Object>> getScrapModeOptions();
}
