package org.example.asset_management.service;

import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:31:42
 * @description:
 */
public interface UpdateService {
    /**
     * @author THS.
     * @param classId 类别id
     * @param status 状态
     * @return int
     * @date 2024/4/17 22:14
     * @description: 根据类别id更新状态
     **/
    int updateAssetClassStatus(Integer classId, Integer status);
    /**
     * @author THS.
     * @param classId 资产类别id
     * @param className 资产类别名
     * @return int
     * @date 2024/4/18 10:57
     * @description: 根据类别id更新类别名或id
     **/
    int updateAssetClassByClassId(Integer classId, String classCode,String className);
    /**
     * @author THS.
     * @param brandId 品牌id
     * @param status 状态
     * @return int
     * @date 2024/4/18 21:28
     * @description:
     **/
    int updateBrandStatus(Integer brandId, Integer status);
    /**
     * @author THS.
     * @param brandId 品牌id
     * @param brandCode 品牌编码
     * @param brandName 品牌名称
     * @return int
     * @date 2024/4/18 21:29
     * @description: 根据品牌id更新品牌编码或名称
     **/
    int updateBrandByBrandId(Integer brandId, String brandCode, String brandName);
    /**
     * @author THS.
     * @param sectionId 部门id
     * @param status 状态
     * @return int
     * @date 2024/4/19 16:12
     * @description: 根据部门id更新状态
     **/
    int updateSectionStatus(Integer sectionId, Integer status);
    /**
     * @author THS.
     * @param sectionId 部门id
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @return int
     * @date 2024/4/19 16:13
     * @description: 根据部门id更新部门编码或名称
     **/
    int updateSectionBySectionId(Integer sectionId, String sectionCode, String sectionName);
    /**
     * @author THS.
     * @param scrapModeId 报废方式id
     * @param status 状态
     * @return int
     * @date 2024/4/19 16:13
     * @description: 根据报废方式id更新状态
     **/
    int updateScrapModeStatus(Integer scrapModeId, Integer status);
    /**
     * @author THS.
     * @param scrapModeId 报废方式id
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @return int
     * @date 2024/4/19 16:14
     * @description: 根据报废方式id更新报废方式编码或名称
     **/
    int updateScrapModeByScrapModeId(Integer scrapModeId, String scrapModeCode, String scrapModeName);
    /**
     * @param supplierId 供应商id
     * @param status     状态
     * @return int
     * @author THS.
     * @date 2024/4/20 20:34
     * @description: 根据供应商id修改供应商状态
     **/
    int updateSupplierStatus(Integer supplierId, Integer status);

    /**
     * @param supplierId   供应商id
     * @param supplierName 供应商名称
     * @param supplierType 供应商类型
     * @param linkman      联系人
     * @param mp_number    联系人电话
     * @param location     地址
     * @return int
     * @author THS.
     * @date 2024/4/20 20:36
     * @description: 根据供应商id修改供应商信息
     **/
    int updateSupplier(Integer supplierId, String supplierName, String supplierType, String linkman, String mp_number, String location);
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
    int updateLocation(Integer locationId, String locationName, String locationType, String locationInstructions);
    /**
     * @author THS.
     * @param locationId 地址id
     * @param status 状态
     * @return int
     * @date 2024/4/21 19:54
     * @description:
     **/
    int updateLocationStatus(Integer locationId, Integer status);
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
    int updateAsset(Integer assetId, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId);
    /**
     * @author THS.
     * @param assetId 资产id
     * @param status 状态
     * @return int
     * @date 2024/4/22 14:59
     * @description:  根据资产id更新资产状态
     **/
    int updateAssetStatus(Integer assetId ,String status);
    /**
     * @author THS.
     * @param borrowedCode 借用编号
     * @param backDate 归还日期
     * @return int
     * @date 2024/4/22 14:55
     * @description: 资产归还
     **/
    int updateAssetRM(String borrowedCode, Date backDate , String backRemark);
    /**
     * @author THS.
     * @param assetRTId 资产申购id
     * @param result 审批结果
     * @return int
     * @date 2024/4/26 13:55
     * @description:
     **/
    int updateAssetRT(Integer assetRTId, String result);
    /**
     * @author THS.
     * @param ID id
     * @param RESULT 状态
     * @return int
     * @date 2024/4/26 21:15
     * @description: 根据id修改资产报废信息审批结果
     **/
    int updateAssetScrap(Integer ID, String RESULT);

}
