package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.AddressBook;
import com.bryllyant.kona.app.entity.AddressBookExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressBookMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(AddressBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(AddressBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(AddressBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(AddressBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<AddressBook> selectByExample(AddressBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    AddressBook selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") AddressBook record, @Param("example") AddressBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") AddressBook record, @Param("example") AddressBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(AddressBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__address_book
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(AddressBook record);

    int updateCoords(Long objectId);

    List<AddressBook> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );
}           
