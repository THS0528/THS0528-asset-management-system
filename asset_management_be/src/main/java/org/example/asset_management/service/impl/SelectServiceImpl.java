package org.example.asset_management.service.impl;

import lombok.Data;
import org.apache.ibatis.annotations.Select;
import org.example.asset_management.dao.*;
import org.example.asset_management.service.SelectService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:36:52
 * @description: 查询业务实现类
 */
@Service
public class SelectServiceImpl implements SelectService {
    private final AssetClassMapper assetClassMapper;
    private final BrandMapper brandMapper;
    private final SectionMapper sectionMapper;
    private final ScrapModeMapper scrapModeMapper;
    private final SupplierMapper supplierMapper;
    private final LocationMapper locationMapper;
    private final AssetMapper assetMapper;
    private final AssetScrapMapper assetScrapMapper;
    private final AssetRequisitionMapper assetRequisitionMapper;
    private final AssetRepaymentMapper assetRepaymentMapper;

    public SelectServiceImpl(AssetClassMapper assetClassMapper, BrandMapper brandMapper,
                             SectionMapper sectionMapper, ScrapModeMapper scrapModeMapper,
                             SupplierMapper supplierMapper, LocationMapper locationMapper,
                             AssetMapper assetMapper, AssetScrapMapper assetScrapMapper,
                             AssetRequisitionMapper assetRequisitionMapper, AssetRepaymentMapper assetRepaymentMapper) {
        this.assetClassMapper = assetClassMapper;
        this.brandMapper = brandMapper;
        this.sectionMapper = sectionMapper;
        this.scrapModeMapper = scrapModeMapper;
        this.supplierMapper = supplierMapper;
        this.locationMapper = locationMapper;
        this.assetMapper = assetMapper;
        this.assetScrapMapper = assetScrapMapper;
        this.assetRequisitionMapper = assetRequisitionMapper;
        this.assetRepaymentMapper = assetRepaymentMapper;
    }


    /**
     * @param userId 用户id
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/17 10:02
     * @description: 根据用户id获取当前用户创建的资产类bie
     **/
    @Override
    public List<Map<String, Object>> getAssetClassByUserId(Integer userId) {
        if (userId == null){
            return null;
        }
        return assetClassMapper.getAssetClassByUserId(userId);
    }

    /**
     * @param classCode 资产类别编码
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/17 20:25
     * @description: 根据资产类别编码获取资产类别信息
     **/
    @Override
    public List<Map<String, Object>> getAssetClassByClassCode(String classCode) {
        if (classCode == null){
            System.out.println("classId is null");
            return null;
        }
        List<Map<String, Object>> assetClass = assetClassMapper.getAssetClassByClassCode(classCode);
        if (!assetClass.isEmpty()){
            return assetClass;
        }
        return null;
    }

    /**
     * @param classId   资产类别id
     * @param className 资产类别名称
     * @param status    资产类别状态
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/17 22:53
     * @description: 根据资产类别id、资产类别名称、资产类别状态获取资产类别信息
     **/
    @Override
    public List<Map<String, Object>> getAssetClassByCondition(String classId, String className, Integer status) {
        return assetClassMapper.getAssetClassByCondition(classId, className, status);
    }
    /**
     * @author THS.
     * @param userId 用户id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/18 20:11
     * @description: 根据用户id获取当前用户创建的品牌
     **/
    @Override
    public List<Map<String, Object>> getBrandByUserId(Integer userId) {
        if (userId == null){
            return null;
        }
        return brandMapper.getBrandByUserId(userId);
    }

    /**
     * @param brandCode 品牌编码
     * @param brandName 品牌名
     * @param status    状态
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/18 20:12
     * @description: 根据品牌编码、品牌名称、状态获取品牌信息
     **/
    @Override
    public List<Map<String, Object>> getBrandByCondition(String brandCode, String brandName, Integer status) {
        return brandMapper.getBrandByCondition(brandCode, brandName, status);
    }

    /**
     * @param brandCode 品牌编码
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/18 21:56
     * @description: 根据品牌编码获取品牌信息
     **/
    @Override
    public List<Map<String, Object>> getBrandByBrandCode(String brandCode) {
        if (brandCode == null){
            return null;
        }
        return brandMapper.getBrandByBrandCode(brandCode);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/19 16:03
     * @description: 获取所有部门信息
     **/
    @Override
    public List<Map<String, Object>> getSection() {
        return sectionMapper.getSection();
    }

    /**
     * @param sectionCode 部门编码
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/19 16:04
     * @description: 查询是否有此部门编号
     **/
    @Override
    public List<Map<String, Object>> getSectionBySectionCode(String sectionCode) {
        if (sectionCode == null){
            return null;
        }
        return sectionMapper.getSectionBySectionCode(sectionCode);
    }

    /**
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @param status      状态
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/19 16:05
     * @description: 根据部门编码、部门名称、状态获取部门信息
     **/
    @Override
    public List<Map<String, Object>> getSectionByCondition(String sectionCode, String sectionName, Integer status) {
        return sectionMapper.getSectionByCondition(sectionCode, sectionName, status);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/19 16:06
     * @description: 查询报废模式
     **/
    @Override
    public List<Map<String, Object>> getScrapMode() {
        return scrapModeMapper.getScrapMode();
    }

    /**
     * @param scrapModeCode 报废方式编码
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/19 16:07
     * @description: 查询是否有此报废方式编码
     **/
    @Override
    public List<Map<String, Object>> getScrapModeByScrapModeCode(String scrapModeCode) {
        if (scrapModeCode == null){
            return null;
        }
        return scrapModeMapper.getScrapModeByScrapModeCode(scrapModeCode);
    }

    /**
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @param status        状态
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/19 16:07
     * @description: 根据报废方式编码、报废方式名称、状态获取报废方式信息
     **/
    @Override
    public List<Map<String, Object>> getScrapModeByCondition(String scrapModeCode, String scrapModeName, Integer status) {
        return scrapModeMapper.getScrapModeByCondition(scrapModeCode, scrapModeName, status);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/20 18:52
     * @description: 查询所有供应商
     **/
    @Override
    public List<Map<String, Object>> getSupplier() {
        return supplierMapper.getSupplier();
    }

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
    @Override
    public List<Map<String, Object>> getSupplierByCondition(String supplierName, String supplierType, String linkman, String mp_number, String location, Integer status) {
        return supplierMapper.getSupplierByCondition(supplierName, supplierType, linkman, mp_number, location, status);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 19:03
     * @description: 查询所有存放位置信息
     **/
    @Override
    public List<Map<String, Object>> getLocation() {
        return locationMapper.getLocation();
    }

    /**
     * @param locationName         存放地点名称
     * @param locationType         存放地点类型
     * @param locationInstructions 存放地点说明
     * @param status               状态
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 19:49
     * @description: 根据条件查询存放位置信息
     **/
    @Override
    public List<Map<String, Object>> getLocationByCondition(String locationName, String locationType, String locationInstructions, Integer status) {
        return locationMapper.getLocationByCondition(locationName, locationType, locationInstructions, status);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 22:55
     * @description: 查询所有未停用资产类别
     **/
    @Override
    public List<Map<String, Object>> getAssetClassOptions() {
        return assetClassMapper.getAssetClassOptions();
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 22:56
     * @description: 查询所有未停用的品牌
     **/
    @Override
    public List<Map<String, Object>> getBrandOptions() {
        return brandMapper.getBrandOptions();
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 22:58
     * @description: 查询所有未停用的存放位置信息
     */
    @Override
    public List<Map<String, Object>> getLocationOptions() {
        return locationMapper.getLocationOptions();
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/19 15:37
     * @description: 查询未停用的报废方式
     **/
    @Override
    public List<Map<String, Object>> getScrapModeOptions() {
        return scrapModeMapper.getScrapModeOptions();
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 22:59
     * @description: 查询未停用的部门
     **/
    @Override
    public List<Map<String, Object>> getSectionOptions() {
        return sectionMapper.getSectionOptions();
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 23:02
     * @description: 查询未停用的供应商
     **/
    @Override
    public List<Map<String, Object>> getSupplierOptions() {
        return supplierMapper.getSupplierOptions();
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 22:34
     * @description: 查询所有资产信息
     */
    @Override
    public List<Map<String, Object>> getAsset() {
        return assetMapper.getAsset();
    }

    /**
     * @param assetCode  资产编码
     * @param assetName  资产名称
     * @param classId    资产类别id
     * @param supplierId 供应商id
     * @param brandId    品牌id
     * @param locationId 存放地点id
     * @param date       日期
     * @param status     状态
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 22:45
     * @description: 根据条件查询资产信息
     **/
    @Override
    public List<Map<String, Object>> getAssetByCondition(String assetCode, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId, Date date, Integer status) {
        return assetMapper.getAssetByCondition(assetCode, assetName, classId, supplierId, brandId, locationId, date, status);
    }
    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 23:13
     * @description: 查询所有在库的资产
     */
    @Override
    public List<Map<String, Object>> getAssetOptions() {
        return assetMapper.getAssetOptions();
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/22 10:46
     * @description: 查询所有借用记录
     **/
    @Override
    public List<Map<String, Object>> getAssetRM() {
        return assetRepaymentMapper.getAssetRM();
    }

    /**
     * @param assetCode 资产编码
     * @param assetId   资产id
     * @param sectionId 部门id
     * @param classId   资产类别id
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/22 11:33
     * @description: 根据条件查询资产借用记录
     **/
    @Override
    public List<Map<String, Object>> getAssetRMByCondition(String assetCode, Integer assetId, Integer sectionId, Integer classId) {
        return assetRepaymentMapper.getAssetRMByCondition(assetCode, assetId, sectionId, classId);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/22 15:15
     * @description: 查询所有资产申购信息
     **/
    @Override
    public List<Map<String, Object>> getAssetRT() {
        return assetRequisitionMapper.getAssetRT();
    }

    /**
     * @param assetName    资产名称
     * @param className    资产类别名称
     * @param supplierName 供应商名称
     * @param brandName    品牌名称
     * @param argument     申请理由
     * @param budgetStart  预算头
     * @param budgetEnd    预算尾
     * @param result       审批结果
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/22 15:30
     * @description: 根据条件查询资产申购信息
     **/
    @Override
    public List<Map<String, Object>> getAssetRTByCondition(String assetName, String className, String supplierName, String brandName, String argument, String budgetStart, String budgetEnd, String result) {
        return assetRequisitionMapper.getAssetRTByCondition(assetName, className, supplierName, brandName, argument, budgetStart, budgetEnd, result);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/22 16:03
     * @description: 获取所有资产报废信息
     **/
    @Override
    public List<Map<String, Object>> getAssetScrap() {
        return assetScrapMapper.getAssetScrap();
    }

    /**
     * @param assetCode 资产编号
     * @param assetName 资产名称
     * @param scrapId   报废方式id
     * @param dateStart 日期头
     * @param dateEnd   日期尾
     * @param result    审批结果
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/22 16:29
     * @description:
     **/
    @Override
    public List<Map<String, Object>> getAssetScrapByCondition(String assetCode, String assetName, String scrapId, String dateStart, String dateEnd, String result) {
        return assetScrapMapper.getAssetScrapByCondition(assetCode, assetName, scrapId, dateStart, dateEnd, result);
    }
}
