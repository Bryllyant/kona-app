package com.bryllyant.kona.app.service;

import com.bryllyant.kona.data.service.KServiceException;

import java.io.Serializable;

/**
 * SmsException.
 */
public class PushException extends KServiceException implements Serializable {

	private static final long serialVersionUID = 1L;

	public PushException(String message) {
        super(message);
    }

    public PushException(String message, Throwable cause) {
        super(message, cause);
    }

    public PushException(Throwable cause) {
        super(cause);
    }

}
