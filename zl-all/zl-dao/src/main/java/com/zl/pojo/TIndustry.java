package com.zl.pojo;

import java.io.Serializable;

/**
 * tableName{T_Industry}
 * by is_zhoufeng@163.com 2015-06-28 20:50:31
 */
public class TIndustry implements Serializable {
    /**
     *   
     * column{Id},jdbcType{INTEGER}
     */
    private Integer id;

    /**
     * 编码  
     * column{Code},jdbcType{INTEGER}
     */
    private Integer code;

    /**
     * 名称  
     * column{Name},jdbcType{VARCHAR}
     */
    private String name;

    /**
     * 英文名称  
     * column{NameEn},jdbcType{VARCHAR}
     */
    private String nameEn;

    /**
     *   
     * column{CateCode},jdbcType{INTEGER}
     */
    private Integer cateCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_Industry
     *
     * @mbggenerated Sun Jun 28 20:50:31 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     *   
     * column{Id},jdbcType{INTEGER}
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 编码  
     * column{Code},jdbcType{INTEGER}
     */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 名称  
     * column{Name},jdbcType{VARCHAR}
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 英文名称  
     * column{NameEn},jdbcType{VARCHAR}
     */
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     *   
     * column{CateCode},jdbcType{INTEGER}
     */
    public Integer getCateCode() {
        return cateCode;
    }

    public void setCateCode(Integer cateCode) {
        this.cateCode = cateCode;
    }
}