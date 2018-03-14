package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.entity.ShortUrlExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShortUrlMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<ShortUrl, ShortUrlExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    long countByExample(ShortUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int deleteByExample(ShortUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int insert(ShortUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int insertSelective(ShortUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    List<ShortUrl> selectByExample(ShortUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    ShortUrl selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") ShortUrl record, @Param("example") ShortUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByExample(@Param("record") ShortUrl record, @Param("example") ShortUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByPrimaryKeySelective(ShortUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__short_url
     *
     * @mbg.generated Mon Mar 12 17:32:51 EDT 2018
     */
    int updateByPrimaryKey(ShortUrl record);
}