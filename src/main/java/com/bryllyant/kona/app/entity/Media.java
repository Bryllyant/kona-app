package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class Media extends KBaseMedia implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.duration
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    private Long duration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__media
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.duration
     *
     * @return the value of kona__media.duration
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.duration
     *
     * @param duration the value for kona__media.duration
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }
}