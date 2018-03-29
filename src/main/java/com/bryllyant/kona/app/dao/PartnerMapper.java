package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.entity.PartnerExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartnerMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Partner, PartnerExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    long countByExample(PartnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int deleteByExample(PartnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int insert(Partner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int insertSelective(Partner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    List<Partner> selectByExample(PartnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    Partner selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Partner record, @Param("example") PartnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByExample(@Param("record") Partner record, @Param("example") PartnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByPrimaryKeySelective(Partner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__partner
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    int updateByPrimaryKey(Partner record);
}