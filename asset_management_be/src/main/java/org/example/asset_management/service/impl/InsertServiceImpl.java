package org.example.asset_management.service.impl;

import lombok.Data;
import org.example.asset_management.dao.*;
import org.example.asset_management.service.InsertService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:39:00
 * @description: 插入服务实现类
 */
@Service
public class InsertServiceImpl implements InsertService {
    private AssetClassMapper assetClassMapper;
    private BrandMapper brandMapper;
    private SectionMapper sectionMapper;
    private ScrapModeMapper scrapModeMapper;
    private SupplierMapper supplierMapper;
    private LocationMapper locationMapper;
    private AssetMapper assetMapper;
    private AssetRequisitionMapper assetRequisitionMapper;
    private AssetRepaymentMapper assetRepaymentMapper;
    private AssetScrapMapper assetScrapMapper;
    public InsertServiceImpl(AssetClassMapper assetClassMapper, BrandMapper brandMapper,
                             SectionMapper sectionMapper, ScrapModeMapper scrapModeMapper,
                             SupplierMapper supplierMapper, LocationMapper locationMapper,
                             AssetMapper assetMapper, AssetRequisitionMapper assetRequisitionMapper,
                             AssetRepaymentMapper assetRepaymentMapper, AssetScrapMapper assetScrapMapper) {
        this.assetClassMapper = assetClassMapper;
        this.brandMapper = brandMapper;
        this.sectionMapper = sectionMapper;
        this.scrapModeMapper = scrapModeMapper;
        this.supplierMapper = supplierMapper;
        this.locationMapper = locationMapper;
        this.assetMapper = assetMapper;
        this.assetRequisitionMapper = assetRequisitionMapper;
        this.assetRepaymentMapper = assetRepaymentMapper;
        this.assetScrapMapper = assetScrapMapper;
    }

    /**
     * @param classCode   资产类别编码
     * @param className 资产类别名称
     * @param userId    用户id
     * @param status    状态
     * @return int
     * @author THS.
     * @date 2024/4/17 11:16
     * @description: 用来插入资产类别
     **/
    @Override
    public int insertAssetClass(String classCode, String className, Integer userId, Integer status) {
        if (classCode == null || !classCode.matches("^[a-zA-Z0-9]{1,10}$")) {
            System.out.println("资产类别编码不符合要求");
            return 0;
        }
        if (className == null || !className.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")) {
            return 0;
        }
        if (userId == null || userId < 0) {
            System.out.println("用户id不符合要求");
            return 0;
        }
        if (status == null || (status != 0 && status != 1)) {
            System.out.println("状态不符合要求");
            return 0;
        }
        System.out.println("插入资产类别");;
        return assetClassMapper.insertAssetClass(classCode, className, userId, status);
    }

    /**
     * @param brandCode 品牌id
     * @param brandName 品牌名称
     * @param userId    用户id
     * @param status    状态
     * @return int
     * @author THS.
     * @date 2024/4/18 21:25
     * @description: 插入品牌信息
     **/
    @Override
    public int insertBrand(String brandCode, String brandName, Integer userId, Integer status) {
        if (brandCode == null || !brandCode.matches("^[a-zA-Z0-9]{1,10}$")){
            System.out.println("品牌编码不符合要求");
            return 0;
        }
        if (brandName == null || !brandName.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("品牌名称不符合要求");
            return 0;
        }
        if (userId == null || userId < 0){
            System.out.println("用户id不符合要求");
            return 0;
        }
        if (status == null || (status != 0 && status != 1)){
            System.out.println("状态不符合要求");
            return 0;
        }
        return brandMapper.insertBrand(brandCode, brandName, userId, status);
    }

    /**
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @param status      状态
     * @return int
     * @author THS.
     * @date 2024/4/19 16:10
     * @description: 新增部门信息
     **/
    @Override
    public int insertSection(String sectionCode, String sectionName, Integer status) {
        if (sectionCode == null || !sectionCode.matches("^[a-zA-Z0-9]{1,10}$")){
            System.out.println("部门编码不符合要求");
            return 0;
        }
        if (sectionName == null || !sectionName.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("部门名称不符合要求");
            return 0;
        }
        if (status == null || (status != 0 && status != 1)){
            System.out.println("状态不符合要求");
            return 0;
        }
        return sectionMapper.insertSection(sectionCode, sectionName, status);
    }

    /**
     * @param scrapModeCode 报废方式编码
     * @param scrapModeName 报废方式名称
     * @param status        状态
     * @return int
     * @author THS.
     * @date 2024/4/19 16:10
     * @description: 新增报废方式
     **/
    @Override
    public int insertScrapMode(String scrapModeCode, String scrapModeName, Integer status) {
        if (scrapModeCode == null || !scrapModeCode.matches("^[a-zA-Z0-9]{1,10}$")){
            System.out.println("报废方式编码不符合要求");
            return 0;
        }
        if (scrapModeName == null || !scrapModeName.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("报废方式名称不符合要求");
            return 0;
        }
        if (status == null || (status != 0 && status != 1)){
            System.out.println("状态不符合要求");
            return 0;
        }
        return scrapModeMapper.insertScrapMode(scrapModeCode, scrapModeName, status);
    }

    /**
     * @param supplierName 供应商名称
     * @param supplierType 供应商类型
     * @param linkman      联系人
     * @param mp_number    联系人电话
     * @param location     地址
     * @param status       状态
     * @return int
     * @author THS.
     * @date 2024/4/20 20:40
     * @description: 添加供应商
     **/
    @Override
    public int insertSupplier(String supplierName, String supplierType, String linkman, String mp_number, String location, Integer status) {
        if (supplierName == null || !supplierName.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("供应商名称不符合要求");
            return 0;
        }
        if (supplierType == null || !supplierType.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("供应商类型不符合要求");
            return 0;
        }
        if (linkman == null || !linkman.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("联系人不符合要求");
            return 0;
        }
        if (mp_number == null || !mp_number.matches("^[0-9]{11}$")){
            System.out.println("联系人电话不符合要求");
            return 0;
        }
        if (location == null || !location.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,100}$")){
            System.out.println("地址不符合要求");
            return 0;
        }
        if (status == null || (status != 0 && status != 1)){
            System.out.println("状态不符合要求");
            return 0;
        }
        return supplierMapper.insertSupplier(supplierName, supplierType, linkman, mp_number, location, status);
    }

    /**
     * @param locationName         存放地点名称
     * @param locationType         存放地点类型
     * @param locationInstructions 存放地点说明
     * @param status               状态
     * @return int
     * @author THS.
     * @date 2024/4/21 19:49
     * @description: 更新存放位置信息
     **/
    @Override
    public int insertLocation(String locationName, String locationType, String locationInstructions, Integer status) {
        if (locationName == null || !locationName.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("存放地点名称不符合要求");
            return 0;
        }
        if (locationType == null || !locationType.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("存放地点类型不符合要求");
            return 0;
        }
        if (locationInstructions == null ){
            locationInstructions = "";
        }
        if (status == null || (status != 0 && status != 1)){
            System.out.println("状态不符合要求");
            return 0;
        }
        return locationMapper.insertLocation(locationName, locationType, locationInstructions, status);
    }

    /**
     * @param assetCode  资产编码
     * @param assetName  资产名称
     * @param classId    资产类别id
     * @param supplierId 供应商id
     * @param brandId    品牌id
     * @param locationId 存放地址id
     * @param date       日期
     * @param status     状态
     * @return int
     * @author THS.
     * @date 2024/4/21 22:48
     * @description: 添加资产
     **/
    @Override
    public int insertAsset(String assetCode, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId, Date date, String status) {
        if (assetCode == null || !assetCode.matches("^[a-zA-Z0-9]{1,16}$")){
            System.out.println("资产编码不符合要求");
            return 0;
        }
        if (assetName == null || !assetName.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("资产名称不符合要求");
            return 0;
        }
        if (classId == null || classId < 0){
            System.out.println("资产类别id不符合要求");
            return 0;
        }
        if (supplierId == null || supplierId < 0){
            System.out.println("供应商id不符合要求");
            return 0;
        }
        if (brandId == null || brandId < 0){
            System.out.println("品牌id不符合要求");
            return 0;
        }
        if (locationId == null || locationId < 0){
            System.out.println("存放地址id不符合要求");
            return 0;
        }
        if (date == null){
            System.out.println("日期不符合要求");
            return 0;
        }
        if (status == null){
            System.out.println("状态不符合要求");
            return 0;
        }
        return assetMapper.insertAsset(assetCode, assetName, classId, supplierId, brandId, locationId, date, status);
    }

    /**
     * @param borrowedCode 借用编号
     * @param assetId      资产id
     * @param loanDate     借用日期
     * @param sectionId    部门id
     * @param cause        借用原因
     * @return int
     * @author THS.
     * @date 2024/4/22 14:54
     * @description: 添加资产借用记录
     **/
    @Override
    public int addAssetRM(String borrowedCode, Integer assetId, Date loanDate, Integer sectionId, String cause) {
        if (borrowedCode == null || !borrowedCode.matches("^[a-zA-Z0-9]{1,16}$")){
            System.out.println("借用编号不符合要求");
            return 0;
        }
        if (assetId == null || assetId < 0){
            System.out.println("资产id不符合要求");
            return 0;
        }
        if (loanDate == null){
            System.out.println("借用日期不符合要求");
            return 0;
        }
        if (sectionId == null || sectionId < 0){
            System.out.println("部门id不符合要求");
            return 0;
        }
        if (cause == null){
            System.out.println("借用原因不符合要求");
            return 0;
        }
        return assetRepaymentMapper.addAssetRM(borrowedCode, assetId, loanDate, sectionId, cause);
    }

    /**
     * @param assetName  资产名称
     * @param classId    资产类别id
     * @param supplierId 供应商id
     * @param brandId    品牌id
     * @param argument   申请理由
     * @param applicant  申请人
     * @param budget     申购预算
     * @param result     审批结果
     * @return int
     * @author THS.
     * @date 2024/4/22 15:33
     * @description: 添加资产申购信息
     **/
    @Override
    public int addAssetRT(String assetName, Integer classId, Integer supplierId, Integer brandId, String argument, String applicant, String budget, String result) {
        if (assetName == null || !assetName.matches("^[a-zA-Z\\u4e00-\\u9fa51-9]{1,10}$")){
            System.out.println("资产名称不符合要求");
            return 0;
        }
        if (classId == null || classId < 0){
            System.out.println("资产类别id不符合要求");
            return 0;
        }
        if (supplierId == null || supplierId < 0){
            System.out.println("供应商id不符合要求");
            return 0;
        }
        if (brandId == null || brandId < 0){
            System.out.println("品牌id不符合要求");
            return 0;
        }
        if (argument == null){
            System.out.println("申请理由不符合要求");
            return 0;
        }
        if (applicant == null ){
            System.out.println("申请人不符合要求");
            return 0;
        }
        if (budget == null || !budget.matches("^[0-9.]{1,10}$")){
            System.out.println("申购预算不符合要求");
            return 0;
        }
        if (result == null){
            System.out.println("审批结果不符合要求");
            return 0;
        }
        return assetRequisitionMapper.addAssetRT(assetName, classId, supplierId, brandId, argument, applicant, budget, result);
    }

    /**
     * @param assetId   资产id
     * @param scrapId   报废方式id
     * @param date      报废日期
     * @param cause     报废原因
     * @param applicant 申请人
     * @param result    审批结果
     * @return int
     * @author THS.
     * @date 2024/4/22 16:35
     * @description:
     **/
    @Override
    public int addAssetScrap(Integer assetId, String scrapId, Date date, String cause, String applicant, String result) {
        if (assetId == null ){
            System.out.println("资产id不符合要求");
            return 0;
        }
        if (scrapId == null ){
            System.out.println("报废方式id不符合要求");
            return 0;
        }
        if (date == null){
            System.out.println("报废日期不符合要求");
            return 0;
        }
        if (cause == null){
            System.out.println("报废原因不符合要求");
            return 0;
        }
        if (applicant == null){
            System.out.println("申请人不符合要求");
            return 0;
        }
        if (result == null){
            System.out.println("审批结果不符合要求");
            return 0;
        }
        return assetScrapMapper.addAssetScrap(assetId, scrapId, date, cause, applicant, result);
    }
}
