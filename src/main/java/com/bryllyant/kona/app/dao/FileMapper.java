package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.FileExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<File> selectByExample(FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    File selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") File record, @Param("example") FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") File record, @Param("example") FileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(File record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__file
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(File record);
}