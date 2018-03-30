package com.bryllyant.kona.config;

import java.io.Serializable;

public class KConfigException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public KConfigException(String message) {
		super(message);
	}

	public KConfigException(String message, Throwable t) {
		super(message, t);
	}

	public KConfigException(Throwable t) {
		super(t);
	}
}
