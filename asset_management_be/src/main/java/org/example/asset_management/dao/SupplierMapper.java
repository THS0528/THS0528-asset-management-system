package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/20 18:47:46
 * @description:
 */
@Mapper
public interface SupplierMapper {
    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author THS.
     * @date 2024/4/20 18:52
     * @description: 查询所有供应商
     **/
    @Select("SELECT * FROM supplier")
    List<Map<String, Object>> getSupplier();

    /**
     * @param supplierId 供应商id
     * @return int
     * @author THS.
     * @date 2024/4/20 18:54
     * @description: 根据供应商id删除供应商
     **/
    @Delete("DELETE FROM supplier WHERE SUPPLIER_ID = #{supplierId}")
    int deleteSupplier(Integer supplierId);

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
    @MapKey("id")
    List<Map<String, Object>> getSupplierByCondition(String supplierName, String supplierType, String linkman, String mp_number, String location, Integer status);

    /**
     * @param supplierId 供应商id
     * @param status     状态
     * @return int
     * @author THS.
     * @date 2024/4/20 20:34
     * @description: 根据供应商id修改供应商状态
     **/
    @Update("UPDATE supplier SET STATUS = #{status} WHERE SUPPLIER_ID = #{supplierId}")
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
    @Update("UPDATE supplier SET SUPPLIER_NAME = #{supplierName},SUPPLIER_TYPE = #{supplierType},LINKMAN = #{linkman},MP_NUMBER = #{mp_number},LOCATION = #{location} WHERE SUPPLIER_ID = #{supplierId}")
    int updateSupplier(Integer supplierId, String supplierName, String supplierType, String linkman, String mp_number, String location);
    /**
     * @author THS.
     * @param supplierName 供应商名称
     * @param supplierType 供应商类型
     * @param linkman 联系人
     * @param mp_number 联系人电话
     * @param location 地址
     * @param status 状态
     * @return int
     * @date 2024/4/20 20:40
     * @description: 添加供应商
     **/
    @Insert("INSERT INTO supplier(SUPPLIER_NAME,SUPPLIER_TYPE,LINKMAN,MP_NUMBER,LOCATION,STATUS) VALUES(#{supplierName},#{supplierType},#{linkman},#{mp_number},#{location},#{status})")
    int insertSupplier(String supplierName, String supplierType, String linkman, String mp_number, String location, Integer status);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 23:02
     * @description: 查询未停用的供应商
     **/
    @Select("SELECT SUPPLIER_ID,SUPPLIER_NAME FROM supplier WHERE STATUS = '1'")
    List<Map<String, Object>> getSupplierOptions();
}
