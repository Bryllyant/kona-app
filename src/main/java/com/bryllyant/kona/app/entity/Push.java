package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KPush.Platform;
import java.io.Serializable;

public class Push extends KBasePush implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.platform
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    private Platform platform;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.platform
     *
     * @return the value of kona__push.platform
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.platform
     *
     * @param platform the value for kona__push.platform
     *
     * @mbg.generated Sat Mar 24 14:14:45 EDT 2018
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}