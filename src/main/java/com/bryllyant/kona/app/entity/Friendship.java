package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class Friendship extends KBaseFriendship implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship.status
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private Status status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__friendship
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship.status
     *
     * @return the value of kona__friendship.status
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship.status
     *
     * @param status the value for kona__friendship.status
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}