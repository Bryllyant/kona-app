package com.bryllyant.kona.app.service;

import java.io.Serializable;

/**
 * QueueException.
 */
public class QueueException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

    public QueueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public QueueException(final String message) {
        super(message);
    }

    public QueueException(final Throwable cause) {
        super(cause);
    }
}
