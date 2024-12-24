package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;
import org.example.asset_management.entity.AssetClass;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/17 10:15:35
 * @description: 资产类别接口
 */
@Mapper
public interface AssetClassMapper {
    /**
     * @author THS.
     * @param userId  用户id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/17 10:22
     * @description: 根据用户id获取资产类别
     **/
    @Select("SELECT CLASS_ID,CLASS_CODE, CLASS_NAME, STATUS FROM asset_class WHERE USER_ID = #{userId}")
    List<Map<String, Object>> getAssetClassByUserId(int userId);
    /**
     * @author THS.
     * @param classCode 资产类别编码
     * @param className 资产类别名
     * @param userId 用户id
     * @param status 状态
     * @return int
     * @date 2024/4/17 20:26
     * @description: 插入资产类别
     **/
    @Insert("INSERT INTO asset_class(CLASS_CODE,CLASS_NAME, USER_ID, STATUS) VALUES(#{classCode},#{className}, #{userId}, #{status})")
    int insertAssetClass(String classCode, String className, int userId, int status);
    /**
     * @author THS.
     * @param classCode 资产类别id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/17 21:18
     * @description: 根据资产类别id查询资产类别
     **/
    @Select("SELECT CLASS_NAME FROM asset_class WHERE CLASS_CODE = #{classCode}")
    List<Map<String, Object>> getAssetClassByClassCode(String classCode);
    /**
     * @author THS.
     * @param classId 资产类别id
     * @param status 资产类别状态
     * @return int
     * @date 2024/4/17 22:47
     * @description: 根据资产类别id更新资产类别状态
     **/
    @Update("UPDATE asset_class SET STATUS = #{status} WHERE CLASS_ID = #{classId}")
    int updateAssetClassStatus(Integer classId, Integer status);
    /**
     * @author THS.
     * @param classCode 资产类别编码
     * @param className 资产类别名称
     * @param status 资产类别状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/17 22:49
     * @description:
     **/
    @MapKey("id")
    List<Map<String, Object>> getAssetClassByCondition(String classCode, String className, Integer status);
    /**
     * @author THS.
     * @param classCode 资产类别编码
     * @param className 资产类别名
     * @return int
     * @date 2024/4/18 11:01
     * @description: 根据资产类别id更新资产类别名称或资产类别id
     **/
    @Update("UPDATE asset_class SET CLASS_CODE = #{classCode},CLASS_NAME = #{className} WHERE CLASS_ID = #{classId}")
    int updateAssetClassByClassId(Integer classId,String classCode, String className);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:55
     * @description: 查询所有未停用资产类别
     **/
    @Select("SELECT CLASS_ID, CLASS_NAME FROM asset_class WHERE STATUS = 1")
    List<Map<String, Object>> getAssetClassOptions();

}
