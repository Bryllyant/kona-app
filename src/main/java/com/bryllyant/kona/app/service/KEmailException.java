package com.bryllyant.kona.app.service;

import java.io.Serializable;

public class KEmailException extends KServiceException implements Serializable {
	private static final long serialVersionUID = 1L;

	public KEmailException(String message) {
		super(message);
	}

	public KEmailException(String message, Throwable t) {
		super(message, t);
	}

	public KEmailException(Throwable t) {
		super(t);
	}
}
