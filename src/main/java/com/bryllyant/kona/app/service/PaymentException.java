package com.bryllyant.kona.app.service;

import java.io.Serializable;

/**
 * PaymentException.
 */
public class PaymentException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public PaymentException(String message) {
        super(message);
    }


    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentException(Throwable cause) {
        super(cause);
    }

}
