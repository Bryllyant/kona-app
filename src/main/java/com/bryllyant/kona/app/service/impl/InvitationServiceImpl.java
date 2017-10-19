package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.InvitationMapper;
import com.bryllyant.kona.app.entity.AddressBook;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.Invitation;
import com.bryllyant.kona.app.entity.InvitationExample;
import com.bryllyant.kona.app.entity.KInvitationChannel;
import com.bryllyant.kona.app.entity.KInvitationType;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AddressBookService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.app.service.InvitationService;
import com.bryllyant.kona.app.service.KAbstractInvitationService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
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
		extends KAbstractInvitationService<Invitation,InvitationExample,AddressBook,Friendship,User> 
		implements InvitationService {
	
	private static Logger logger = LoggerFactory.getLogger(InvitationServiceImpl.class);

	@Autowired
	private InvitationMapper invitationDao;
	
	@Autowired
	private KConfig config;
	
	@Autowired
	AddressBookService addressBookService;
	
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
    

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected InvitationMapper getDao() {
		return invitationDao;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override @SuppressWarnings("unchecked")
	protected AddressBookService getAddressBookService() {
		return addressBookService;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override @SuppressWarnings("unchecked")
	protected FriendshipService getFriendshipService() {
		return friendshipService;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	protected Invitation getNewObject() {
		return new Invitation();
	}
	
	// ----------------------------------------------------------------------------

	@Override
	protected InvitationExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		InvitationExample example = new InvitationExample();

		if (sortOrder != null) {
			example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
		}

		if (startRow == null) startRow = 0;
		if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
		example.setDistinct(distinct);

		KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
		return example;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	protected String getInvitationUrl(Long userId, String code) {
		String url = config.getString("urlTemplate.system.invitationCode");
		url = url.replaceAll("\\{code\\}", code);

		url = shortUrlService.shorten(appService.getSystemApp().getId(), userId, url);
		return url;
	}
	
	// ----------------------------------------------------------------------------
	
	protected Invitation sendInvitation(Invitation invitation, AddressBook addressBook, String invitationUrl) {
		KInvitationChannel channel = KInvitationChannel.getInstance(invitation.getChannelId());
		
		switch (channel){
		case EMAIL:
			return sendEmail(invitation, addressBook, invitationUrl);
		case SMS:
			return sendSms(invitation, addressBook, invitationUrl);
		case TWITTER:
			return sendTwitter(invitation, addressBook, invitationUrl);
		case FACEBOOK:
			return sendFacebook(invitation, addressBook, invitationUrl);
		default:
			throw new IllegalArgumentException("Invalid invitation channel: " + channel);
		}	
	}
	
	// ----------------------------------------------------------------------------
	
	protected Invitation sendEmail(Invitation invitation, AddressBook addressBook, String invitationUrl) {
		return invitation;
		
	}
	
	// ----------------------------------------------------------------------------
	
	protected Invitation sendFacebook(Invitation invitation, AddressBook addressBook, String invitationUrl) {
		return invitation;
	}
	
	// ----------------------------------------------------------------------------
	
	protected Invitation sendTwitter(Invitation invitation, AddressBook addressBook, String invitationUrl) {
		return invitation;
	}
	
	// ----------------------------------------------------------------------------

	protected Invitation sendSms(Invitation invitation, AddressBook addressBook, String invitationUrl) {
		KInvitationType type = KInvitationType.getInstance(invitation.getTypeId());
		User user = userService.fetchById(invitation.getUserId());
		String to = addressBook.getMobileNumber();
		App app = appService.getSystemApp();

		String message = null;
		String templatePath = null;

		switch (type) {
		case JOIN:
			templatePath= config.getString("sms.templates.invitation.join");
			message = getSmsTextString(templatePath, user, addressBook, invitationUrl);
			break;

		case FRIEND:
			templatePath= config.getString("sms.templates.invitation.friend");
			message = getSmsTextString(templatePath, user, addressBook, invitationUrl);
			break;

		default:
			throw new IllegalStateException("Invalid invitation type: " + type);
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

	// ----------------------------------------------------------------------------

	private String getSmsTextString(String templatePath, User user, AddressBook addressBook, String invitationUrl) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("Util", KUtil.getInstance());
		params.put("user", user);
		params.put("firstName", addressBook.getFirstName());
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
