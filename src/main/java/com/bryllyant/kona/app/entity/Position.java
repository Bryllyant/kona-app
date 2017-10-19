package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class Position extends KBasePosition implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.dwell_time
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private Long dwellTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.background
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private boolean background;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.source
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__position
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.dwell_time
     *
     * @return the value of kona__position.dwell_time
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public Long getDwellTime() {
        return dwellTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.dwell_time
     *
     * @param dwellTime the value for kona__position.dwell_time
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public void setDwellTime(Long dwellTime) {
        this.dwellTime = dwellTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.background
     *
     * @return the value of kona__position.background
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public boolean isBackground() {
        return background;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.background
     *
     * @param background the value for kona__position.background
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public void setBackground(boolean background) {
        this.background = background;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.source
     *
     * @return the value of kona__position.source
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.source
     *
     * @param source the value for kona__position.source
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}