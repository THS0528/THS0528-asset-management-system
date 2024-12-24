package org.example.asset_management.controller;

import org.example.asset_management.service.DeleteService;
import org.example.asset_management.service.impl.DeleteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/20 23:10:33
 * @description: 删除控制器
 */
@RequestMapping("/delete")
@RestController
public class DeleteController {
    private final DeleteServiceImpl deleteService;
    public DeleteController(DeleteServiceImpl deleteService) {
        this.deleteService = deleteService;
    }
    @GetMapping("/deleteSupplier")
    public String deleteSupplier(Integer supplierId) {

        int row = deleteService.deleteSupplier(supplierId);
        if (row > 0){
            return "{\"code\":1,\"msg\":\"供应商删除成功\"}";
        }else {
            return "{\"code\":0,\"msg\":\"供应商删除失败\"}";
        }
    }
    @GetMapping("/deleteAsset")
    public String deleteAsset(Integer assetId) {
        int row = deleteService.deleteAsset(assetId);
        if (row > 0){
            return "{\"code\":1,\"msg\":\"资产删除成功\"}";
        }else {
            return "{\"code\":0,\"msg\":\"资产删除失败\"}";
        }
    }
    @GetMapping("/deleteAssetRT")
    public String deleteAssetRT(Integer assetRTId) {
        System.out.println(assetRTId);
        int row = deleteService.deleteAssetRT(assetRTId);
        if (row > 0){
            return "{\"code\":1,\"msg\":\"资产调拨删除成功\"}";
        }else {
            return "{\"code\":0,\"msg\":\"资产调拨删除失败\"}";
        }
    }
    @GetMapping("/deleteAssetScrap")
    public String deleteAssetScrap(String id) {
        int row = deleteService.deleteAssetScrap(id);
        if (row > 0){
            return "{\"code\":1,\"msg\":\"资产报废删除成功\"}";
        }else {
            return "{\"code\":0,\"msg\":\"资产报废删除失败\"}";
        }
    }

}
