package com.zl.dao.mapper;

import com.zl.pojo.Lvuser;
import com.zl.pojo.LvuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LvuserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int countByExample(LvuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int deleteByExample(LvuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int insert(Lvuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int insertSelective(Lvuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    List<Lvuser> selectByExample(LvuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    Lvuser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int updateByExampleSelective(@Param("record") Lvuser record, @Param("example") LvuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int updateByExample(@Param("record") Lvuser record, @Param("example") LvuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int updateByPrimaryKeySelective(Lvuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lvuser
     *
     * @mbggenerated Mon Jun 01 22:28:29 CST 2015
     */
    int updateByPrimaryKey(Lvuser record);
}