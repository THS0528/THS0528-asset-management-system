package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/18 18:05:10
 * @description: 品牌mapper
 */
@Mapper
public interface BrandMapper {
    /**
     * @author THS.
     * @param userId 用户id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/18 18:33
     * @description: 根据用户id查询品牌
     **/
    @Select("SELECT BRAND_ID,BRAND_NAME,BRAND_CODE FROM brand WHERE USER_ID = #{classId}")
    List<Map<String, Object>> getBrandByUserId(Integer userId);
    /**
     * @author THS.
     * @param brandId 品牌id
     * @param status 状态
     * @return int
     * @date 2024/4/18 18:33
     * @description: 根据品牌id修改状态
     **/
    @Update("UPDATE brand SET STATUS = #{status} WHERE BRAND_ID = #{brandId}")
    int updateBrandCode(Integer brandId, Integer status);
    /**
     * @author THS.
     * @param brandCode 品牌编码
     * @param brandName 品牌名称
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/18 21:22
     * @description: 根据品牌编码和品牌名称查询品牌
     **/
    @MapKey("id")
    List<Map<String,Object>> getBrandByCondition(String brandCode, String brandName, Integer status);
    /**
     * @author THS.
     * @param brandId 品牌id
     * @param brandCode 品牌编码
     * @param brandName 品牌名称
     * @return int
     * @date 2024/4/18 21:23
     * @description: 根据品牌id修改品牌
     **/
    @Update("UPDATE brand SET BRAND_CODE = #{brandCode},BRAND_NAME = #{brandName} WHERE BRAND_ID = #{brandId}")
    int updateBrandByBrandId(Integer brandId,String brandCode, String brandName);
    /**
     * @author THS.
     * @param brandCode 品牌编码
     * @param brandName 品牌名
     * @param userId 用户id
     * @param status 状态
     * @return int
     * @date 2024/4/18 21:50
     * @description:
     **/
    @Insert("INSERT INTO brand(BRAND_CODE,BRAND_NAME, USER_ID, STATUS) VALUES(#{brandCode},#{brandName}, #{userId}, #{status})")
    int insertBrand(String brandCode, String brandName, int userId, int status);
    /**
     * @author THS.
     * @param brandCode 品牌编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/18 21:50
     * @description: 根据品牌编码查询品牌
     **/
    @Select("SELECT BRAND_ID FROM brand WHERE BRAND_CODE = #{brandCode}")
    List<Map<String, Object>> getBrandByBrandCode(String brandCode);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:56
     * @description: 查询所有未停用的品牌
     **/
    @Select("SELECT BRAND_ID, BRAND_NAME FROM brand WHERE STATUS = 1")
    List<Map<String, Object>> getBrandOptions();
}
