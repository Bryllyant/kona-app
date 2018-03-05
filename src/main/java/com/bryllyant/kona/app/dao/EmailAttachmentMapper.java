package com.bryllyant.kona.app.dao;

import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailAttachmentExample;
import com.bryllyant.kona.data.dao.KMyBatisDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailAttachmentMapper extends KMyBatisDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    long countByExample(EmailAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int deleteByExample(EmailAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int insert(EmailAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int insertSelective(EmailAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    List<EmailAttachment> selectByExample(EmailAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    EmailAttachment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByExampleSelective(@Param("record") EmailAttachment record, @Param("example") EmailAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByExample(@Param("record") EmailAttachment record, @Param("example") EmailAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByPrimaryKeySelective(EmailAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 14:06:43 EST 2018
     */
    int updateByPrimaryKey(EmailAttachment record);
}