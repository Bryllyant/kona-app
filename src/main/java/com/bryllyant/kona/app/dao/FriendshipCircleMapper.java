package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.app.entity.FriendshipCircleExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendshipCircleMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    long countByExample(FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByExample(FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insert(FriendshipCircle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int insertSelective(FriendshipCircle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    List<FriendshipCircle> selectByExample(FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    FriendshipCircle selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExampleSelective(@Param("record") FriendshipCircle record, @Param("example") FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByExample(@Param("record") FriendshipCircle record, @Param("example") FriendshipCircleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKeySelective(FriendshipCircle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    int updateByPrimaryKey(FriendshipCircle record);
}