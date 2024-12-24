package org.example.asset_management.controller;

import org.example.asset_management.entity.*;
import org.example.asset_management.service.impl.SelectServiceImpl;
import org.example.asset_management.service.impl.UpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 22:10:33
 * @description: 更新的控制器
 */
@RestController
@RequestMapping("/update")
public class UpdateController {
    private final UpdateServiceImpl updateService;
    private final SelectServiceImpl selectService;
    @Autowired
    public UpdateController(UpdateServiceImpl updateService, SelectServiceImpl selectService) {
        this.updateService = updateService;
        this.selectService = selectService;
    }
    //更新资产类别状态
    @PostMapping("/updateAssetClassStatus")
    public String updateAssetClassStatus(@RequestBody AssetClass assetClass) {
        Integer classId = assetClass.getClassId();
        Integer status = assetClass.getStatus();
        int row = updateService.updateAssetClassStatus(classId, status);
        System.out.println(row);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"状态更新失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"状态更新成功\"}";
        }
    }
    //更新资产类别
    @PostMapping("/updateAssetClass")
    public String updateAssetClass(@RequestBody AssetClass assetClass) {
        Integer classId = assetClass.getClassId();
        String className = assetClass.getClassName();
        String classCode = assetClass.getClassCode();
        if (selectService.getAssetClassByClassCode(classCode).size() > 1) {
            return "{\"code\":0,\"msg\":\"资产编号已存在\"}";
        }
        int row = updateService.updateAssetClassByClassId(classId, classCode, className);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"修改数据失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"修改数据成功\"}";
        }
    }
    //更新品牌状态
    @PostMapping("/updateBrandStatus")
    public String updateBrandStatus(@RequestBody AssetClass assetClass) {
        Integer brandId = assetClass.getClassId();
        Integer status = assetClass.getStatus();
        int row = updateService.updateBrandStatus(brandId, status);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"状态更新失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"状态更新成功\"}";
        }
    }
    @PostMapping("/updateBrand")
    public String updateBrand(@RequestBody Brand brand) {
        Integer brandId = brand.getBrandId();
        String brandCode = brand.getBrandCode();
        String brandName = brand.getBrandName();
        if (selectService.getBrandByBrandCode(brandCode).size() > 1) {
            return "{\"code\":0,\"msg\":\"品牌编号已存在\"}";
        }
        int row = updateService.updateBrandByBrandId(brandId, brandCode, brandName);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"修改数据失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"修改数据成功\"}";
        }
    }
    @PostMapping("/updateSectionStatus")
    public String updateSectionStatus(@RequestBody Section section) {
        Integer sectionId = section.getSectionId();
        Integer status = section.getStatus();
        int row = updateService.updateSectionStatus(sectionId, status);
        System.out.println("row:"+row);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"状态更新失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"状态更新成功\"}";
        }
    }
    @PostMapping("/updateSection")
    public String updateSection(@RequestBody Section section) {
        Integer sectionId = section.getSectionId();
        String sectionCode = section.getSectionCode();
        String sectionName = section.getSectionName();
        if (selectService.getBrandByBrandCode(sectionCode).size() > 1) {
            return "{\"code\":0,\"msg\":\"区域编号已存在\"}";
        }
        int row = updateService.updateSectionBySectionId(sectionId, sectionCode, sectionName);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"修改数据失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"修改数据成功\"}";
        }
    }
    @PostMapping("/updateScrapModeStatus")
    public String updateScrapModeStatus(@RequestBody ScrapMode scrapMode) {
        Integer scrapModeId = scrapMode.getScrapModeId();
        Integer status = scrapMode.getStatus();
        int row = updateService.updateScrapModeStatus(scrapModeId, status);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"状态更新失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"状态更新成功\"}";
        }
    }
    @PostMapping("/updateScrapMode")
    public String updateScrapMode(@RequestBody ScrapMode scrapMode) {
        System.out.println("实体："+scrapMode);
        Integer scrapModeId = scrapMode.getScrapModeId();
        String scrapModeCode = scrapMode.getScrapModeCode();
        String scrapModeName = scrapMode.getScrapModeName();
        if (selectService.getScrapModeByScrapModeCode(scrapModeCode).size() > 1) {
            return "{\"code\":0,\"msg\":\"报废方式编号已存在\"}";
        }
        int row = updateService.updateScrapModeByScrapModeId(scrapModeId, scrapModeCode, scrapModeName);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"修改数据失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"修改数据成功\"}";
        }
    }
    @PostMapping("/updateSupplierStatus")
    public String updateSupplierStatus(@RequestBody Supplier supplier) {
        Integer supplierId = supplier.getSupplierId();
        Integer status = supplier.getStatus();
        System.out.println(supplierId+":"+status);
        int row = updateService.updateSupplierStatus(supplierId, status);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"状态更新失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"状态更新成功\"}";
        }
    }
    @PostMapping("/updateSupplier")
    public String updateSupplier(@RequestBody Supplier supplier) {
        Integer supplierId = supplier.getSupplierId();
        String supplierName = supplier.getSupplierName();
        String supplierType = supplier.getSupplierType();
        String linkman = supplier.getLinkman();
        String mp_number = supplier.getMp_number();
        String location = supplier.getLocation();
        System.out.println(supplierId+":"+supplierName+":"+supplierType+":"+linkman+":"+mp_number+":"+location);
        int row = updateService.updateSupplier(supplierId, supplierName, supplierType, linkman, mp_number, location);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"修改数据失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"修改数据成功\"}";
        }
    }
    @PostMapping("/updateLocationStatus")
    public String updateLocationStatus(@RequestBody Location location) {
        Integer locationId = location.getLocationId();
        Integer status = location.getStatus();
        int row = updateService.updateLocationStatus(locationId, status);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"状态更新失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"状态更新成功\"}";
        }
    }
    @PostMapping("updateAsset")
    public String updateAsset(@RequestBody Asset asset) {
        Integer assetId = asset.getAssetId();
        String assetName = asset.getAssetName();
        Integer classId = asset.getClassId();
        Integer supplierId = asset.getSupplierId();
        Integer brandId = asset.getBrandId();
        Integer locationId = asset.getLocationId();
        System.out.println(assetId+":"+assetName+":"+classId+":"+supplierId+":"+brandId+":"+locationId);
        int row = updateService.updateAsset(assetId, assetName, classId, supplierId, brandId, locationId);
        if (row == 0){
            return "{\"code\": 0,\"msg\":\"修改数据失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"修改数据成功\"}";
        }
    }
    @PostMapping("/updateAssetRM")
    public String updateAssetRM(@RequestBody AssetRepayment assetRepayment) {
        String borrowedCode = assetRepayment.getBorrowedCode();
        Date backDate = assetRepayment.getBackDate();
        String backRemark = assetRepayment.getBackRemark();
        System.out.println(borrowedCode+":"+backDate);
        int row = updateService.updateAssetRM(borrowedCode, backDate,backRemark);
        updateService.updateAssetStatus(assetRepayment.getAssetId(), "在库");
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"资产归还失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"资产归还成功\"}";
        }
    }
    @PostMapping("/updateAssetRT")
    public String updateAssetRT(@RequestBody AssetRequisition assetRequisition) {
        Integer id = assetRequisition.getId();
        String result = assetRequisition.getResult();
        System.out.println(id+":"+result);
        int row = updateService.updateAssetRT(id, result);
        if (row == 0) {
            return "{\"code\": 0,\"msg\":\"申购审批失败\"}";
        }else {
            return "{\"code\": 1,\"msg\":\"申购审批成功\"}";
        }
    }
    @PostMapping("/updateAssetScrap")
        public String updateAssetScrap(@RequestBody AssetScrap assetScrap) {
                int ID = assetScrap.getId();
                String RESULT = assetScrap.getResult();
                Integer assetId = assetScrap.getAssetId();
                System.out.println(ID+":"+RESULT);
                int row = updateService.updateAssetScrap(ID, RESULT);
                if (row == 0) {
                    return "{\"code\": 0,\"msg\":\"报废审批失败\"}";
                }else {
                    if ("审批不通过".equals(RESULT)){
                        updateService.updateAssetStatus(assetId, "在库");
                    }
                    if ("审批通过".equals(RESULT)){
                        updateService.updateAssetStatus(assetId, "已报废");
                    }
                    return "{\"code\":1,\"msg\":\"报废审批成功\"}";
        }
    }
}
