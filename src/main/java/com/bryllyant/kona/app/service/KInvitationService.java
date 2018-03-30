/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KInvitation;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface KInvitationService<INVITATION extends KInvitation> extends KService, KEntityService<INVITATION> {
    public static final String SERVICE_PATH = "rpc/kona/InvitationService";

    public INVITATION invite(Long addressBookId, INVITATION.Type type, INVITATION.Channel channel, boolean resend);
    
    public INVITATION inviteByMobileNumber(Long ownerId, INVITATION.Type type, String mobileNumber, String firstName, boolean resend);
    
    public INVITATION inviteByEmail(Long ownerId, INVITATION.Type type, String email, String firstName, boolean resend);
    
    public INVITATION accept(INVITATION invitation);
    
    public INVITATION fetchByInvitationCode(String invitationCode);
    
    public INVITATION accessInvitationCode(String invitationCode);
    
    // process a user's invitations after he/she has registered
    // this method is called by UserService()
	public void processNewUserInvitations(Long ownerId);

    
    public List<INVITATION> fetchByOwnerId(Long ownerId, INVITATION.Status status,
    		INVITATION.Type type, INVITATION.Channel channel);
    
    public List<INVITATION> fetchByContactId(Long contactId, INVITATION.Status status,
    		INVITATION.Type type, INVITATION.Channel channel);
    
    public List<INVITATION> fetchByEmail(String email, INVITATION.Status status,
    		INVITATION.Type type, INVITATION.Channel channel);
    
    public List<INVITATION> fetchByMobileNumber(String mobileNumber, INVITATION.Status status,
    		INVITATION.Type type, INVITATION.Channel channel);
}
