package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAccount;
import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.app.entity.KAppConfig;
import com.bryllyant.kona.app.entity.KEmail;
import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.app.entity.KEmailContent;
import com.bryllyant.kona.app.entity.KEmailEvent;
import com.bryllyant.kona.app.model.KEmailFooter;
import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KPush;
import com.bryllyant.kona.app.entity.KSms;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.util.KCallback;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.templates.KTemplate;
import com.bryllyant.kona.util.KClassUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class KAbstractSystemService<
        APP extends KApp, 
        APP_CONFIG extends KAppConfig,
        ACCOUNT extends KAccount,
        USER extends KUser,
        SMS extends KSms,
        PUSH extends KPush,
        EMAIL extends KEmail,
        EMAIL_EVENT extends KEmailEvent,
        EMAIL_CONTENT extends KEmailContent,
        EMAIL_ATTACHMENT extends KEmailAttachment,
        FILE extends KFile> 
        implements KSystemService<APP,ACCOUNT,USER,FILE,EMAIL,SMS,PUSH> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSystemService.class);


    private static Map<Long,Configuration> appConfigCache = new HashMap<Long,Configuration>();

    private APP systemApp;

    private USER systemUser;

    private ACCOUNT systemAccount;

    protected abstract FILE getNewFileObject();
    protected abstract EMAIL_ATTACHMENT getNewEmailAttachmentObject();

    protected abstract String getTestLoginCode();
    protected abstract String getSystemAppSlug();
    protected abstract String getSystemAccountSlug();
    protected abstract String getSystemUsername();
    protected abstract String getSystemMailAlertTo();
    protected abstract String getSystemMailFrom();
    protected abstract String getFilesBaseUrl();
    protected abstract String getAssetsBaseUrl();
    protected abstract String getAppBaseUrl();
    protected abstract String getTemplatePath(String templateName);
    
    protected abstract KEmailFooter getEmailFooter();

    protected abstract <S extends KAppConfigService<APP_CONFIG>> S getAppConfigService();
    protected abstract <S extends KAppService<APP>> S getAppService();
    protected abstract <S extends KUserService<USER>> S getUserService();
    protected abstract <S extends KAccountService<ACCOUNT>> S getAccountService();
    protected abstract <S extends KSmsService<SMS>> S getSmsService();
    protected abstract <S extends KPushService<PUSH>> S getPushService();
    protected abstract <S extends KEmailService<EMAIL,EMAIL_EVENT,EMAIL_CONTENT,EMAIL_ATTACHMENT>> S getEmailService();


    @Override
    public Configuration getConfig(Long appId) {
        Configuration result = appConfigCache.get(appId);

        if (result == null) {
            String _env = System.getProperty("env", "dev");

            KAppConfig.Env env = KAppConfig.Env.valueOf(_env.toUpperCase());

            Map<String,Object> config = getAppConfigService().getConfig(appId, env);

            if (config == null) {
                throw new IllegalStateException("Configuration not found for appId: " + appId);
            }

            result = new MapConfiguration(config);

            appConfigCache.put(appId, result);
        }

        return result;
    }

    private List<EMAIL_ATTACHMENT> toEmailAttachmentList(List<FILE> files) {
        if (files == null) return null;

        List<EMAIL_ATTACHMENT> attachments = new ArrayList<>();

        for (FILE file : files) {
            EMAIL_ATTACHMENT attachment = getNewEmailAttachmentObject();

            attachment.setFileId(file.getId());
            attachment.setName(file.getName());
            attachment.setContentType(file.getContentType());
            attachment.setData(file.getData());

            attachments.add(attachment);
        }
        
        return attachments;
    }


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
            List<FILE> attachments
            ) throws KEmailException {

        getEmailService().sendRawSMTP(
                body, 
                subject, 
                from, 
                replyTo, 
                to, 
                cc, 
                bcc, 
                html, 
                toEmailAttachmentList(attachments)
                );
    }


    @Override
    public void sendEmail(String body, String subject, String from, String replyTo,
            String to, boolean html, List<FILE> attachments, KCallback<EMAIL> callback) {

        KEmailFooter footer = getEmailFooter();

        List<EMAIL_ATTACHMENT> attachmentList = toEmailAttachmentList(attachments);

        new Thread(() -> {

            try {
                EMAIL email = getEmailService().send(body, subject, from, replyTo, to, html, attachmentList, footer);

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
    public void sendEmail(String templateName, Map<String,Object> params, String subject, String to, KCallback<EMAIL> callback) {
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
            List<FILE> attachments
            ) throws KEmailException {
        
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
            List<FILE> attachments,
            KCallback<EMAIL> callback
            ) throws KEmailException {

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
            List<FILE> mediaList,
            KCallback<EMAIL> callback
            ) throws KEmailException {

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
        params.put("Util", KUtil.getInstance());
        params.put("app", getSystemApp());

        String body = null;

        try {
            KTemplate t = new KTemplate(templatePath, params, appBaseUrl, filesBaseUrl);
            t.setInlineCss(true);
            body = t.toHtml();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new KEmailException(e.getMessage(), e);
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
    public void sendSms(String to, String body, KCallback callback) throws KSmsException {
        sendSms(to, body, (String) null, callback);
    }


    @Override
    public void sendSms(String to, String body, String mediaUrl, KCallback callback) throws KSmsException {
        List<String> urls = null;

        if (mediaUrl != null) {
            urls = new ArrayList<String>();
            urls.add(mediaUrl);
        }

        sendSms(to, body, urls, callback);
    }

    @Override
    public void sendSms(String to, String body, List<String> mediaUrls, KCallback<SMS> callback) throws KSmsException {
        sendSms(to, body, mediaUrls, null, callback);
    }


    @Override
    public void sendSms(String to, String body, List<String> mediaUrls, Long delay, KCallback<SMS> callback) throws KSmsException {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    if (delay != null) {
                        Thread.sleep(delay);
                    }

                    SMS sms = _sendSms(to, body, mediaUrls);

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


    private SMS _sendSms(String to, String body, List<String> mediaUrls) throws KSmsException {
        if (isTestPhoneNumber(to)) {
            return null;
        }

        return getSmsService().sendMessage(to, body, mediaUrls);
    }

    @Override
    public void sendPush(Long userId, String title, String message, String imageUrl, String actionUrl, boolean sandbox, KCallback<PUSH> callback) {
        new Thread(() -> {
            try {
                PUSH push = getPushService().send(userId, title, message, imageUrl, actionUrl, sandbox);

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
        } catch (KEmailException e) {
            logger.error(e.getMessage(), e);
        }
    }


    @Override
    public APP getSystemApp() {
        if (systemApp == null) {
            String appSlug = getSystemAppSlug();
            systemApp = getAppService().fetchBySlug(appSlug);
        }

        return systemApp;
    }


    @Override
    public ACCOUNT getSystemAccount() {
        if (systemAccount == null) {
            String slug = getSystemAccountSlug();
            systemAccount = getAccountService().fetchBySlug(slug);
        }

        return systemAccount;
    }


    @Override
    public USER getSystemUser() {
        if (systemUser == null) {
            String username = getSystemUsername();
            systemUser = getUserService().fetchByUsername(username);
        }

        return systemUser;
    }

    @Override
    public FILE toFile(byte[] data, String contentType, String filename) {
        String srcFilename = filename;
        String srcHostname = "127.0.0.1";
        boolean tempFile = false;
        return toFile(getSystemUser(), data, contentType, filename, srcFilename, srcHostname, tempFile);
    }

    @Override
    public FILE toFile(USER user, byte[] data, String contentType, String filename) {
        String srcFilename = filename;
        String srcHostname = "127.0.0.1";
        boolean tempFile = false;
        return toFile(user, data, contentType, filename, srcFilename, srcHostname, tempFile);
    }


    @Override
    public FILE toFile(USER user, byte[] data, String contentType, String filename, String srcFilename, String srcHostname, boolean tempFile) {

        @SuppressWarnings("unchecked")
        Class<FILE> clazz = (Class<FILE>) getNewFileObject().getClass();

        try {
            return KUtil.toFile(clazz, user, data, contentType, filename, srcFilename, srcHostname, tempFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*
        KFileType type = KFileType.getInstance(contentType, true);
        FILE file = getNewFileObject();
        file.setTypeId(type.getId());
        file.setAccessId(KFileAccess.PUBLIC.getId());
        file.setUserId(user.getId());
        file.setAccountId(user.getAccountId());
        file.setTokenId(null);
        file.setSrcFilename(srcFilename);
        file.setSrcHostname(srcHostname);
        file.setName(filename);
        file.setContentType(contentType);
        file.setSize(new Long(data.length));
        file.setActive(true);
        file.setEnabled(true);
        file.setHidden(false);
        file.setTempFile(tempFile);
        file.setCreatedDate(new Date());
        file.setData(data);
        return file;
         */
    }


    @Override
    public FILE saveUrlToFile(String url) throws IOException {
        return saveUrlToFile(null, url);
    }


    @Override
    public FILE saveUrlToFile(USER user, String url) throws IOException {
        if (user == null) {
            user = getSystemUser();
        }

        @SuppressWarnings("unchecked")
        Class<FILE> clazz = (Class<FILE>) getNewFileObject().getClass();

        try {
            return KUtil.saveUrlToFile(clazz, user, url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        /*
        URL u = new URL(url);
        URLConnection uc = u.openConnection();
        byte[] data = KFileUtil.toByteArray(uc.getInputStream());
        return toFile(user, data, uc.getContentType(), u.getPath());
         */
    }


    @Override
    public boolean isTestPhoneNumber(String phoneNumber) {
        return getSmsService().isTestPhoneNumber(phoneNumber);
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
