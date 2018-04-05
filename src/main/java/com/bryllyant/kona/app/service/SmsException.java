package com.bryllyant.kona.app.service;

import com.bryllyant.kona.data.service.KServiceException;

import java.io.Serializable;

/**
 * SmsException.
 */
public class SmsException extends KServiceException implements Serializable {

	private static final long serialVersionUID = 1L;

	public SmsException(String message) {
        super(message);
    }


    public SmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsException(Throwable cause) {
        super(cause);
    }

}
