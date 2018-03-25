package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.app.entity.FriendshipEventExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipEventMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<FriendshipEvent, FriendshipEventExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    long countByExample(FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByExample(FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insert(FriendshipEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int insertSelective(FriendshipEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    List<FriendshipEvent> selectByExample(FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    FriendshipEvent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExampleSelective(@Param("record") FriendshipEvent record, @Param("example") FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByExample(@Param("record") FriendshipEvent record, @Param("example") FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKeySelective(FriendshipEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    int updateByPrimaryKey(FriendshipEvent record);
}