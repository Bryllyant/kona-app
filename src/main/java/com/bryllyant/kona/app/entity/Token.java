package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KToken.Type;
import java.io.Serializable;

public class Token extends KBaseToken implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.type
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.authentication
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    private byte[] authentication;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__token
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.type
     *
     * @return the value of kona__token.type
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.type
     *
     * @param type the value for kona__token.type
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.authentication
     *
     * @return the value of kona__token.authentication
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    public byte[] getAuthentication() {
        return authentication;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.authentication
     *
     * @param authentication the value for kona__token.authentication
     *
     * @mbg.generated Mon Mar 12 10:20:15 EDT 2018
     */
    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}