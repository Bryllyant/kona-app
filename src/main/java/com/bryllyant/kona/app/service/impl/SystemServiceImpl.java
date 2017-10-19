package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.KEmailFooter;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.KAbstractSystemService;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service(SystemService.SERVICE_PATH)
public class SystemServiceImpl 
    extends KAbstractSystemService<
            App,
            AppConfig,
            User,
            Sms,
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
    private AppConfigService appConfigService;

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    // ----------------------------------------------------------------------------

    protected File getNewFileObject() {
        return new File();
    }
    
    // ----------------------------------------------------------------------------

    protected EmailAttachment getNewEmailAttachmentObject() {
        return new EmailAttachment();
    }

    // ----------------------------------------------------------------------------

    protected String getTestLoginCode() {
        return config.getString("system.test.loginCode");
    }

    // ----------------------------------------------------------------------------

    protected String getSystemUsername() {
        return config.getString("system.username"); 
    }

    // ----------------------------------------------------------------------------

    protected String getSystemAppSlug() {
        return config.getString("system.app.slug");
    }

    // ----------------------------------------------------------------------------

    protected String getSystemMailAlertTo() {
        return config.getString("system.mail.alertTo");
    }

    // ----------------------------------------------------------------------------

    protected String getSystemMailFrom() {
        return config.getString("system.mail.from");
    }

    // ----------------------------------------------------------------------------

    protected String getFilesBaseUrl() {
        return config.getString("system.files.baseUrl"); 
    }

    // ----------------------------------------------------------------------------

    protected String getAssetsBaseUrl() {
        return config.getString("system.assets.baseUrl"); 
    }

    // ----------------------------------------------------------------------------

    protected String getAppBaseUrl() {
        return config.getString("system.app.baseUrl");
    }

    // ----------------------------------------------------------------------------

    protected String getTemplatePath(String templateName) {
        return config.getString(templateName);
    }

    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }

    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected AppService getAppService() {
        return appService;
    }

    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected AppConfigService getAppConfigService() {
        return appConfigService;
    }

    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected EmailService getEmailService() {
        return emailService;
    }

    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected SmsService getSmsService() {
        return smsService;
    }

    // ----------------------------------------------------------------------------

    @Override
    protected KEmailFooter getEmailFooter() {
        Integer copyrightYear = KDateUtil.getYear(new Date());
        String copyrightHolder = config.getString("email.footer.copyrightHolder");
        String companyName = config.getString("email.footer.companyName");
        String street1 = config.getString("email.footer.address.street1");
        String street2 = config.getString("email.footer.address.street2");
        String city = config.getString("email.footer.address.city");
        String state = config.getString("email.footer.address.state");
        String postalCode = config.getString("email.footer.address.postalCode");
        String country = config.getString("email.footer.address.country");
        
        String defaultPermissionReminder = 
                "We sent you this email because you signed up with us or requested to receive "
                + "information related to one of our services.";
        
        String permissionReminder = config.getString("email.footer.permissionReminder", defaultPermissionReminder);

        KEmailFooter footer = new KEmailFooter();

        footer.setType(KEmailFooter.Type.TRANSACTIONAL);

        footer.setCopyrightHolder(copyrightHolder);
        footer.setCopyrightYear(copyrightYear);
        footer.setCompanyName(companyName);
        footer.setStreet1(street1);
        footer.setStreet1(street2);
        footer.setCity(city);
        footer.setState(state);
        footer.setPostalCode(postalCode);
        footer.setCountry(country);
        
        footer.setPermissionReminder(permissionReminder);

        return footer;
    }
}