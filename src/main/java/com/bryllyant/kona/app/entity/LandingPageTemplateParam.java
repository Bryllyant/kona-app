package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KLandingPageTemplateParam.Type;
import java.io.Serializable;

public class LandingPageTemplateParam extends KBaseLandingPageTemplateParam implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private Type type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.type
     *
     * @return the value of kona__landing_page_template_param.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.type
     *
     * @param type the value for kona__landing_page_template_param.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }
}