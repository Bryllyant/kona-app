package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.InvitationExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvitationMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Invitation, InvitationExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    long countByExample(InvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByExample(InvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insert(Invitation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int insertSelective(Invitation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    List<Invitation> selectByExample(InvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    Invitation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Invitation record, @Param("example") InvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByExample(@Param("record") Invitation record, @Param("example") InvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKeySelective(Invitation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__invitation
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    int updateByPrimaryKey(Invitation record);
}