package com.zl.dao.mapper;

import com.zl.pojo.TUser;
import com.zl.pojo.TUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int countByExample(TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int deleteByExample(TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int insert(TUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int insertSelective(TUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    List<TUser> selectByExample(TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    TUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int updateByPrimaryKeySelective(TUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User
     *
     * @mbggenerated Sun Jun 14 16:11:25 CST 2015
     */
    int updateByPrimaryKey(TUser record);
}