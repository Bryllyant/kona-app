package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.app.entity.FriendshipEventExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipEventMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    long countByExample(FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByExample(FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insert(FriendshipEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insertSelective(FriendshipEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    List<FriendshipEvent> selectByExample(FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    FriendshipEvent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExampleSelective(@Param("record") FriendshipEvent record, @Param("example") FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExample(@Param("record") FriendshipEvent record, @Param("example") FriendshipEventExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKeySelective(FriendshipEvent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKey(FriendshipEvent record);
}