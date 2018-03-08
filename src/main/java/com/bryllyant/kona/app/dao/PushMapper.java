package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Push, PushExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    long countByExample(PushExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByExample(PushExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insert(Push record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int insertSelective(Push record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    List<Push> selectByExample(PushExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    Push selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExampleSelective(@Param("record") Push record, @Param("example") PushExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByExample(@Param("record") Push record, @Param("example") PushExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKeySelective(Push record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push
     *
     * @mbg.generated Thu Mar 08 16:28:05 EST 2018
     */
    int updateByPrimaryKey(Push record);
}