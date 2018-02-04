package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.app.entity.FriendshipCircleExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipCircleMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    long countByExample(FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByExample(FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insert(FriendshipCircle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int insertSelective(FriendshipCircle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    List<FriendshipCircle> selectByExample(FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    FriendshipCircle selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") FriendshipCircle record, @Param("example") FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByExample(@Param("record") FriendshipCircle record, @Param("example") FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKeySelective(FriendshipCircle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    int updateByPrimaryKey(FriendshipCircle record);
}