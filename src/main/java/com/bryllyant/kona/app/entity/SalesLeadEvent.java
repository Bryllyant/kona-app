package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KSalesLeadEvent.Type;
import java.io.Serializable;

public class SalesLeadEvent extends KBaseSalesLeadEvent implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead_event.type
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    private Type type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__sales_lead_event
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead_event.type
     *
     * @return the value of kona__sales_lead_event.type
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead_event.type
     *
     * @param type the value for kona__sales_lead_event.type
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }
}