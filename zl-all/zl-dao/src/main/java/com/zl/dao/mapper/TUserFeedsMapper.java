package com.zl.dao.mapper;

import com.zl.pojo.TUserFeeds;
import com.zl.pojo.TUserFeedsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserFeedsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int countByExample(TUserFeedsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int deleteByExample(TUserFeedsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int insert(TUserFeeds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int insertSelective(TUserFeeds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    List<TUserFeeds> selectByExample(TUserFeedsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    TUserFeeds selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int updateByExampleSelective(@Param("record") TUserFeeds record, @Param("example") TUserFeedsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int updateByExample(@Param("record") TUserFeeds record, @Param("example") TUserFeedsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int updateByPrimaryKeySelective(TUserFeeds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_User_Feeds
     *
     * @mbggenerated Tue Jul 07 12:49:50 CST 2015
     */
    int updateByPrimaryKey(TUserFeeds record);
}