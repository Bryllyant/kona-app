package com.bryllyant.kona.app.service;

import java.io.Serializable;

/**
 * KSmsException.
 */
public class KPushException extends KServiceException implements Serializable {

	private static final long serialVersionUID = 1L;

	public KPushException(String message) {
        super(message);
    }

    public KPushException(String message, Throwable cause) {
        super(message, cause);
    }

    public KPushException(Throwable cause) {
        super(cause);
    }

}
