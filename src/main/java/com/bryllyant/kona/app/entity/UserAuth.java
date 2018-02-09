package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class UserAuth extends KBaseUserAuth implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_auth.voice_sample_id
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    private Long voiceSampleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_auth.fingerprint_id
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    private Long fingerprintId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_auth.voice_sample_id
     *
     * @return the value of kona__user_auth.voice_sample_id
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public Long getVoiceSampleId() {
        return voiceSampleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_auth.voice_sample_id
     *
     * @param voiceSampleId the value for kona__user_auth.voice_sample_id
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public void setVoiceSampleId(Long voiceSampleId) {
        this.voiceSampleId = voiceSampleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_auth.fingerprint_id
     *
     * @return the value of kona__user_auth.fingerprint_id
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public Long getFingerprintId() {
        return fingerprintId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_auth.fingerprint_id
     *
     * @param fingerprintId the value for kona__user_auth.fingerprint_id
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public void setFingerprintId(Long fingerprintId) {
        this.fingerprintId = fingerprintId;
    }
}