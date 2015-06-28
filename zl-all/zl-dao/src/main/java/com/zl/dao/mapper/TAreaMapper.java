package com.zl.dao.mapper;

import com.zl.pojo.TArea;
import com.zl.pojo.TAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAreaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int countByExample(TAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int deleteByExample(TAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int insert(TArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int insertSelective(TArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    List<TArea> selectByExample(TAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    TArea selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int updateByExampleSelective(@Param("record") TArea record, @Param("example") TAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int updateByExample(@Param("record") TArea record, @Param("example") TAreaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int updateByPrimaryKeySelective(TArea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Area
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    int updateByPrimaryKey(TArea record);
}