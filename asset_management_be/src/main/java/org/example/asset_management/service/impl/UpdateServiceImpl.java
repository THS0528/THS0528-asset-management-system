package org.example.asset_management.service.impl;

import lombok.Data;
import org.example.asset_management.dao.*;
import org.example.asset_management.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:40:05
 * @description: 更新服务实现类
 */
@Service
public class UpdateServiceImpl implements UpdateService {
    private final AssetClassMapper assetClassMapper;
    private final BrandMapper brandMapper;
    private final ScrapModeMapper scrapModeMapper;
    private final SectionMapper sectionMapper;
    private final SupplierMapper supplierMapper;
    private final LocationMapper locationMapper;
    private final AssetMapper assetMapper;
    private final AssetRepaymentMapper assetRepaymentMapper;
    private final AssetRequisitionMapper assetRequisitionMapper;
    private final AssetScrapMapper assetScrapMapper;
    @Autowired
    public UpdateServiceImpl(AssetClassMapper assetClassMapper, BrandMapper brandMapper,
                             ScrapModeMapper scrapModeMapper, SectionMapper sectionMapper,
                             SupplierMapper supplierMapper, LocationMapper locationMapper,
                             AssetMapper assetMapper, AssetRepaymentMapper assetRepaymentMapper,
                             AssetRequisitionMapper assetRequisitionMapper,AssetScrapMapper assetScrapMapper) {
        this.assetClassMapper = assetClassMapper;
        this.brandMapper = brandMapper;
        this.scrapModeMapper = scrapModeMapper;
        this.sectionMapper = sectionMapper;
        this.supplierMapper = supplierMapper;
        this.locationMapper = locationMapper;
        this.assetMapper = assetMapper;
        this.assetRepaymentMapper = assetRepaymentMapper;
        this.assetRequisitionMapper = assetRequisitionMapper;
        this.assetScrapMapper = assetScrapMapper;
    }


    /**
     * @param classId 类别id
     * @param status  状态
     * @return int
     * @author THS.
     * @date 2024/4/17 22:14
     * @description: 根据类别id更新状态
     **/
    @Override
    public int updateAssetClassStatus(Integer classId, Integer status) {
        if (classId == null || status == null){
            return 0;
        }
        return assetClassMapper.updateAssetClassStatus(classId, status);
    }
    /**
     * @param classId   资产类别id
     * @param className 资产类别名
     * @return int
     * @author THS.
     * @date 2024/4/18 10:57
     * @description: 根据类别id更新类别名或资产编码
     **/
    @Override
    public int updateAssetClassByClassId(Integer classId, String classCode,String className) {
        if (classId == null || className == null || classCode == null){
            return 0;
        }
        return assetClassMapper.updateAssetClassByClassId(classId, classCode ,className);
    }
    /**
     * @author THS.
     * @param brandId 品牌id
     * @param status 状态
     * @return int
     * @date 2024/4/18 21:28
     * @description: 根据品牌id更新状态
     **/
    @Override
    public int updateBrandStatus(Integer brandId, Integer status) {
        if (brandId == null || status == null){
            return 0;
        }
        return brandMapper.updateBrandCode(brandId, status);
    }

    /**
     * @param brandId   品牌id
     * @param brandCode 品牌编码
     * @param brandName 品牌名称
     * @return int
     * @author THS.
     * @date 2024/4/18 21:29
     * @description: 根据品牌id更新品牌编码或名称
     **/
    @Override
    public int updateBrandByBrandId(Integer brandId, String brandCode, String brandName) {
        if (brandId == null || brandCode == null || brandName == null){
            return 0;
        }
        return brandMapper.updateBrandByBrandId(brandId, brandCode, brandName);
    }

    /**
     * @param sectionId 部门id
     * @param status    状态
     * @return int
     * @author THS.
     * @date 2024/4/19 16:12
     * @description: 根据部门id更新状态
     **/
    @Override
    public int updateSectionStatus(Integer sectionId, Integer status) {
        if (sectionId == null || status == null){
            return 0;
        }
        return sectionMapper.updateSectionStatus(sectionId, status);
    }

    /**
     * @param sectionId   部门id
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @return int
     * @author THS.
     * @date 2024/4/19 16:13
     * @description: 根据部门id更新部门编码或名称
     **/
    @Override
    public int updateSectionBySectionId(Integer sectionId, String sectionCode, String sectionName) {
        if (sectionId == null || sectionCode == null || sectionName == null){
            return 0;
        }
        return sectionMapper.updateSectionBySectionId(sectionId, sectionCode, sectionName);
    }

    /**
     * @param scrapModeId 报废方式id
     * @param status      状态
     * @return int
     * @author THS.
     * @date 2024/4/19 16:13
     * @description: 根据报废方式id更新状态
     **/
    @Override
    public int updateScrapModeStatus(Integer scrapModeId, Integer status) {
        if (scrapModeId == null || status == null){
            return 0;
        }
        return scrapModeMapper.updateScrapModeStatus(scrapModeId, status);
    }

    /**
     * @param scrapModeId   报废方式id
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @return int
     * @author THS.
     * @date 2024/4/19 16:14
     * @description: 根据报废方式id更新报废方式编码或名称
     **/
    @Override
    public int updateScrapModeByScrapModeId(Integer scrapModeId, String scrapModeCode, String scrapModeName) {
        if (scrapModeId == null || scrapModeCode == null || scrapModeName == null){
            return 0;
        }
        return scrapModeMapper.updateScrapModeByScrapModeId(scrapModeId, scrapModeCode, scrapModeName);
    }

    /**
     * @param supplierId 供应商id
     * @param status     状态
     * @return int
     * @author THS.
     * @date 2024/4/20 20:34
     * @description: 根据供应商id修改供应商状态
     **/
    @Override
    public int updateSupplierStatus(Integer supplierId, Integer status) {
        if (supplierId == null || status == null){
            return 0;
        }
        return supplierMapper.updateSupplierStatus(supplierId, status);
    }

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
    @Override
    public int updateSupplier(Integer supplierId, String supplierName, String supplierType, String linkman, String mp_number, String location) {
        if (supplierId == null || supplierName == null || supplierType == null || linkman == null || mp_number == null || location == null){
            return 0;
        }
        return supplierMapper.updateSupplier(supplierId, supplierName, supplierType, linkman, mp_number, location);
    }

    /**
     * @param locationId           存放地址id
     * @param locationName         存放地址名称
     * @param locationType         存放地址类型
     * @param locationInstructions 存放地点说明
     * @return int
     * @author THS.
     * @date 2024/4/21 19:53
     * @description: 更新存放位置信息
     **/
    @Override
    public int updateLocation(Integer locationId, String locationName, String locationType, String locationInstructions) {
        if (locationId == null || locationName == null || locationType == null || locationInstructions == null){
            return 0;
        }
        return locationMapper.updateLocation(locationId, locationName, locationType, locationInstructions);
    }

    /**
     * @param locationId 地址id
     * @param status     状态
     * @return int
     * @author THS.
     * @date 2024/4/21 19:54
     * @description: 根据地址id更新状态
     **/
    @Override
    public int updateLocationStatus(Integer locationId, Integer status) {
        if (locationId == null || status == null){
            return 0;
        }
        return locationMapper.updateLocationStatus(locationId, status);
    }

    /**
     * @param assetId    资产id
     * @param assetName  资产名称
     * @param classId    资产类别id
     * @param supplierId 供应商id
     * @param brandId    品牌id
     * @param locationId 存放地址id
     * @return int
     * @author THS.
     * @date 2024/4/21 22:49
     * @description: 更新资产
     **/
    @Override
    public int updateAsset(Integer assetId, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId) {
        if (assetId == null || assetName == null || classId == null || supplierId == null || brandId == null || locationId == null){
            return 0;
        }
        return assetMapper.updateAsset(assetId, assetName, classId, supplierId, brandId, locationId);
    }

    /**
     * @param assetId 资产id
     * @param status  状态
     * @return int
     * @author THS.
     * @date 2024/4/22 14:59
     * @description: 根据资产id更新资产状态
     **/
    @Override
    public int updateAssetStatus(Integer assetId, String status) {
       if (assetId == null || status == null){
           return 0;
       }
       return assetMapper.updateAssetStatus(assetId, status);
    }

    /**
     * @param borrowedCode 借用编号
     * @param backDate     归还日期
     * @return int
     * @author THS.
     * @date 2024/4/22 14:55
     * @description: 资产归还
     **/
    @Override
    public int updateAssetRM(String borrowedCode, Date backDate, String backRemark) {
        if (borrowedCode == null || backDate == null){
            return 0;
        }
        return assetRepaymentMapper.updateAssetRM(borrowedCode, backDate, backRemark);
    }

    /**
     * @param assetRTId 资产申购id
     * @param result    审批结果
     * @return int
     * @author THS.
     * @date 2024/4/26 13:55
     * @description:
     **/
    @Override
    public int updateAssetRT(Integer assetRTId, String result) {
        if (assetRTId == null || result == null){
            return 0;
        }
        return assetRequisitionMapper.updateAssetRT(assetRTId, result);
    }

    /**
     * @param ID     id
     * @param RESULT 状态
     * @return int
     * @author THS.
     * @date 2024/4/26 21:15
     * @description: 根据id修改资产报废信息审批结果
     **/
    @Override
    public int updateAssetScrap(Integer ID, String RESULT) {
        if (ID != null && RESULT != null){
            return assetScrapMapper.updateAssetScrap(ID, RESULT);
        }
        return 0;
    }
}
