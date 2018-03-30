package com.bryllyant.kona.app.service;

import java.io.Serializable;

/**
 * KServiceException.
 */
public class KServiceException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public KServiceException() {

    }
    
	public KServiceException(String message) {
        super(message);
    }


    public KServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public KServiceException(Throwable cause) {
        super(cause);
    }

}
