package org.example.asset_management.service;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:29:38
 * @description: 插入数据业务逻辑
 */
public interface InsertService {
    /**
     * @author THS.
     * @param classCode 资产类别id
     * @param className 资产类别名称
     * @param userId 用户id
     * @param status 状态
     * @return int
     * @date 2024/4/17 11:16
     * @description: 新增资产类别
     **/
    int insertAssetClass(String classCode, String className, Integer userId, Integer status);
    /**
     * @author THS.
     * @param brandCode 品牌id
     * @param brandName 品牌名称
     * @param userId 用户id
     * @param status 状态
     * @return int
     * @date 2024/4/18 21:25
     * @description: 新增品牌信息
     **/
    int insertBrand(String brandCode, String brandName, Integer userId, Integer status);
    /**
     * @author THS.
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @param status 状态
     * @return int
     * @date 2024/4/19 16:10
     * @description: 新增部门信息
     **/
    int insertSection(String sectionCode, String sectionName, Integer status);
    /**
     * @author THS.
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @param status 状态
     * @return int
     * @date 2024/4/19 16:10
     * @description: 新增报废方式
     **/
    int insertScrapMode(String scrapModeCode, String scrapModeName, Integer status);
    /**
     * @author THS.
     * @param supplierName 供应商名称
     * @param supplierType 供应商类型
     * @param linkman 联系人
     * @param mp_number 联系人电话
     * @param location 地址
     * @param status 状态
     * @return int
     * @date 2024/4/20 20:40
     * @description: 添加供应商
     **/
    int insertSupplier(String supplierName, String supplierType, String linkman, String mp_number, String location, Integer status);
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
    int insertLocation(String locationName, String locationType, String locationInstructions, Integer status);
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
    int insertAsset(String assetCode, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId, Date date, String status);
    /**
     * @author THS.
     * @param borrowedCode 借用编号
     * @param assetId 资产id
     * @param loanDate 借用日期
     * @param sectionId 部门id
     * @param cause 借用原因
     * @return int
     * @date 2024/4/22 14:54
     * @description: 添加资产借用记录
     **/
    int addAssetRM(String borrowedCode, Integer assetId, Date loanDate, Integer sectionId,  String cause);
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
    int addAssetRT(String assetName, Integer classId, Integer supplierId, Integer brandId, String argument, String applicant, String budget, String result);
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
    int addAssetScrap(Integer assetId, String scrapId, Date date, String cause, String applicant, String result);
}
