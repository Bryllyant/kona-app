package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    User selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    int updateByPrimaryKey(User record);

    int updatePresence(@Param("inactiveSeconds") Integer inactiveSeconds);
}           
