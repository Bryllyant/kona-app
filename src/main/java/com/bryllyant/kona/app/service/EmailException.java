package com.bryllyant.kona.app.service;

import com.bryllyant.kona.data.service.KServiceException;

import java.io.Serializable;

public class EmailException extends KServiceException implements Serializable {
	private static final long serialVersionUID = 1L;

	public EmailException(String message) {
		super(message);
	}

	public EmailException(String message, Throwable t) {
		super(message, t);
	}

	public EmailException(Throwable t) {
		super(t);
	}
}
