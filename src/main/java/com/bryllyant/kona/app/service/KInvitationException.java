/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.io.Serializable;

/**
 * KInvitationException.
 */
public class KInvitationException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public KInvitationException(String message) {
        super(message);
    }


    public KInvitationException(String message, Throwable cause) {
        super(message, cause);
    }

    public KInvitationException(Throwable cause) {
        super(cause);
    }

}
