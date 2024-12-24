package org.example.asset_management.dao;

import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/21 22:23:11
 * @description:
 */
@Mapper
public interface AssetMapper {
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:34
     * @description: 查询所有资产信息
     **/
    @Select("SELECT asset.ASSET_ID,asset.ASSET_CODE,asset.ASSET_NAME,asset.CLASS_ID,asset.BRAND_ID,asset.SUPPLIER_ID,asset.LOCATION_ID," +
            "ac.CLASS_NAME,su.SUPPLIER_NAME,br.BRAND_NAME,lo.LOCATION_NAME,asset.DATE,asset.STATUS FROM asset " +
            "JOIN asset_class ac ON asset.CLASS_ID = ac.CLASS_ID " +
            "JOIN supplier su ON asset.SUPPLIER_ID = su.SUPPLIER_ID " +
            "JOIN brand br ON asset.BRAND_ID = br.BRAND_ID " +
            "JOIN LOCATION lo ON asset.LOCATION_ID = lo.LOCATION_ID")
    List<Map<String, Object>> getAsset();
    /**
     * @author THS.
     * @param assetCode 资产编码
     * @param assetName 资产名称
     * @param classId 资产类别id
     * @param supplierId 供应商id
     * @param brandId 品牌id
     * @param locationId 存放地点id
     * @param date 日期
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:45
     * @description: 根据条件查询资产信息
     **/
    @MapKey("id")
    List<Map<String, Object>> getAssetByCondition(String assetCode, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId, Date date, Integer status);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 23:13
     * @description: 查询所有在库的资产
     **/
    @Select("SELECT ASSET_NAME,ASSET_ID,ASSET_CODE FROM asset WHERE STATUS = '在库'")
    List<Map<String, Object>> getAssetOptions();
    /**
     * @author THS.
     * @param assetCode 资产编码
     * @param assetName 资产名称
     * @param classId 资产类别id
     * @param supplierId 供应商id
     * @param brandId 品牌id
     * @param locationId 存放地址id
     * @param date 日期
     * @param status 状态
     * @return int
     * @date 2024/4/21 22:48
     * @description: 添加资产
     **/
    @Insert("INSERT INTO asset(ASSET_CODE,ASSET_NAME,CLASS_ID,SUPPLIER_ID,BRAND_ID,LOCATION_ID,DATE,STATUS) VALUES(#{assetCode},#{assetName},#{classId},#{supplierId},#{brandId},#{locationId},#{date},#{status})")
    int insertAsset(String assetCode, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId, Date date, String status);
    /**
     * @author THS.
     * @param assetName 资产名称
     * @param classId 资产类别id
     * @param supplierId 供应商id
     * @param brandId 品牌id
     * @param locationId 存放地址id
     * @return int
     * @date 2024/4/21 22:49
     * @description: 更新资产
     **/
    @Update("UPDATE asset SET ASSET_NAME = #{assetName},CLASS_ID = #{classId},SUPPLIER_ID = #{supplierId},BRAND_ID = #{brandId},LOCATION_ID = #{locationId} WHERE ASSET_ID= #{assetId}")
    int updateAsset(Integer assetId, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId);
    /**
     * @author THS.
     * @param assetId 资产id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:51
     * @description: 根据资产id查询资产信息
     **/
    @Delete("DELETE FROM asset WHERE ASSET_ID = #{assetId}")
    int deleteAsset(Integer assetId);
    /**
     * @author THS.
     * @param assetId 资产id
     * @param status 状态
     * @return int
     * @date 2024/4/22 14:59
     * @description:  根据资产id更新资产状态
     **/
    @Update("UPDATE asset SET STATUS = #{status} WHERE ASSET_ID = #{assetId}")
    int updateAssetStatus(Integer assetId ,String status);
}
