package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/22 11:10:09
 * @description:
 */
@Mapper
public interface AssetScrapMapper {
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 16:03
     * @description: 获取所有资产报废信息
     **/
    @Select("SELECT scrap.ID,scrap.ASSET_ID,asset.ASSET_CODE,asset.ASSET_NAME,sm.SCRAP_NAME,scrap.DATE,scrap.CAUSE,scrap.APPLICANT,scrap.RESULT FROM asset_scrap scrap JOIN asset ON scrap.ASSET_ID = asset.ASSET_ID JOIN scrap_mode sm ON scrap.SCRAP_ID = sm.SCRAP_ID")
    List<Map<String, Object>> getAssetScrap();
    /**
     * @author THS.
     * @param assetCode 资产编号
     * @param assetName 资产名称
     * @param scrapId 报废方式id
     * @param dateStart 日期头
     * @param dateEnd 日期尾
     * @param result 审批结果
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 16:29
     * @description:
     **/
    @MapKey("id")
    List<Map<String, Object>> getAssetScrapByCondition(String assetCode, String assetName, String scrapId, String dateStart, String dateEnd, String result);
    /**
     * @author THS.
     * @param assetId 资产id
     * @param scrapId 报废方式id
     * @param date 报废日期
     * @param cause 报废原因
     * @param applicant 申请人
     * @param result 审批结果
     * @return int
     * @date 2024/4/22 16:35
     * @description:
     **/
    @Insert("INSERT INTO asset_scrap(ASSET_ID,SCRAP_ID,DATE,CAUSE,APPLICANT,RESULT) VALUES(#{assetId},#{scrapId},#{date},#{cause},#{applicant},#{result})")
    int addAssetScrap(Integer assetId, String scrapId, Date date, String cause, String applicant, String result);
    /**
     * @author THS.
     * @param id 资产报废id
     * @return int
     * @date 2024/4/22 16:36
     * @description: 根据id删除资产报废信息
     **/
    @Delete("DELETE FROM asset_scrap WHERE ID = #{id}")
    int deleteAssetScrap(String id);
    /**
     * @author THS.
     * @param ID id
     * @param RESULT 状态
     * @return int
     * @date 2024/4/26 21:15
     * @description: 根据id修改资产报废信息审批结果
     **/
    @Update("UPDATE asset_scrap SET RESULT = #{RESULT} WHERE ID = #{ID}")
    int updateAssetScrap(Integer ID, String RESULT);
}
