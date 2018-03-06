package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.FileExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<File, FileExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    long countByExample(FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByExample(FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insert(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int insertSelective(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    List<File> selectByExample(FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    File selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExampleSelective(@Param("record") File record, @Param("example") FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByExample(@Param("record") File record, @Param("example") FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKeySelective(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Mar 06 18:09:25 EST 2018
     */
    int updateByPrimaryKey(File record);
}