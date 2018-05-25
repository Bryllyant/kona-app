package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.FriendshipExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Friendship, FriendshipExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    long countByExample(FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int deleteByExample(FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int insert(Friendship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int insertSelective(Friendship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    List<Friendship> selectByExample(FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    Friendship selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Friendship record, @Param("example") FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByExample(@Param("record") Friendship record, @Param("example") FriendshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByPrimaryKeySelective(Friendship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    int updateByPrimaryKey(Friendship record);
}