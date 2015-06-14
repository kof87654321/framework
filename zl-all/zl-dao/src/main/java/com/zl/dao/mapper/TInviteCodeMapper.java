package com.zl.dao.mapper;

import com.zl.pojo.TInviteCode;
import com.zl.pojo.TInviteCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TInviteCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int countByExample(TInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int deleteByExample(TInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int insert(TInviteCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int insertSelective(TInviteCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    List<TInviteCode> selectByExample(TInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    TInviteCode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int updateByExampleSelective(@Param("record") TInviteCode record, @Param("example") TInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int updateByExample(@Param("record") TInviteCode record, @Param("example") TInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int updateByPrimaryKeySelective(TInviteCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Invite_Code
     *
     * @mbggenerated Sun Jun 14 16:08:48 CST 2015
     */
    int updateByPrimaryKey(TInviteCode record);
}