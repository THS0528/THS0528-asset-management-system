package org.example.asset_management.dao;

import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/22 10:32:59
 * @description:
 */
@Mapper
public interface AssetRepaymentMapper {
    /**
     * @author THS.
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 10:46
     * @description: 查询所有借用记录
     **/
    @Select("SELECT ar.BORROWED_CODE,ar.ASSET_ID,asset.ASSET_CODE,asset.ASSET_NAME ,ac.CLASS_NAME,ar.LOAN_DATE,se.SECTION_NAME,ar.CAUSE,ar.BACK_DATE,ar.BACK_REMARK,ar.STATUS FROM asset_repayment ar " +
            "JOIN asset ON ar.ASSET_ID = asset.ASSET_ID " +
            "JOIN asset_class ac ON asset.CLASS_ID = ac.CLASS_ID " +
            "JOIN section se ON ar.SECTION_ID = se.SECTION_ID")
    List<Map<String, Object>> getAssetRM();
    /**
     * @author THS.
     * @param assetCode 资产编码
     * @param assetId 资产id
     * @param sectionId 部门id
     * @param classId 资产类别id
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @date 2024/4/22 11:33
     * @description: 根据条件查询资产借用记录
     **/
    @MapKey("id")
    List<Map<String,Object>> getAssetRMByCondition(String assetCode, Integer assetId, Integer sectionId, Integer classId);
    /**
     * @author THS.
     * @param borrowedCode 借用编号
     * @param assetId 资产id
     * @param loanDate 借用日期
     * @param sectionId 部门id
     * @param cause 借用原因
     * @return int
     * @date 2024/4/22 14:54
     * @description: 添加资产借用记录
     **/
    @Insert("INSERT INTO asset_repayment(BORROWED_CODE,ASSET_ID,LOAN_DATE,SECTION_ID,CAUSE,STATUS) VALUES(#{borrowedCode},#{assetId},#{loanDate},#{sectionId},#{cause},0)")
    int addAssetRM(String borrowedCode, Integer assetId, Date loanDate, Integer sectionId, String cause);
    /**
     * @author THS.
     * @param borrowedCode 借用编号
     * @param backDate 归还日期
     * @return int
     * @date 2024/4/22 14:55
     * @description: 资产归还
     **/
    @Update("UPDATE asset_repayment SET BACK_DATE = #{backDate},BACK_REMARK = #{backRemark},STATUS = 1 WHERE BORROWED_CODE = #{borrowedCode}")
    int updateAssetRM(String borrowedCode, Date backDate , String backRemark);

}
