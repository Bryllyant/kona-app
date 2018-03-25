package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KRemoteServiceUserCreds.AuthType;
import java.io.Serializable;

public class RemoteServiceUserCreds extends KBaseRemoteServiceUserCreds implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.auth_type
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    private AuthType authType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.auth_type
     *
     * @return the value of kona__remote_service_user_creds.auth_type
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    public AuthType getAuthType() {
        return authType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.auth_type
     *
     * @param authType the value for kona__remote_service_user_creds.auth_type
     *
     * @mbg.generated Sun Mar 25 13:33:31 EDT 2018
     */
    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }
}