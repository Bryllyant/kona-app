package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.InvitationMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Contact;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.InvitationExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.ContactService;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.app.service.KAbstractInvitationService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.templates.KTemplate;
import com.bryllyant.kona.templates.KTemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service(InvitationService.SERVICE_PATH)
public class InvitationServiceImpl 
		extends KAbstractInvitationService<Invitation, InvitationExample, InvitationMapper,Contact,Friendship,User>
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
	

	
	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}
	

	
	@Override @SuppressWarnings("unchecked")
	protected ContactService getContactService() {
		return contactService;
	}
	

	
	@Override @SuppressWarnings("unchecked")
	protected FriendshipService getFriendshipService() {
		return friendshipService;
	}
	

	
	@Override
	protected Invitation getNewObject() {
		return new Invitation();
	}
	


	 @Override
    protected InvitationExample getEntityExampleObject() { return new InvitationExample(); }

	

	
	@Override
	protected String getInvitationUrl(Long userId, String code) {
		String url = config.getString("urlTemplate.system.invitationCode");
		url = url.replaceAll("\\{code\\}", code);

		url = shortUrlService.shorten(userId, url);
		return url;
	}
	

	
	protected Invitation sendInvitation(Invitation invitation, Contact contact, String invitationUrl) {
		switch (invitation.getChannel()){
		case EMAIL:
			return sendEmail(invitation, contact, invitationUrl);
		case SMS:
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
	
}
