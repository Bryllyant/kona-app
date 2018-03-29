package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KInvitation.Channel;
import com.bryllyant.kona.app.entity.KInvitation.Status;
import com.bryllyant.kona.app.entity.KInvitation.Type;
import java.io.Serializable;

public class Invitation extends KBaseInvitation implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.channel
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private Channel channel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.status
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private Status status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__invitation
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.type
     *
     * @return the value of kona__invitation.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.type
     *
     * @param type the value for kona__invitation.type
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.channel
     *
     * @return the value of kona__invitation.channel
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.channel
     *
     * @param channel the value for kona__invitation.channel
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.status
     *
     * @return the value of kona__invitation.status
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.status
     *
     * @param status the value for kona__invitation.status
     *
     * @mbg.generated Thu Mar 29 15:50:51 EDT 2018
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}