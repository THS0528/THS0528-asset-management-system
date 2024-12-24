package org.example.asset_management.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.annotations.ResultMap;
import org.example.asset_management.service.SelectService;
import org.example.asset_management.service.impl.SelectServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 10:23:09
 * @description: 查询数据控制层
 */
@RestController
@RequestMapping("/select")
public class SelectController {
    private final SelectServiceImpl selectService;
    public SelectController(SelectServiceImpl selectService)
    {
        this.selectService = selectService;
    }
    //定义Json工具
    Gson gson =  new GsonBuilder()
            .setDateFormat("yyyy-MM-dd") // 设置日期格式为"yyyy-MM-dd"
            .create();

    @GetMapping("/getAssetClass")
    public String selectAssetClass(Integer userId) {
        List<Map<String, Object>> assetClass = selectService.getAssetClassByUserId(userId);
        if (assetClass == null){
            return "{\"code\":0,\"msg\":\"获取资产类别失败\"}";
        }
        String json = gson.toJson(assetClass);
        return "{\"code\":1,\"msg\":\"获取资产类别成功\",\"assetClass\":" + json + "}";
    }
    @GetMapping("/getAssetClassByCondition")
    private String selectAssetClassByCondition(String classId, String className, Integer status) {
        System.out.println("classId:"+classId +"classname" + className +"status"+ status);
        List<Map<String, Object>> assetClass = selectService.getAssetClassByCondition(classId, className, status);
        System.out.println(assetClass);
        if (!assetClass.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取资产类别成功\",\"assetClass\":" + gson.toJson(assetClass) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取资产类别成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getBrand")
    public String selectBrand(Integer userId) {
        List<Map<String, Object>> brand = selectService.getBrandByUserId(userId);
        if (brand == null){
            return "{\"code\":0,\"msg\":\"获取品牌失败\"}";
        }
        String json = gson.toJson(brand);
        return "{\"code\":1,\"msg\":\"获取品牌成功\",\"brand\":" + json + "}";
   }
   @GetMapping("/getBrandByCondition")
    public String selectBrandByCondition(String brandCode, String brandName, Integer status) {
        List<Map<String, Object>> brand = selectService.getBrandByCondition(brandCode, brandName, status);
        if (!brand.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取品牌成功\",\"brand\":" + gson.toJson(brand) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取品牌成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getSection")
    public String selectSection(){
        List<Map<String, Object>> section = selectService.getSection();
        if (section.isEmpty()){
            return "{\"code\":0,\"msg\":\"获取部门信息失败\"}";
        }else {
            return "{\"code\":1,\"msg\":\"获取部门信息成功\",\"section\":" + gson.toJson(section) + "}";
        }
    }
    @GetMapping("/getSectionByCondition")
    public String selectSectionByCondition(String sectionCode, String sectionName, Integer status) {
        List<Map<String, Object>> section = selectService.getSectionByCondition(sectionCode, sectionName, status);
        if (!section.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取部门信息成功\",\"section\":" + gson.toJson(section)+ "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取部门信息成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getScrapMode")
    public String selectScrapMode() {
        List<Map<String, Object>> scrapMode = selectService.getScrapMode();
        if (scrapMode == null){
            return "{\"code\":0,\"msg\":\"获取报废方式失败\"}";
        }
        String json = gson.toJson(scrapMode);
        return "{\"code\":1,\"msg\":\"获取报废方式成功\",\"scrapMode\":" + json + "}";
    }
    @GetMapping("/getScrapModeByCondition")
    public String selectScrapModeByCondition(String scrapModeCode, String scrapModeName, Integer status) {
        List<Map<String, Object>> scrapMode = selectService.getScrapModeByCondition(scrapModeCode, scrapModeName, status);
        if (!scrapMode.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取报废方式成功\",\"scrapMode\":" + gson.toJson(scrapMode) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取报废方式成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getSupplier")
    public String selectSupplier() {
        List<Map<String, Object>> supplier = selectService.getSupplier();
        if (supplier == null){
            return "{\"code\":0,\"msg\":\"获取供应商失败\"}";
        }
        String json = gson.toJson(supplier);
        return "{\"code\":1,\"msg\":\"获取供应商成功\",\"supplier\":" + json + "}";
    }
    @GetMapping("/getSupplierByCondition")
    public String selectSupplierByCondition(String supplierName, String supplierType, String linkman, String mp_number, String location, Integer status) {
        List<Map<String, Object>> supplier = selectService.getSupplierByCondition(supplierName, supplierType, linkman, mp_number, location, status);
        if (!supplier.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取供应商成功\",\"supplier\":" + gson.toJson(supplier) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取供应商成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getLocation")
    public String selectLocation() {
        List<Map<String, Object>> location = selectService.getLocation();
        if (location == null){
            return "{\"code\":0,\"msg\":\"获取位置失败\"}";
        }
        String json = gson.toJson(location);
        return "{\"code\":1,\"msg\":\"获取位置成功\",\"location\":" + json + "}";
    }
    @GetMapping("/getLocationByCondition")
    public String selectLocationByCondition(String locationName, String locationType, String locationInstructions, Integer status) {
        List<Map<String, Object>> location = selectService.getLocationByCondition(locationName, locationType, locationInstructions, status);
        if (!location.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取位置成功\",\"location\":" + gson.toJson(location) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取位置成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getAsset")
    public String selectAsset() {
        List<Map<String, Object>> asset = selectService.getAsset();
        if (asset == null){
            return "{\"code\":0,\"msg\":\"获取资产失败\"}";
        }
        String json = gson.toJson(asset);
        return "{\"code\":1,\"msg\":\"获取资产成功\",\"asset\":" + json + "}";
    }
    @GetMapping("/getAssetByCondition")
    public String selectAssetByCondition(String assetCode, String assetName, Integer classId, Integer supplierId, Integer brandId, Integer locationId, Date date, Integer status) {
        List<Map<String, Object>> asset = selectService.getAssetByCondition(assetCode, assetName, classId, supplierId, brandId, locationId, date, status);
        if (!asset.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取资产成功\",\"asset\":" + gson.toJson(asset) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取资产成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getAssetClassOptions")
    public String selectAssetClassOptions() {
        List<Map<String, Object>> assetClassOptions = selectService.getAssetClassOptions();
        if (assetClassOptions == null){
            return "{\"code\":0,\"msg\":\"获取资产分类选项失败\"}";
        }
        String json = gson.toJson(assetClassOptions);
        return "{\"code\":1,\"msg\":\"获取资产分类选项成功\",\"assetClassOptions\":" + json + "}";
    }
    @GetMapping("/getScrapModeOptions")
    public String selectScrapModeOptions() {
        List<Map<String, Object>> scrapModeOptions = selectService.getScrapModeOptions();
        if (scrapModeOptions == null){
            return "{\"code\":0,\"msg\":\"获取报废方式选项失败\"}";
        }
        String json = gson.toJson(scrapModeOptions);
        return "{\"code\":1,\"msg\":\"获取报废方式选项成功\",\"scrapModeOptions\":" + json + "}";
    }
    @GetMapping("/getBrandOptions")
    public String selectBrandOptions() {
        List<Map<String, Object>> brandOptions = selectService.getBrandOptions();
        if (brandOptions == null){
            return "{\"code\":0,\"msg\":\"获取品牌选项失败\"}";
        }
        String json = gson.toJson(brandOptions);
        return "{\"code\":1,\"msg\":\"获取品牌选项成功\",\"brandOptions\":" + json + "}";
    }
    @GetMapping("/getSupplierOptions")
    public String selectSupplierOptions() {
        List<Map<String, Object>> supplierOptions = selectService.getSupplierOptions();
        if (supplierOptions == null){
            return "{\"code\":0,\"msg\":\"获取供应商选项失败\"}";
        }
        String json = gson.toJson(supplierOptions);
        return "{\"code\":1,\"msg\":\"获取供应商选项成功\",\"supplierOptions\":" + json + "}";
    }
    @GetMapping("/getLocationOptions")
    public String selectLocationOptions() {
        List<Map<String, Object>> locationOptions = selectService.getLocationOptions();
        if (locationOptions == null){
            return "{\"code\":0,\"msg\":\"获取位置选项失败\"}";
        }
        String json = gson.toJson(locationOptions);
        return "{\"code\":1,\"msg\":\"获取位置选项成功\",\"locationOptions\":" + json + "}";
    }
    @GetMapping("/getSectionOption")
    public String selectSectionOptions() {
        List<Map<String, Object>> sectionOptions = selectService.getSectionOptions();
        if (sectionOptions == null){
            return "{\"code\":0,\"msg\":\"获取部门选项失败\"}";
        }
        String json = gson.toJson(sectionOptions);
        return "{\"code\":1,\"msg\":\"获取部门选项成功\",\"sectionOptions\":" + json + "}";
    }
    @GetMapping("/getAssetRT")
    public String selectAssetRT() {
        List<Map<String, Object>> assetRT = selectService.getAssetRT();
        if (assetRT == null){
            return "{\"code\":0,\"msg\":\"获取资产领用失败\"}";
        }
        String json = gson.toJson(assetRT);
        return "{\"code\":1,\"msg\":\"获取资产领用成功\",\"assetRT\":" + json + "}";
    }
    @GetMapping("/getAssetRM")
    public String selectAssetRM() {
        List<Map<String, Object>> assetRM = selectService.getAssetRM();
        if (assetRM == null){
            return "{\"code\":0,\"msg\":\"获取资产归还失败\"}";
        }
        String json = gson.toJson(assetRM);
        return "{\"code\":1,\"msg\":\"获取资产归还成功\",\"assetRM\":" + json + "}";
    }
    @GetMapping("/getAssetScrap")
    public String selectAssetScrap() {
        List<Map<String, Object>> assetScrap = selectService.getAssetScrap();
        if (assetScrap == null){
            return "{\"code\":0,\"msg\":\"获取资产报废失败\"}";
        }
        String json = gson.toJson(assetScrap);
        return "{\"code\":1,\"msg\":\"获取资产报废成功\",\"assetScrap\":" +json + "}";
    }
    @GetMapping("/getAssetRTByCondition")
    public String selectAssetRTByCondition(String assetName, String className, String supplierName, String brandName, String argument, String applicant, String budgetStart,String budgetEnd, String result) {
        List<Map<String, Object>> assetRT = selectService.getAssetRTByCondition(assetName, className, supplierName, brandName, argument,  budgetStart, budgetEnd, result);
        if (!assetRT.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取资产领用成功\",\"assetRT\":" + gson.toJson(assetRT) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取资产领用成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getAssetRMByCondition")
    public String selectAssetRMByCondition(String assetCode, Integer assetId, Integer sectionId, Integer classId) {
        List<Map<String, Object>> assetRM = selectService.getAssetRMByCondition(assetCode, assetId, sectionId, classId);
        if (!assetRM.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取资产归还成功\",\"assetRM\":" + gson.toJson(assetRM) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取资产归还成功，但没有符合体条件的数据\"}";
        }
    }
    @GetMapping("/getAssetScrapByCondition")
    public String selectAssetScrapByCondition(String assetCode, String assetName, String scrapId, String dateStart, String dateEnd, String result) {
        List<Map<String, Object>> assetScrap = selectService.getAssetScrapByCondition(assetCode, assetName, scrapId, dateStart, dateEnd, result);
        if (!assetScrap.isEmpty()){
            return "{\"code\":1,\"msg\":\"获取资产报废成功\",\"assetScrap\":" + gson.toJson(assetScrap) + "}";
        }else {
            return "{\"code\":1,\"msg\":\"获取资产报废成功，但没有符合体条件的数据\",\"assetScrap\":[]}";
        }
    }
    @GetMapping("/getAssetOption")
    public String selectAssetOption() {
        List<Map<String, Object>> assetOptions = selectService.getAssetOptions();
        if (assetOptions == null){
            return "{\"code\":0,\"msg\":\"获取资产选项失败\"}";
        }
        String json = gson.toJson(assetOptions);
        return "{\"code\":1,\"msg\":\"获取资产选项成功\",\"assetOptions\":" + json + "}";
    }

}
