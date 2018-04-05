package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.model.EmailFooter;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.EmailException;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.PushService;
import com.bryllyant.kona.app.service.SmsException;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.templates.KTemplate;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.Callback;
import com.bryllyant.kona.util.KClassUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(SystemService.SERVICE_PATH)
public class SystemServiceImpl implements SystemService {

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

    private static Map<Long,Configuration> appConfigCache = new HashMap<>();

    private App systemApp;

    private User systemUser;

    private Account systemAccount;



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


    protected EmailFooter getEmailFooter() {
        return util.getEmailFooter();

    }


    @Override
    public Configuration getConfig(Long appId) {
        Configuration result = appConfigCache.get(appId);

        if (result == null) {
            String _env = System.getProperty("env", "dev");

            AppConfig.Env env = AppConfig.Env.valueOf(_env.toUpperCase());

            Map<String,Object> config = appConfigService.getConfig(appId, env);

            if (config == null) {
                throw new IllegalStateException("Configuration not found for appId: " + appId);
            }

            result = new MapConfiguration(config);

            appConfigCache.put(appId, result);
        }

        return result;
    }

//    private List<EmailAttachment> toEmailAttachmentList(List<File> files) {
//        if (files == null) return null;
//
//        List<EmailAttachment> attachments = new ArrayList<>();
//
//        for (File file : files) {
//            EmailAttachment attachment = new EmailAttachment();
//
//            attachment.setFileId(file.getId());
//            attachment.setName(file.getName());
//            attachment.setContentType(file.getContentType());
//            attachment.setData(file.getData());
//
//            attachments.add(attachment);
//        }
//
//        return attachments;
//    }


    @Override
    public void sendRawEmail(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            boolean html,
            List<File> attachments
    ) throws EmailException {

        emailService.sendRawSMTP(
                body,
                subject,
                from,
                replyTo,
                to,
                cc,
                bcc,
                html,
                attachments
        );
    }


    @Override
    public void sendEmail(String body, String subject, String from, String replyTo,
                          String to, boolean html, List<File> attachments, Callback<Email> callback) {

        EmailFooter footer = getEmailFooter();

        new Thread(() -> {

            try {
                Email email = emailService.send(body, subject, from, replyTo, to, html, attachments, footer);

                if (callback != null) {
                    callback.success(email);
                }
            } catch (Throwable t) {
                logger.error(t.getMessage(), t);
                if (callback != null) {
                    callback.error(t);
                }
            }
        }).start();
    }


    @Override
    public void sendEmail(String templateName, Map<String,Object> params, String subject, String to, Callback<Email> callback) {
        sendEmail(templateName, params, subject, null, null, to, null, callback);
    }


    @Override
    public void sendRawEmail(
            String templateName,
            Map<String,Object> params,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            List<File> attachments
    ) throws EmailException {

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    _sendEmail(templateName, params, subject, from, replyTo, to, cc, bcc, attachments, null);
                } catch (Exception e) {
                    alert("Error sending email for template: " + templateName, e);
                }
            }
        };

        t.start();
    }


    @Override
    public void sendEmail(
            String templateName,
            Map<String,Object> params,
            String subject,
            String from,
            String replyTo,
            String to,
            List<File> attachments,
            Callback<Email> callback
    ) throws EmailException {

        _sendEmail(templateName, params, subject, from, replyTo, to, null, null, attachments, callback);

    }


    private void _sendEmail(
            String templateName,
            Map<String,Object> params,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            List<File> mediaList,
            Callback<Email> callback
    ) throws EmailException {

        String templatePath = getTemplatePath(templateName);
        String appBaseUrl = getAppBaseUrl();
        String filesBaseUrl = getFilesBaseUrl();
        String assetsBaseUrl = getAssetsBaseUrl();

        if (from == null) {
            from = getSystemMailFrom();
        }

        if (replyTo == null) {
            replyTo = from;
        }

        if (params == null) {
            params = new HashMap<String,Object>();
        }

        params.put("ASSETS_BASE_URL", assetsBaseUrl);
        params.put("Util", util);
        params.put("app", getSystemApp());

        String body = null;

        try {
            KTemplate t = new KTemplate(templatePath, params, appBaseUrl, filesBaseUrl);
            t.setInlineCss(true);
            body = t.toHtml();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EmailException(e.getMessage(), e);
        }

        boolean html = true;

        String email = "\n"
                + "\ntemplateName: " + templateName
                + "\nparams: " + KClassUtil.toString(params)
                + "\nsubject: " + subject
                + "\nfrom: " + from
                + "\nreplyTo: " + replyTo
                + "\nto: " + to
                + "\ncc: " + cc
                + "\nbcc: " + bcc
                + "\nhtml: " + html
                + "\nbody: " + body.substring(1, 100);

        logger.debug("sendEmail: message properties:" + email);

        if (cc == null && bcc == null) {
            sendEmail(body, subject, from, replyTo, to, html, mediaList, callback);
        } else {
            sendRawEmail(body, subject, from, replyTo, to, cc, bcc, html, mediaList);
        }
    }


    @Override
    public void sendSms(String to, String body, Callback callback) throws SmsException {
        sendSms(to, body, (String) null, callback);
    }


    @Override
    public void sendSms(String to, String body, String mediaUrl, Callback callback) throws SmsException {
        List<String> urls = null;

        if (mediaUrl != null) {
            urls = new ArrayList<String>();
            urls.add(mediaUrl);
        }

        sendSms(to, body, urls, callback);
    }

    @Override
    public void sendSms(String to, String body, List<String> mediaUrls, Callback<Sms> callback) throws SmsException {
        sendSms(to, body, mediaUrls, null, callback);
    }


    @Override
    public void sendSms(String to, String body, List<String> mediaUrls, Long delay, Callback<Sms> callback) throws SmsException {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    if (delay != null) {
                        Thread.sleep(delay);
                    }

                    Sms sms = _sendSms(to, body, mediaUrls);

                    if (callback != null) {
                        callback.success(sms);
                    }

                } catch (Exception e) {
                    logger.error(e.getMessage(), e);

                    alert("Error sending sms to number: " + to, e);

                    if (callback != null) {
                        callback.error(e);
                    }
                }
            }
        };

        t.start();
    }


    private Sms _sendSms(String to, String body, List<String> mediaUrls) throws SmsException {
        if (isTestPhoneNumber(to)) {
            return null;
        }

        return smsService.sendMessage(to, body, mediaUrls);
    }

    @Override
    public void sendPush(Long userId, String title, String message, String imageUrl, String actionUrl, boolean sandbox, Callback<Push> callback) {
        new Thread(() -> {
            try {
                Push push = pushService.send(userId, title, message, imageUrl, actionUrl, sandbox);

                if (callback != null) {
                    callback.success(push);
                }
            } catch (Throwable t) {
                logger.error(t.getMessage(), t);

                alert("Error sending push to userId: " + userId, t);

                if (callback != null) {
                    callback.error(t);
                }
            }
        }).start();
    }

    @Override
    public void alert(String subject, Throwable t) {
        alert(subject, null, t);
    }


    @Override
    public void alert(String subject, String body) {
        alert(subject, body, false);
    }


    @Override
    public void alert(String subject, String body, Throwable t) {
        if (body == null) {
            body = "";
        }

        if (t != null) {
            StringWriter s = new StringWriter();
            t.printStackTrace(new PrintWriter(s));
            body += "\n\n" + s.toString();
        }

        alert(subject, body, false);
    }


    @Override
    public void alert(String subject, String body, boolean html) {

        String from = getSystemMailFrom();

        String to = getSystemMailAlertTo();

        String replyTo = from;

        try {
            sendEmail(body, subject, from, replyTo, to, html, null, null);
        } catch (EmailException e) {
            logger.error(e.getMessage(), e);
        }
    }


    @Override
    public App getSystemApp() {
        if (systemApp == null) {
            String appSlug = getSystemAppSlug();
            systemApp = appService.fetchBySlug(appSlug);
        }

        return systemApp;
    }


    @Override
    public Account getSystemAccount() {
        if (systemAccount == null) {
            String slug = getSystemAccountSlug();
            systemAccount = accountService.fetchBySlug(slug);
        }

        return systemAccount;
    }


    @Override
    public User getSystemUser() {
        if (systemUser == null) {
            String username = getSystemUsername();
            systemUser = userService.fetchByUsername(username);
        }

        return systemUser;
    }

    @Override
    public File toFile(byte[] data, String contentType, String filename) {
        String srcFilename = filename;
        String srcHostname = "127.0.0.1";
        boolean tempFile = false;
        return toFile(getSystemUser(), data, contentType, filename, srcFilename, srcHostname, tempFile);
    }

    @Override
    public File toFile(User user, byte[] data, String contentType, String filename) {
        String srcFilename = filename;
        String srcHostname = "127.0.0.1";
        boolean tempFile = false;
        return toFile(user, data, contentType, filename, srcFilename, srcHostname, tempFile);
    }


    @Override
    public File toFile(User user, byte[] data, String contentType, String filename, String srcFilename, String srcHostname, boolean tempFile) {
            return util.toFile(user, data, contentType, filename, srcFilename, srcHostname, tempFile);
    }


    @Override
    public File saveUrlToFile(String url) throws IOException {
        return saveUrlToFile(null, url);
    }


    @Override
    public File saveUrlToFile(User user, String url) throws IOException {
        if (user == null) {
            user = getSystemUser();
        }

        try {
            return util.saveUrlToFile(user, url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean isTestPhoneNumber(String phoneNumber) {
        return smsService.isTestPhoneNumber(phoneNumber);
    }


    @Override
    public boolean isTestLoginCode(String code) {
        String testCode = getTestLoginCode();

        if (code != null && testCode != null && testCode.equalsIgnoreCase(code)) {
            return true;
        }

        return false;
    }


    // Assumes US number
    @Override
    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("+1")) {
            phoneNumber = phoneNumber.substring(2);
        }

        String testNumber = phoneNumber.replaceAll("[^\\d]", "");

        testNumber = "+1" + testNumber;

        return testNumber;
    }




//
//    public AccessToken findToken(HttpServletRequest req) {
//        AccessToken token = null;
//        String tokenValue = null;
//
//        if (config == null) {
//            throw new IllegalStateException("Configuration not Autowired");
//        }
//
//        String headerName = config.getString("system.api.header.paramName.apiKey", "X-API-KEY");
//
//        if (headerName != null) {
//            tokenValue = req.getHeader(headerName);
//        }
//
//        // If token is not in the header, see if it's a request parameter
//        if (tokenValue == null) {
//            tokenValue = req.getParameter("client_id");
//        }
//
//        if (tokenValue != null) {
//            token = new AccessToken(tokenValue);
//        }
//
//        //logger.debug("clientId: token: " + token);
//
//        return token;
//    }
//
//    public String getClientId(HttpServletRequest req) {
//        String clientId = null;
//
//        AccessToken token = tokenReader.findToken(req);
//
//        if (token != null) {
//            clientId = token.getValue();
//        }
//
//        return clientId;
//    }
//
//    public Long getAppId(HttpServletRequest req) {
//        String clientId = apiAuthService.getClientId(req);
//
//        AppCreds creds = appCredsService.fetchByClientId(clientId);
//
//        return creds.getAppId();
//    }
//
//    public KServiceClient getServiceClient(Long appId, Long userId, Long deviceId, HttpServletRequest req) {
//        KServiceClient client = new KServiceClient();
//
//        if (appId == null) {
//            appId = getAppId(req);
//        }
//
//        String accessToken = apiAuthService.getAccessToken(req);
//
//        if (userId == null && accessToken != null) {
//            User user = apiAuthService.fetchUserByAccessToken(accessToken);
//
//            if (user != null) {
//                userId = user.getId();
//            }
//        }
//
//        if (accessToken != null) {
//            Token token = tokenService.fetchByAccessToken(accessToken);
//
//            if (token != null && !tokenService.isValid(token, false)) {
//                client.setTokenId(token.getId());
//            }
//        }
//
//        client.setAppId(appId);
//        client.setUserId(userId);
//        client.setDeviceId(deviceId);
//        client.setAppClientId(apiAuthService.getClientId(req));
//        client.setHostname(KServletUtil.getClientHostname(req));
//        client.setUserAgent(KServletUtil.getClientUserAgent(req));
//        client.setLatitude(KServletUtil.getClientLatitude(req));
//        client.setLongitude(KServletUtil.getClientLongitude(req));
//        client.setRequestUrl(KServletUtil.getFullRequestURL(req));
//
//        return client;
//    }
}