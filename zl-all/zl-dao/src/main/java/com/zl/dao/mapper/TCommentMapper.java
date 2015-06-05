package com.zl.dao.mapper;

import com.zl.pojo.TComment;
import com.zl.pojo.TCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int countByExample(TCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int deleteByExample(TCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int insert(TComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int insertSelective(TComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    List<TComment> selectByExample(TCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    TComment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByExampleSelective(@Param("record") TComment record, @Param("example") TCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByExample(@Param("record") TComment record, @Param("example") TCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByPrimaryKeySelective(TComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_Comment
     *
     * @mbggenerated Fri Jun 05 23:56:33 CST 2015
     */
    int updateByPrimaryKey(TComment record);
}