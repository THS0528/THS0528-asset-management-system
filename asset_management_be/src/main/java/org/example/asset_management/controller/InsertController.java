package org.example.asset_management.controller;

import lombok.Data;
import org.example.asset_management.dao.AssetRequisitionMapper;
import org.example.asset_management.entity.*;
import org.example.asset_management.service.impl.InsertServiceImpl;
import org.example.asset_management.service.impl.SelectServiceImpl;
import org.example.asset_management.service.impl.UpdateServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 11:11:32
 * @description:
 */
@RestController
@RequestMapping("/insert")
public class InsertController {
    private final InsertServiceImpl insertService;
    private final SelectServiceImpl selectService;
    public final UpdateServiceImpl updateService;
    public InsertController(InsertServiceImpl insertService, SelectServiceImpl selectService,
                            UpdateServiceImpl updateService) {
        this.insertService = insertService;
        this.selectService = selectService;
        this.updateService = updateService;
    }
    @PostMapping("/insertAssetClass")
    public String insertAssetClass(@RequestBody AssetClass assetClass) {
        //获取数据
        String assetCode = assetClass.getClassCode();
        String assetName = assetClass.getClassName();
        int userId = assetClass.getUserId();
        int status = assetClass.getStatus();
        if (!selectService.getAssetClassByClassCode(assetCode).isEmpty()) {
            return "{\"code\":0,\"msg\":\"资产编号已存在\"}";
        }
        int row = insertService.insertAssetClass(assetCode, assetName, userId, status);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"资产类型新增失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"资产类型新增成功\"}";
        }
    }
    @PostMapping("/insertBrand")
    public String insertBrand(@RequestBody Brand brand) {
        String brandCode = brand.getBrandCode();
        String brandName = brand.getBrandName();
        int userId = brand.getUserId();
        int status = brand.getStatus();
        if (!selectService.getBrandByBrandCode(brandCode).isEmpty()) {
            return "{\"code\":0,\"msg\":\"品牌编号已存在\"}";
        }
        int row = insertService.insertBrand(brandCode, brandName, userId, status);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"品牌新增失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"品牌新增成功\"}";
        }
    }
    @PostMapping("/insertSection")
    public String insertSection(@RequestBody Section section) {
        String sectionCode = section.getSectionCode();
        String sectionName = section.getSectionCode();
        int status = section.getStatus();
        if (!selectService.getSectionBySectionCode(sectionCode).isEmpty()) {
            return "{\"code\":0,\"msg\":\"部门编号已存在\"}";
        }
        int row = insertService.insertSection(sectionCode, sectionName, status);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"部门新增失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"部门新增成功\"}";
        }
    }
    @PostMapping("/insertScrapMode")
    public String insertScrapMode(@RequestBody ScrapMode scrapMode) {
        String scrapModeCode = scrapMode.getScrapModeCode();
        String scrapModeName = scrapMode.getScrapModeName();
        int status = scrapMode.getStatus();
        if (!selectService.getScrapModeByScrapModeCode(scrapModeCode).isEmpty()) {
            return "{\"code\":0,\"msg\":\"报废方式编号已存在\"}";
        }
        int row = insertService.insertScrapMode(scrapModeCode, scrapModeName, status);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"报废方式新增失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"报废方式新增成功\"}";
        }
    }
    @PostMapping("/insertSupplier")
    public String insertSupplier(@RequestBody Supplier supplier) {
        String supplierName = supplier.getSupplierName();
        String supplierType = supplier.getSupplierType();
        String linkman = supplier.getLinkman();
        String mp_number = supplier.getMp_number();
        String location = supplier.getLocation();
        int status = supplier.getStatus();
        int row = insertService.insertSupplier(supplierName, supplierType, linkman, mp_number, location, status);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"供应商新增失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"供应商新增成功\"}";
        }
    }
    @PostMapping("/insertLocation")
    public String insertLocation(@RequestBody Location location) {
        String locationName = location.getLocationName();
        String locationType = location.getLocationType();
        String locationInstructions = location.getLocationInstructions();
        int status = location.getStatus();
        int row = insertService.insertLocation(locationName, locationType, locationInstructions, status);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"存放地点新增失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"存放地点新增成功\"}";
        }
    }
    @PostMapping("/insertAsset")
    public String insertAsset(@RequestBody Asset asset) {
        System.out.println("xinzeng");
        String assetName = asset.getAssetName();
        int classId = asset.getClassId();
        int supplierId = asset.getSupplierId();
        int brandId = asset.getBrandId();
        int locationId = asset.getLocationId();
        Date date = asset.getDate();
        String status = asset.getStatus();
        String assetCode = "zc";
        // 获取当前时间
        LocalDateTime time = LocalDateTime.now();
        //设置时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //格式化时间
        String now = time.format(formatter);
        assetCode = assetCode + now;
        System.out.println(assetName + " " + classId + " "+assetCode + " " + supplierId + " " + brandId + " " + locationId + " " + date + " " + status);
        int row = insertService.insertAsset(assetCode, assetName, classId, supplierId, brandId, locationId, date, status);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"资产新增失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"资产新增成功\"}";
        }
    }
    @PostMapping("/insertAssetRT")
    public String insertAssetRT(@RequestBody AssetRequisition assetRequisition) {
        String assetName = assetRequisition.getAssetName();
        int classId = assetRequisition.getClassId();
        int supplierId = assetRequisition.getSupplierId();
        int brandId = assetRequisition.getBrandId();
        String argument = assetRequisition.getArgument();
        String applicant = assetRequisition.getApplicant();
        String budget = assetRequisition.getBudget();
        String result = assetRequisition.getResult();
        int row = insertService.addAssetRT(assetName, classId, supplierId, brandId, argument, applicant, budget, result);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"资产申购失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"资产申购成功\"}";
        }
    }
    @PostMapping("/insertAssetRM")
    public String insertAssetRM(@RequestBody AssetRepayment assetRepayment) {
        int assetId = assetRepayment.getAssetId();
        Date loanDate = assetRepayment.getLoanDate();
        int sectionId = assetRepayment.getSectionId();
        String cause = assetRepayment.getCause();
        // 获取当前时间
        LocalDateTime time = LocalDateTime.now();
        //设置时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //格式化时间
        String now = time.format(formatter);
        String borrowedCode = "jy"+now;
        //资产状态设置为已借出
        updateService.updateAssetStatus(assetId, "已借出");
        int row = insertService.addAssetRM(borrowedCode, assetId, loanDate, sectionId, cause);
        if (row == 0){
            return "{\"code\":0,\"msg\":\"资产借用失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"资产借用成功\"}";
        }
    }
    @PostMapping("/insertAssetScrap")
    public String insertAssetScrap(@RequestBody AssetScrap assetScrap) {
        System.out.println(assetScrap.toString());
        Integer assetId = assetScrap.getAssetId();
        String scrapId = assetScrap.getScrapId();
        Date date = assetScrap.getDate();
        String cause = assetScrap.getCause();
        String applicant = assetScrap.getApplicant();
        String result = assetScrap.getResult();
        int row = insertService.addAssetScrap(assetId, scrapId, date, cause, applicant, result);
        updateService.updateAssetStatus(assetId, "报废待审批");
        if (row == 0){
            return "{\"code\":0,\"msg\":\"资产申请报废失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"资产申请报废成功\"}";
        }
    }
}
