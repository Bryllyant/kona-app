package com.bryllyant.kona.app.service;

import java.io.Serializable;

/**
 * KSmsException.
 */
public class KSmsException extends KServiceException implements Serializable {

	private static final long serialVersionUID = 1L;

	public KSmsException(String message) {
        super(message);
    }


    public KSmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KSmsException(Throwable cause) {
        super(cause);
    }

}
