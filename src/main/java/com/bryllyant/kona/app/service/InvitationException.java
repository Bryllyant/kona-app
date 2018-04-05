/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.io.Serializable;

/**
 * InvitationException.
 */
public class InvitationException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public InvitationException(String message) {
        super(message);
    }


    public InvitationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvitationException(Throwable cause) {
        super(cause);
    }

}
