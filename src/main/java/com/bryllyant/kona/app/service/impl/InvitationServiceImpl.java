package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.InvitationMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Contact;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.InvitationExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.ContactService;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.app.service.InvitationException;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.templates.KTemplate;
import com.bryllyant.kona.templates.KTemplateException;
import com.bryllyant.kona.util.KUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(InvitationService.SERVICE_PATH)
public class InvitationServiceImpl 
		extends KAbstractService<Invitation, InvitationExample, InvitationMapper>
		implements InvitationService {
	
	private static Logger logger = LoggerFactory.getLogger(InvitationServiceImpl.class);

	@Autowired
	private InvitationMapper invitationMapper;
	
	@Autowired
	private KConfig config;
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	FriendshipService friendshipService;
	
	@Autowired
	ShortUrlService shortUrlService;
	
	@Autowired
	AppService appService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SystemService system;
    
	@Override @SuppressWarnings("unchecked")
	protected InvitationMapper getMapper() {
		return invitationMapper;
	}
	

	protected String getInvitationUrl(Long userId, String code) {
		String url = config.getString("urlTemplate.system.invitationCode");
		url = url.replaceAll("\\{code\\}", code);

		url = shortUrlService.shorten(userId, url);
		return url;
	}
	

	protected Invitation sendInvitation(Invitation invitation, Contact contact, String invitationUrl) {
		switch (invitation.getChannel()){
		case Email:
			return sendEmail(invitation, contact, invitationUrl);
		case Sms:
			return sendSms(invitation, contact, invitationUrl);
		case TWITTER:
			return sendTwitter(invitation, contact, invitationUrl);
		case FACEBOOK:
			return sendFacebook(invitation, contact, invitationUrl);
		default:
			throw new IllegalArgumentException("Invalid invitation channel: " + invitation.getChannel());
		}	
	}
	

	protected Invitation sendEmail(Invitation invitation, Contact contact, String invitationUrl) {
		return invitation;
	}
	

	protected Invitation sendFacebook(Invitation invitation, Contact contact, String invitationUrl) {
		return invitation;
	}

	
	protected Invitation sendTwitter(Invitation invitation, Contact contact, String invitationUrl) {
		return invitation;
	}
	


	protected Invitation sendSms(Invitation invitation, Contact contact, String invitationUrl) {
		User user = userService.fetchById(invitation.getOwnerId());

		String to = contact.getMobileNumber();

		App app = appService.getSystemApp();

		String message = null;
		String templatePath = null;

		switch (invitation.getType()) {
		case JOIN:
			templatePath= config.getString("sms.templates.invitation.join");
			message = getSmsTextString(templatePath, user, contact, invitationUrl);
			break;

		case FRIEND:
			templatePath= config.getString("sms.templates.invitation.friend");
			message = getSmsTextString(templatePath, user, contact, invitationUrl);
			break;

		default:
			throw new IllegalStateException("Invalid invitation type: " + invitation.getType());
		}


		message = "[" + app.getName() + "] " + message;

		invitation.setMessage(message);

		try {
			logger.debug("sendInvitationSms: [" + user.getId() + "]\nto: " + to + "\nmessage:" + message);
			system.sendSms(to, message, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return invitation;
	}



	private String getSmsTextString(String templatePath, User user, Contact contact, String invitationUrl) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("Util", KUtil.getInstance());
		params.put("user", user);
		params.put("firstName", contact.getFirstName());
		params.put("invitationUrl", invitationUrl);

		String body = null;

		try {
			KTemplate t = new KTemplate(templatePath, params, null, null);
			t.setInlineCss(false);
			body = t.toString();
			body = body.trim();
		} catch (KTemplateException e) {
			logger.error(e.getMessage(), e);
		}

		return body;
	}



    protected String generateAccessCode() {
        return uuid();
    }



    @Override
    public void validate(Invitation invitation) {
        if (invitation.getCreatedDate() == null) {
            invitation.setCreatedDate(new Date());
        }

        invitation.setUpdatedDate(new Date());

        if (invitation.getUid() == null) {
            invitation.setUid(uuid());
        }
    }



    @Override
    public Invitation fetchByInvitationCode(String invitationCode) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("invitationCode", invitationCode);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<Invitation> fetchByOwnerId(Long ownerId, Invitation.Status status,
                                           Invitation.Type type, Invitation.Channel channel) {
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
    public List<Invitation> fetchByContactId(Long contactId, Invitation.Status status,
                                             Invitation.Type type, Invitation.Channel channel) {
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
    public List<Invitation> fetchByEmail(String email, Invitation.Status status,
                                         Invitation.Type type, Invitation.Channel channel) {
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
    public List<Invitation> fetchByMobileNumber(String mobileNumber, Invitation.Status status,
                                                Invitation.Type type, Invitation.Channel channel) {
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



    private Long getInviteeUserId(Contact contact) {
        Long userId = contact.getRefUserId();

        if (userId == null) {
            User user = null;

            if (contact.getMobileNumber() != null) {
                user = userService.fetchByMobileNumber(contact.getMobileNumber());
            }

            if (user == null && contact.getEmail() != null) {
                user = userService.fetchByEmail(contact.getEmail());
            }

            if (user != null) {
                userId = user.getId();
                contact.setRefUserId(userId);
                contactService.update(contact);
            }
        }

        return userId;
    }



    @Override
    public Invitation inviteByMobileNumber(Long ownerId, Invitation.Type type, String mobileNumber, String firstName, boolean resend) {
        Contact contact = null;

        List<Contact> list = contactService.fetchByMobileNumber(ownerId, mobileNumber);

        if (list == null || list.size() == 0)  {
            contact = contactService.create(ownerId, mobileNumber, null, firstName, null);
        } else {
            contact = list.get(0);
        }

        return invite(contact.getId(), type, Invitation.Channel.Sms, resend);
    }



    @Override
    public Invitation inviteByEmail(Long ownerId, Invitation.Type type, String email, String firstName, boolean resend) {
        Contact contact = null;

        List<Contact> list = contactService.fetchByEmail(ownerId, email);

        if (list == null || list.size() == 0)  {
            contact = contactService.create(ownerId, null, email, firstName, null);
        } else {
            contact = list.get(0);
        }

        return invite(contact.getId(), type, Invitation.Channel.Email, resend);
    }





    protected boolean existingUser(Contact contact) {
        User user = null;

        if (contact.getRefUserId() != null) {
            user = userService.fetchById(contact.getRefUserId());
            if (user != null) {
                return true;
            } else {
                contact.setRefUserId(null);
                contact = contactService.update(contact);
            }
        }

        if (contact.getMobileNumber() != null) {
            user = userService.fetchByMobileNumber(contact.getMobileNumber());
            if (user != null) {
                contact.setRefUserId(user.getId());
                contact = contactService.update(contact);
                return true;
            }
        }

        if (contact.getEmail() != null) {
            user = userService.fetchByEmail(contact.getEmail());
            if (user != null) {
                contact.setRefUserId(user.getId());
                contact = contactService.update(contact);
                return true;
            }
        }

        return false;
    }



    //FIXME: invitations can be made to current users who are not necessarily in the user's address book

    @Override
    public Invitation invite(Long contactId, Invitation.Type type, Invitation.Channel channel, boolean resend) {

        Contact contact = contactService.fetchById(contactId);

        if (existingUser(contact) && (type == Invitation.Type.JOIN || channel != Invitation.Channel.IN_APP)) {
            throw new InvitationException("Invitation being sent to existing user.");
        }

        // first check if an invitation has already been sent
        Invitation invitation = null;

        List<Invitation> list = fetchByContactId(contact.getId(), null, type, null);

        for (Invitation pastInvitation : list) {
            if (pastInvitation.getStatus() != Invitation.Status.PENDING) {
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
            contactService.update(contact);

            return invitation;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }



    protected Invitation createInvitation(Contact contact, Invitation.Type type, Invitation.Channel channel) {

        //String code = sequence.getHexNo("confirmation.code", 9);
        String code = generateAccessCode();

        Long inviteeUserId = getInviteeUserId(contact);

        Invitation invitation = new Invitation();
        invitation.setType(type);
        invitation.setChannel(channel);
        invitation.setStatus(Invitation.Status.PENDING);
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
    public  Invitation accessInvitationCode(String invitationCode) {
        if (invitationCode == null) return null;

        Invitation invitation = fetchByInvitationCode(invitationCode);

        if (invitation == null) return null;

        Contact contact = contactService.fetchById(invitation.getContactId());

        switch (invitation.getChannel()) {
            case  Email:
                if (contact != null) {
                    contact.setEmailVerified(true);
                    contactService.update(contact);
                }
                break;

            case  Sms:
                if (contact != null) {
                    contact.setMobileVerified(true);
                    contactService.update(contact);
                }
                break;

            default:
                break;
        }


        invitation.setViewedDate(new Date());

        return update(invitation);
    }



    @Override
    public  Invitation accept(Invitation invitation) {
        if (invitation.getInviteeUserId() == null) {
            throw new IllegalStateException("Cannot accept invitation if invitee userId is null.");
        }

        Long ownerId = invitation.getOwnerId();

        Long friendId = invitation.getInviteeUserId();

        invitation.setStatus(Invitation.Status.ACCEPTED);

        invitation.setAcceptedDate(new Date());

        invitation = update(invitation);

        switch (invitation.getType()) {
            case FRIEND:
                // create friendship and notify user
                friendshipService.createFriendship(ownerId, friendId, null, true);
                break;
            default:

        }

        return invitation;
    }



    @Override
    public void processNewUserInvitations(Long ownerId) {
        User user = userService.fetchById(ownerId);

        List<Invitation> invitationList = null;

        if (user.getMobileNumber() != null) {
            invitationList = fetchByMobileNumber(user.getMobileNumber(),
                    Invitation.Status.PENDING, null, Invitation.Channel.Sms);
        }

        if (invitationList == null && user.getEmail() != null) {
            invitationList = fetchByEmail(user.getEmail(),
                    Invitation.Status.PENDING, null, Invitation.Channel.Email);
        }

        if (invitationList == null) {
            logger.info("No pending invitations matched user: {}", user);
            return;
        }

        for (Invitation invitation : invitationList) {
            invitation.setInviteeUserId(user.getId());
            invitation.setRegisteredDate(new Date());
            accept(invitation);

            if (invitation.getContactId() != null) {
                Contact ab = contactService.fetchById(invitation.getContactId());
                ab.setRefUserId(user.getId());
                ab.setRegisteredDate(new Date());
                contactService.update(ab);
            }
        }
    }
	
}
