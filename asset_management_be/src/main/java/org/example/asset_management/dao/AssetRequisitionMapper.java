package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/22 11:09:41
 * @description:
 */
@Mapper
public interface AssetRequisitionMapper {
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 15:15
     * @description: 查询所有资产申购信息
     **/
    @Select("SELECT ar.id,ar.ASSET_NAME,ac.CLASS_NAME,br.BRAND_NAME,su.SUPPLIER_NAME,ar.ARGUMENT,ar.APPLICANT,FORMAT(ar.BUDGET, 2) as BUDGET,ar.RESULT FROM asset_requisition ar JOIN asset_class ac ON ar.CLASS_ID = ac.CLASS_ID JOIN SUPPLIER su ON ar.SUPPLIER_ID = su.SUPPLIER_ID JOIN BRAND br ON ar.BRAND_ID = br.BRAND_ID")
    List<Map<String, Object>> getAssetRT();
    /**
     * @author THS.
     * @param assetName 资产名称
     * @param className 资产类别名称
     * @param supplierName 供应商名称
     * @param brandName 品牌名称
     * @param argument 申请理由
     * @param budgetStart 预算头
     * @param budgetEnd 预算尾
     * @param result 审批结果
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 15:30
     * @description: 根据条件查询资产申购信息
     **/
    @MapKey("id")
    List<Map<String, Object>> getAssetRTByCondition(String assetName, String className, String supplierName, String brandName, String argument, String budgetStart, String budgetEnd, String result);
    /**
     * @author THS.
     * @param assetName 资产名称
     * @param classId 资产类别id
     * @param supplierId 供应商id
     * @param brandId 品牌id
     * @param argument 申请理由
     * @param applicant 申请人
     * @param budget 申购预算
     * @param result 审批结果
     * @return int
     * @date 2024/4/22 15:33
     * @description: 添加资产申购信息
     **/
    @Insert("INSERT INTO asset_requisition(ASSET_NAME,CLASS_ID,SUPPLIER_ID,BRAND_ID,ARGUMENT,APPLICANT,BUDGET,RESULT) VALUES(#{assetName},#{classId},#{supplierId},#{brandId},#{argument},#{applicant},#{budget},#{result})")
    int addAssetRT(String assetName, Integer classId, Integer supplierId, Integer brandId, String argument, String applicant, String budget, String result);
    /**
     * @author THS.
     * @param assetRTId 资产申购id
     * @return int
     * @date 2024/4/22 15:38
     * @description: 删除资产申购信息
     **/
    @Delete("DELETE FROM asset_requisition WHERE id = #{aseetRTId}")
    int deleteAssetRT(Integer assetRTId);
    /**
     * @author THS.
     * @param assetRTId 资产申购id
     * @param result 审批结果
     * @return int
     * @date 2024/4/26 13:55
     * @description:
     **/
    @Update("UPDATE asset_requisition SET RESULT = #{result} WHERE id = #{assetRTId}")
    int updateAssetRT(Integer assetRTId, String result);
}
