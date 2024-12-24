package org.example.asset_management.service;

import lombok.Data;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:28:42
 * @description:
 */
public interface SelectService {
    /**
     * @author THS.
     * @param userId 用户id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/17 10:02
     * @description: 根据用户id获取当前用户创建的资产类别
     **/
    List<Map<String, Object>> getAssetClassByUserId(Integer userId);
    /**
     * @author THS.
     * @param classId 资产类别id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/17 20:25
     * @description: 根据资产类别id获取资产类别信息
     **/
    List<Map<String, Object>> getAssetClassByClassCode(String classId);
    /**
     * @author THS.
     * @param classId 资产类别id
     * @param className 资产类别名称
     * @param status 资产类别状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/17 22:53
     * @description:  根据资产类别id、资产类别名称、资产类别状态获取资产类别信息
     **/
    List<Map<String, Object>> getAssetClassByCondition(String classId, String className, Integer status);
    /**
     * @author THS.
     * @param userId 用户id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/18 20:11
     * @description: 根据用户id获取当前用户创建的品牌
     **/
    List<Map<String, Object>> getBrandByUserId(Integer userId);
    /**
     * @author THS.
     * @param brandCode 品牌编码
     * @param brandName 品牌名
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/18 20:12
     * @description: 根据品牌编码、品牌名、状态获取品牌信息
     **/
    List<Map<String, Object>> getBrandByCondition(String brandCode, String brandName, Integer status);
    /**
     * @author THS.
     * @param brandCode 品牌编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/18 21:56
     * @description:  根据品牌编码获取品牌信息
     **/
    List<Map<String, Object>> getBrandByBrandCode(String brandCode);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 16:03
     * @description: 获取所有部门信息
     **/
    List<Map<String, Object>> getSection();
    /**
     * @author THS.
     * @param sectionCode 部门编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 16:04
     * @description: 查询是否有此部门编号
     **/
    List<Map<String, Object>> getSectionBySectionCode(String sectionCode);
    /**
     * @author THS.
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 16:05
     * @description: 根据部门编码、部门名称、状态获取部门信息
     **/
    List<Map<String, Object>> getSectionByCondition(String sectionCode, String sectionName, Integer status);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 16:06
     * @description: 查询报废模式
     **/
    List<Map<String, Object>> getScrapMode();
    /**
     * @author THS.
     * @param scrapModeCode 报废方式编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 16:07
     * @description: 查询是否有此报废方式编码
     **/
    List<Map<String, Object>> getScrapModeByScrapModeCode(String scrapModeCode);
    /**
     * @author THS.
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 16:07
     * @description: 根据报废方式编码、报废方式名称、状态获取报废方式信息
     **/
    List<Map<String, Object>> getScrapModeByCondition(String scrapModeCode, String scrapModeName, Integer status);
    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/20 18:52
     * @description: 查询所有供应商
     **/
    @Select("SELECT * FROM supplier")
    List<Map<String, Object>> getSupplier();
    /**
     * @param supplierName 供应商名称
     * @param supplierType 供应商类型
     * @param linkman      联系人
     * @param mp_number    联系人电话
     * @param location     地址
     * @param status       状态
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/20 20:30
     * @description: 根据条件查询供应商
     **/
    List<Map<String, Object>> getSupplierByCondition(String supplierName, String supplierType, String linkman, String mp_number, String location, Integer status);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 19:03
     * @description: 查询所有存放位置信息
     **/
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
    List<Map<String, Object>> getLocationByCondition(String locationName, String locationType, String locationInstructions, Integer status);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:55
     * @description: 查询所有未停用资产类别
     **/
    List<Map<String, Object>> getAssetClassOptions();
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:56
     * @description: 查询所有未停用的品牌
     **/
    List<Map<String, Object>> getBrandOptions();
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:58
     * @description: 查询所有未停用的存放位置信息
     **/
    List<Map<String, Object>> getLocationOptions();
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:37
     * @description: 查询未停用的报废方式
     **/
    List<Map<String, Object>> getScrapModeOptions();
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:59
     * @description: 查询未停用的部门
     **/
    List<Map<String, Object>> getSectionOptions();
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 23:02
     * @description: 查询未停用的供应商
     **/
    List<Map<String, Object>> getSupplierOptions();
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:34
     * @description: 查询所有资产信息
     **/
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
    List<Map<String, Object>> getAssetByCondition(String assetCode, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId, Date date, Integer status);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 23:13
     * @description: 查询所有在库的资产
     **/
    List<Map<String, Object>> getAssetOptions();
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 10:46
     * @description: 查询所有借用记录
     **/
    List<Map<String, Object>> getAssetRM();
    /**
     * @author THS.
     * @param assetCode 资产编码
     * @param assetId 资产id
     * @param sectionId 部门id
     * @param classId 资产类别id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 11:33
     * @description: 根据条件查询资产借用记录
     **/
    List<Map<String,Object>> getAssetRMByCondition(String assetCode, Integer assetId, Integer sectionId, Integer classId);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 15:15
     * @description: 查询所有资产申购信息
     **/
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
   List<Map<String, Object>> getAssetRTByCondition(String assetName, String className, String supplierName, String brandName, String argument, String budgetStart, String budgetEnd, String result);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 16:03
     * @description: 获取所有资产报废信息
     **/
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
    List<Map<String, Object>> getAssetScrapByCondition(String assetCode, String assetName, String scrapId, String dateStart, String dateEnd, String result);

}
