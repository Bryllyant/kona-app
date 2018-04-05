package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface InvitationService extends KService, KEntityService<Invitation> {
	String SERVICE_PATH = "rpc/InvitationService";

    Invitation invite(Long contactId, Invitation.Type type, Invitation.Channel channel, boolean resend);

    Invitation inviteByMobileNumber(Long ownerId, Invitation.Type type, String mobileNumber, String firstName, boolean resend);

    Invitation inviteByEmail(Long ownerId, Invitation.Type type, String email, String firstName, boolean resend);

    Invitation accept(Invitation invitation);

    Invitation fetchByInvitationCode(String invitationCode);

    Invitation accessInvitationCode(String invitationCode);

    // process a user's invitations after he/she has registered
    // this method is called by UserService()
    void processNewUserInvitations(Long ownerId);


    List<Invitation> fetchByOwnerId(Long ownerId, Invitation.Status status,
                                           Invitation.Type type, Invitation.Channel channel);

    List<Invitation> fetchByContactId(Long contactId, Invitation.Status status,
                                             Invitation.Type type, Invitation.Channel channel);

    List<Invitation> fetchByEmail(String email, Invitation.Status status,
                                         Invitation.Type type, Invitation.Channel channel);

    List<Invitation> fetchByMobileNumber(String mobileNumber, Invitation.Status status,
                                                Invitation.Type type, Invitation.Channel channel);
}
