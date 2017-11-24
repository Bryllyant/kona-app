package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.FriendshipExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    long countByExample(FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByExample(FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insert(Friendship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int insertSelective(Friendship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    List<Friendship> selectByExample(FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    Friendship selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExampleSelective(@Param("record") Friendship record, @Param("example") FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByExample(@Param("record") Friendship record, @Param("example") FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKeySelective(Friendship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    int updateByPrimaryKey(Friendship record);
}