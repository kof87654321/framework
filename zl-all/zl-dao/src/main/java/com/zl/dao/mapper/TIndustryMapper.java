package com.zl.dao.mapper;

import com.zl.pojo.TIndustry;
import com.zl.pojo.TIndustryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TIndustryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int countByExample(TIndustryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int deleteByExample(TIndustryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int insert(TIndustry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int insertSelective(TIndustry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    List<TIndustry> selectByExample(TIndustryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    TIndustry selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByExampleSelective(@Param("record") TIndustry record, @Param("example") TIndustryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByExample(@Param("record") TIndustry record, @Param("example") TIndustryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByPrimaryKeySelective(TIndustry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Industry
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByPrimaryKey(TIndustry record);
}