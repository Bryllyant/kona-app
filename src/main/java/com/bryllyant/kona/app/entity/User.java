package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KUser.Gender;
import com.bryllyant.kona.app.entity.KUser.Presence;
import com.bryllyant.kona.app.entity.KUser.Type;
import java.io.Serializable;

public class User extends KBaseUser implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.type
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.gender
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    private Gender gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.presence
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    private Presence presence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__user
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.type
     *
     * @return the value of kona__user.type
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.type
     *
     * @param type the value for kona__user.type
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.gender
     *
     * @return the value of kona__user.gender
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.gender
     *
     * @param gender the value for kona__user.gender
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.presence
     *
     * @return the value of kona__user.presence
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public Presence getPresence() {
        return presence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.presence
     *
     * @param presence the value for kona__user.presence
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public void setPresence(Presence presence) {
        this.presence = presence;
    }
}