package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.model.KEmailFooter;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.KAbstractSystemService;
import com.bryllyant.kona.app.service.PushService;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(SystemService.SERVICE_PATH)
public class SystemServiceImpl 
    extends KAbstractSystemService<
            App,
            AppConfig,
            Account,
            User,
            Sms,
            Push,
            Email,
            EmailEvent,
            EmailContent,
            EmailAttachment,
            File>
        implements SystemService {

    private static Logger logger = LoggerFactory.getLogger(SystemService.class);

    @Autowired
    private KConfig config;

    @Autowired
    private AppService appService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PushService pushService;

    @Autowired
    private AppUtil util;


    protected File getNewFileObject() {
        return new File();
    }
    

    protected EmailAttachment getNewEmailAttachmentObject() {
        return new EmailAttachment();
    }


    protected String getTestLoginCode() {
        return config.getString("system.test.loginCode");
    }


    protected String getSystemAppSlug() {
        return config.getString("system.app.slug");
    }


    protected String getSystemAccountSlug() {
        return config.getString("system.account.slug");
    }


    protected String getSystemUsername() {
        return config.getString("system.username"); 
    }



    protected String getSystemMailAlertTo() {
        return config.getString("system.mail.alertTo");
    }


    protected String getSystemMailFrom() {
        return config.getString("system.mail.from");
    }


    protected String getFilesBaseUrl() {
        return config.getString("system.files.baseUrl"); 
    }


    protected String getAssetsBaseUrl() {
        return config.getString("system.assets.baseUrl"); 
    }


    protected String getAppBaseUrl() {
        return config.getString("system.app.baseUrl");
    }


    protected String getTemplatePath(String templateName) {
        return config.getString(templateName);
    }

    @Override @SuppressWarnings("unchecked")
    protected PushService getPushService() {
        return pushService;
    }

    @Override @SuppressWarnings("unchecked")
    protected AppService getAppService() {
        return appService;
    }

    @Override @SuppressWarnings("unchecked")
    protected AccountService getAccountService() {
        return accountService;
    }

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }


    @Override @SuppressWarnings("unchecked")
    protected AppConfigService getAppConfigService() {
        return appConfigService;
    }


    @Override @SuppressWarnings("unchecked")
    protected EmailService getEmailService() {
        return emailService;
    }


    @Override @SuppressWarnings("unchecked")
    protected SmsService getSmsService() {
        return smsService;
    }


    @Override
    protected KEmailFooter getEmailFooter() {
        return util.getEmailFooter();

    }
}