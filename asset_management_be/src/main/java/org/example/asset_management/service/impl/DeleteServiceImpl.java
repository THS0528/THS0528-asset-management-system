package org.example.asset_management.service.impl;


import org.example.asset_management.dao.*;
import org.example.asset_management.service.DeleteService;
import org.springframework.stereotype.Service;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 09:32:11
 * @description: 删除业务实现类
 */
@Service
public class DeleteServiceImpl implements DeleteService {
    private final SupplierMapper sectionMapper;
    private final AssetMapper assetMapper;
    private final AssetRequisitionMapper assetRequisitionMapper;
    private final AssetScrapMapper assetScrapMapper;
    public DeleteServiceImpl(SupplierMapper sectionMapper, AssetMapper assetMapper,
                             AssetRequisitionMapper assetRequisitionMapper,
                             AssetScrapMapper assetScrapMapper) {
        this.sectionMapper = sectionMapper;
        this.assetMapper = assetMapper;
        this.assetRequisitionMapper = assetRequisitionMapper;
        this.assetScrapMapper = assetScrapMapper;
    }

    /**
     * @param supplierId 供应商id
     * @return int
     * @author THS.
     * @date 2024/4/20 20:44
     * @description: 根据供应商id删除供应商
     **/
    @Override
    public int deleteSupplier(Integer supplierId) {
        if (supplierId == null){
            return 0;
        }
        return sectionMapper.deleteSupplier(supplierId);
    }

    /**
     * @param assetId 资产id
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/21 22:51
     * @description: 根据资产id查询资产信息
     **/
    @Override
    public int deleteAsset(Integer assetId) {
        if (assetId == null){
         return 0;
        }
        return assetMapper.deleteAsset(assetId);
    }

    /**
     * @param assetRTId 资产申购id
     * @return int
     * @author THS.
     * @date 2024/4/22 15:38
     * @description: 删除资产申购信息
     **/
    @Override
    public int deleteAssetRT(Integer assetRTId) {
        if (assetRTId == null){
            return 0;
        }
        return assetRequisitionMapper.deleteAssetRT(assetRTId);
    }

    /**
     * @param id 资产报废id
     * @return int
     * @author THS.
     * @date 2024/4/22 16:36
     * @description: 根据id删除资产报废信息
     **/
    @Override
    public int deleteAssetScrap(String id) {
        if (id == null){
            return 0;
        }
        return assetScrapMapper.deleteAssetScrap(id);
    }
}
