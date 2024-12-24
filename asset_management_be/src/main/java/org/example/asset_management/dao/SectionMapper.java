package org.example.asset_management.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/19 10:04:25
 * @description: 部门mapper
 */
@Mapper
public interface SectionMapper {
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:12
     * @description: 获取所有部门
     **/
    @Select("SELECT SECTION_ID, SECTION_CODE ,SECTION_NAME, STATUS, STATUS FROM section")
    List<Map<String, Object>> getSection();
    /**
     * @author THS.
     * @param sectionCode 部门编号
     * @param sectionName 部门名称
     * @param status 状态
     * @return int
     * @date 2024/4/19 15:14
     * @description: 新增部门
     **/
    @Insert("INSERT INTO section(SECTION_CODE,SECTION_NAME, STATUS) VALUES(#{sectionCode},#{sectionName}, #{status})")
    int insertSection(String sectionCode, String sectionName, int status);
    /**
     * @author THS.
     * @param sectionCode 部门编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:16
     * @description: 根据部门编码获取部门信息
     **/
    @Select("SELECT SECTION_NAME FROM section WHERE SECTION_NAME = #{sectionCode}")
    List<Map<String, Object>> getSectionBySectionCode(String sectionCode);
    /**
     * @author THS.
     * @param sectionId 部门id
     * @param status 状态
     * @return int
     * @date 2024/4/19 15:19
     * @description: 根据部门id修改部门状态
     **/
    @Update("UPDATE section SET STATUS = #{status} WHERE SECTION_ID = #{sectionId}")
    int updateSectionStatus(Integer sectionId, Integer status);
    /**
     * @author THS.
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @param status 状态
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/19 15:19
     * @description: 根据条件获取部门信息
     **/
    @MapKey("id")
    List<Map<String, Object>> getSectionByCondition(String sectionCode, String sectionName, Integer status);
    /**
     * @author THS.
     * @param sectionId 部门id
     * @param sectionCode 部门编码
     * @param sectionName 部门名称
     * @return int
     * @date 2024/4/19 15:20
     * @description: 根据部门id修改部门信息
     **/
    @Update("UPDATE section SET SECTION_CODE = #{sectionCode},SECTION_NAME = #{sectionName} WHERE SECTION_ID = #{sectionId}")
    int updateSectionBySectionId(Integer sectionId, String sectionCode, String sectionName);
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/21 22:59
     * @description: 查询未停用的部门
     **/
    @Select("SELECT SECTION_ID, SECTION_NAME FROM section WHERE STATUS = 1")
    List<Map<String, Object>> getSectionOptions();
}
