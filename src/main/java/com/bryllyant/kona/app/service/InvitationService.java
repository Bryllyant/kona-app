package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.remote.service.KService;

public interface InvitationService extends KService, KInvitationService<Invitation> {
	public static final String SERVICE_PATH = "rpc/InvitationService";
	
}
