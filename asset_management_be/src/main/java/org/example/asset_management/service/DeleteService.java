package org.example.asset_management.service;

import org.apache.ibatis.annotations.Delete;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:29:03
 * @description: 删除数据业务逻辑
 */
public interface DeleteService {
    /**
     * @author THS.
     * @param supplierId 供应商id
     * @return int
     * @date 2024/4/20 20:44
     * @description: 根据供应商id删除供应商
     **/
    int deleteSupplier(Integer supplierId);
    /**
     * @author THS.
     * @param assetId 资产id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:51
     * @description: 根据资产id查询资产信息
     **/
    int deleteAsset(Integer assetId);
    /**
     * @author THS.
     * @param assetRTId 资产申购id
     * @return int
     * @date 2024/4/22 15:38
     * @description: 删除资产申购信息
     **/
    int deleteAssetRT(Integer assetRTId);
    /**
     * @author THS.
     * @param id 资产报废id
     * @return int
     * @date 2024/4/22 16:36
     * @description: 根据id删除资产报废信息
     **/
    int deleteAssetScrap(String id);
}
