/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KContact;
import com.bryllyant.kona.app.entity.KFriendship;
import com.bryllyant.kona.app.entity.KInvitation;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract class KAbstractInvitationService<
        INVITATION extends KInvitation,
        INVITATION_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<INVITATION, INVITATION_EXAMPLE>,
        CONTACT extends KContact,
        FRIENDSHIP extends KFriendship,
        USER extends KUser>
        extends KAbstractService<INVITATION,INVITATION_EXAMPLE,MAPPER>
        implements KInvitationService<INVITATION> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractInvitationService.class);




    protected abstract INVITATION getNewObject();

    protected abstract String getInvitationUrl(Long ownerId, String code);

    protected abstract INVITATION sendInvitation(INVITATION invitation, CONTACT contact, String invitationUrl);

    protected abstract <S extends KUserService<USER>> S getUserService();

    protected abstract <S extends KContactService<CONTACT>> S getContactService();

    protected abstract <S extends KFriendshipService<FRIENDSHIP>> S getFriendshipService();



    protected String generateAccessCode() {
        return uuid();
    }



    @Override
    public void validate(INVITATION invitation) {
        if (invitation.getCreatedDate() == null) {
            invitation.setCreatedDate(new Date());
        }

        invitation.setUpdatedDate(new Date());

        if (invitation.getUid() == null) {
            invitation.setUid(uuid());
        }
    }



    @Override
    public INVITATION fetchByInvitationCode(String invitationCode) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("invitationCode", invitationCode);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<INVITATION> fetchByOwnerId(Long ownerId, INVITATION.Status status,
                                           INVITATION.Type type, INVITATION.Channel channel) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);

        if (status != null) {
            filter.put("status", status);
        }

        if (type != null) {
            filter.put("type", type);
        }

        if (channel != null) {
            filter.put("channel", channel);
        }

        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<INVITATION> fetchByContactId(Long contactId, INVITATION.Status status,
                                             INVITATION.Type type, INVITATION.Channel channel) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("contactId", contactId);

        if (status != null) {
            filter.put("status", status);
        }

        if (type != null) {
            filter.put("type", type);
        }

        if (channel != null) {
            filter.put("channel", channel);
        }

        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<INVITATION> fetchByEmail(String email, INVITATION.Status status,
                                         INVITATION.Type type, INVITATION.Channel channel) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("email", email);

        if (status != null) {
            filter.put("status", status);
        }

        if (type != null) {
            filter.put("type", type);
        }

        if (channel != null) {
            filter.put("channel", channel);
        }

        String[] sortOrder = {"invited_date DESC"};

        return fetchByCriteria(0, 99999, sortOrder, filter, false);
    }



    @Override
    public List<INVITATION> fetchByMobileNumber(String mobileNumber, INVITATION.Status status,
                                                INVITATION.Type type, INVITATION.Channel channel) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);

        if (status != null) {
            filter.put("status", status);
        }

        if (type != null) {
            filter.put("type", type);
        }

        if (channel != null) {
            filter.put("channel", channel);
        }

        String[] sortOrder = {"invited_date DESC"};

        return fetchByCriteria(0, 99999, sortOrder, filter, false);
    }



    private Long getInviteeUserId(CONTACT contact) {
        Long userId = contact.getRefUserId();

        if (userId == null) {
            USER user = null;

            if (contact.getMobileNumber() != null) {
                user = getUserService().fetchByMobileNumber(contact.getMobileNumber());
            }

            if (user == null && contact.getEmail() != null) {
                user = getUserService().fetchByEmail(contact.getEmail());
            }

            if (user != null) {
                userId = user.getId();
                contact.setRefUserId(userId);
                getContactService().update(contact);
            }
        }

        return userId;
    }



    @Override
    public INVITATION inviteByMobileNumber(Long ownerId, INVITATION.Type type, String mobileNumber, String firstName, boolean resend) {
        CONTACT contact = null;

        List<CONTACT> list = getContactService().fetchByMobileNumber(ownerId, mobileNumber);

        if (list == null || list.size() == 0)  {
            contact = getContactService().create(ownerId, mobileNumber, null, firstName, null);
        } else {
            contact = list.get(0);
        }

        return invite(contact.getId(), type, INVITATION.Channel.SMS, resend);
    }



    @Override
    public INVITATION inviteByEmail(Long ownerId, INVITATION.Type type, String email, String firstName, boolean resend) {
        CONTACT contact = null;

        List<CONTACT> list = getContactService().fetchByEmail(ownerId, email);

        if (list == null || list.size() == 0)  {
            contact = getContactService().create(ownerId, null, email, firstName, null);
        } else {
            contact = list.get(0);
        }

        return invite(contact.getId(), type, INVITATION.Channel.EMAIL, resend);
    }





    protected boolean existingUser(CONTACT contact) {
        USER user = null;

        if (contact.getRefUserId() != null) {
            user = getUserService().fetchById(contact.getRefUserId());
            if (user != null) {
                return true;
            } else {
                contact.setRefUserId(null);
                contact = getContactService().update(contact);
            }
        }

        if (contact.getMobileNumber() != null) {
            user = getUserService().fetchByMobileNumber(contact.getMobileNumber());
            if (user != null) {
                contact.setRefUserId(user.getId());
                contact = getContactService().update(contact);
                return true;
            }
        }

        if (contact.getEmail() != null) {
            user = getUserService().fetchByEmail(contact.getEmail());
            if (user != null) {
                contact.setRefUserId(user.getId());
                contact = getContactService().update(contact);
                return true;
            }
        }

        return false;
    }



    //FIXME: invitations can be made to current users who are not necessarily in the user's address book

    @Override
    public INVITATION invite(Long contactId, INVITATION.Type type, INVITATION.Channel channel, boolean resend) {

        CONTACT contact = getContactService().fetchById(contactId);

        if (existingUser(contact) && (type == INVITATION.Type.JOIN || channel != INVITATION.Channel.IN_APP)) {
            throw new KInvitationException("Invitation being sent to existing user.");
        }

        // first check if an invitation has already been sent
        INVITATION invitation = null;

        List<INVITATION> list = fetchByContactId(contact.getId(), null, type, null);

        for (INVITATION pastInvitation : list) {
            if (pastInvitation.getStatus() != INVITATION.Status.PENDING) {
                // we already sent an inviation to this person that was already accepted, ignored, etc.
                // only resend pending invitations
                logger.info("Invitation has already been sent to user and acknowledged: {}", pastInvitation);
                return pastInvitation;
            }

            // ok we have a pending invitation
            if (!resend) {
                logger.info("Found past invitation and resend set to false. Returning current invitation: {}", pastInvitation);
                return pastInvitation;
            } else {
                // if the pastInvitation is not on the same channel then we create a new invitation
                if (pastInvitation.getChannel() == channel) {
                    invitation = pastInvitation;
                    break;
                }
            }
        }

        try {
            if (invitation == null) {
                invitation = createInvitation(contact, type, channel);
            }

            String invitationUrl = getInvitationUrl(invitation.getOwnerId(), invitation.getInvitationCode());

            invitation = sendInvitation(invitation, contact, invitationUrl);

            Date now = new Date();
            int invitedCount = invitation.getInvitedCount();
            invitedCount += 1;
            invitation.setInvitedCount(invitedCount);
            invitation.setInvitedDate(now);
            update(invitation);

            contact.setInvitedDate(now);
            getContactService().update(contact);

            return invitation;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }



    protected INVITATION createInvitation(CONTACT contact, INVITATION.Type type, INVITATION.Channel channel) {

        //String code = sequence.getHexNo("confirmation.code", 9);
        String code = generateAccessCode();

        Long inviteeUserId = getInviteeUserId(contact);

        INVITATION invitation = getNewObject();
        invitation.setType(type);
        invitation.setChannel(channel);
        invitation.setStatus(INVITATION.Status.PENDING);
        invitation.setOwnerId(contact.getOwnerId());
        invitation.setContactId(contact.getId());
        invitation.setInviteeUserId(inviteeUserId);
        invitation.setInvitationCode(code);
        invitation.setFirstName(contact.getFirstName());
        invitation.setLastName(contact.getLastName());
        invitation.setDisplayName(contact.getDisplayName());
        invitation.setEmail(contact.getEmail());
        invitation.setMobileNumber(contact.getMobileNumber());
        invitation.setInvitedCount(0);
        invitation.setCreatedDate(new Date());

        return add(invitation);
    }



    @Override
    public  INVITATION accessInvitationCode(String invitationCode) {
        if (invitationCode == null) return null;

        INVITATION invitation = fetchByInvitationCode(invitationCode);

        if (invitation == null) return null;

        CONTACT contact = getContactService().fetchById(invitation.getContactId());

        switch (invitation.getChannel()) {
            case  EMAIL:
                if (contact != null) {
                    contact.setEmailVerified(true);
                    getContactService().update(contact);
                }
                break;

            case  SMS:
                if (contact != null) {
                    contact.setMobileVerified(true);
                    getContactService().update(contact);
                }
                break;

            default:
                break;
        }


        invitation.setViewedDate(new Date());

        return update(invitation);
    }



    @Override
    public  INVITATION accept(INVITATION invitation) {
        if (invitation.getInviteeUserId() == null) {
            throw new IllegalStateException("Cannot accept invitation if invitee userId is null.");
        }

        Long ownerId = invitation.getOwnerId();

        Long friendId = invitation.getInviteeUserId();

        invitation.setStatus(INVITATION.Status.ACCEPTED);

        invitation.setAcceptedDate(new Date());

        invitation = update(invitation);

        switch (invitation.getType()) {
            case FRIEND:
                // create friendship and notify user
                getFriendshipService().createFriendship(ownerId, friendId, null, true);
                break;
            default:

        }

        return invitation;
    }



    @Override
    public void processNewUserInvitations(Long ownerId) {
        USER user = getUserService().fetchById(ownerId);

        List<INVITATION> invitationList = null;

        if (user.getMobileNumber() != null) {
            invitationList = fetchByMobileNumber(user.getMobileNumber(),
                    INVITATION.Status.PENDING, null, INVITATION.Channel.SMS);
        }

        if (invitationList == null && user.getEmail() != null) {
            invitationList = fetchByEmail(user.getEmail(),
                    INVITATION.Status.PENDING, null, INVITATION.Channel.EMAIL);
        }

        if (invitationList == null) {
            logger.info("No pending invitations matched user: {}", user);
            return;
        }

        for (INVITATION invitation : invitationList) {
            invitation.setInviteeUserId(user.getId());
            invitation.setRegisteredDate(new Date());
            accept(invitation);

            if (invitation.getContactId() != null) {
                CONTACT ab = getContactService().fetchById(invitation.getContactId());
                ab.setRefUserId(user.getId());
                ab.setRegisteredDate(new Date());
                getContactService().update(ab);
            }
        }
    }
}
