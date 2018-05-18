package com.bryllyant.kona.app.dao;
import java.util.Date;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.TokenExample;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TokenMapper extends com.bryllyant.kona.data.dao.KMyBatisMapper<Token, TokenExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    long countByExample(TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int deleteByExample(TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int insert(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int insertSelective(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    List<Token> selectByExampleWithBLOBs(TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    List<Token> selectByExample(TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    Token selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByExampleSelective(@Param("record") Token record, @Param("example") TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Token record, @Param("example") TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByExample(@Param("record") Token record, @Param("example") TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByPrimaryKeySelective(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByPrimaryKeyWithBLOBs(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__token
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    int updateByPrimaryKey(Token record);

    int updateCoords(Long objectId);

    List<Token> selectProximate(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude, 
        @Param("radius") Double radius,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("objectIds") List<Long> objectIdList
    );
}           
