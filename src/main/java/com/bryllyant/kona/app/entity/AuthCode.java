package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class AuthCode extends KBaseAuthCode implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.type
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    private Type type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__auth_code
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.type
     *
     * @return the value of kona__auth_code.type
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.type
     *
     * @param type the value for kona__auth_code.type
     *
     * @mbg.generated Thu Mar 29 15:50:50 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }
}