/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.util;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.model.EmailFooter;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.SystemException;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;


/**
 * AppUtil
 */
@Component
public class AppUtil {
    private static Logger logger = LoggerFactory.getLogger(AppUtil.class);

    public final static String BIRTH_DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    private KConfig config;

    @Autowired
    private FileService fileService;

    @Autowired
    private SystemService system;


    public String getConfigUrl(String key) {
        String url = config.getString(key);

        if (url != null) {
            if (!url.endsWith("/")) {
                url += "/";
            }
        }

        return url;
    }


    public String toAbsoluteUrl(String filePublicPath) {
        if (filePublicPath == null) return null;

        if (filePublicPath.startsWith("http://")
                || filePublicPath.startsWith("https://")
                || filePublicPath.startsWith("file://")
                ) {
            return filePublicPath;
        }

        return fileService.toAbsoluteUrl(filePublicPath);
    }



    public Date toDate(String jsonDate) {
        if (jsonDate == null) return null;

        logger.debug("toDate called for jsonDate: " + jsonDate);

        try {
            return KDateUtil.parseJson(jsonDate);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new BadRequestException("Invalid date: " + jsonDate);
        }
    }



    public String getGenderValue(Object o) {
        String value = getStringValue(o);

        if (value == null) return null;

        if (value.equalsIgnoreCase("male") || value.equalsIgnoreCase("m")) {
            return "male";
        }

        if (value.equalsIgnoreCase("female") || value.equalsIgnoreCase("f")) {
            return "female";
        }

        throw new BadRequestException("Invalid gender: " + value);
    }




    public String getStringValue(Object o) {
        if (o == null) {
            return null;
        }

        String s = o.toString().trim();

        if (s.length() == 0) {
            return null;
        }

        // clean up untrusted html
        s = Jsoup.clean(s, Whitelist.basic());

        return s;
    }



    public Double getDoubleValue(Object o) {
        String s = getStringValue(o);

        if (s == null) {
            return null;
        }

        return Double.valueOf(s);
    }



    public Integer getIntegerValue(Object o) {
        String s = getStringValue(o);

        if (s == null) {
            return null;
        }

        return Integer.valueOf(s);
    }



    public Long getLongValue(Object o) {
        String s = getStringValue(o);

        if (s == null) {
            return null;
        }

        return Long.valueOf(s);
    }



    public Boolean getBooleanValue(Object o) {
        return getBooleanValue(o, null);

    }



    public Boolean getBooleanValue(Object o, Boolean defaultValue) {
        String s = getStringValue(o);

        if (s == null) {
            return defaultValue;
        }

        return Boolean.valueOf(s);
    }



    public Date getDateValue(Object o) {
        logger.debug("getDateValue called for raw object: " + o);

        String s = getStringValue(o);

        if (s == null) return null;

        Date d = toDate(s);

        logger.debug("getDateValue called: "
                + "\n\traw object: " + o
                + "\n\tString value: " + s
                + "\n\tDate value: " + d);

        //return toDate(getStringValue(o));
        return d;
    }



    public String getPhoneNumberValue(Object o) {
        String s = getStringValue(o);

        if (s == null) {
            return null;
        }

        // Remove whitespace from the number
        s = s.replaceAll("\\s+", "");

        if (!system.isTestPhoneNumber(s) && !KValidator.isE164PhoneNumber(s)) {
            throw new BadRequestException("Invalid mobile number [" + s + "]. Phone numbers must be in E.164 format.");
        }

        return s;
    }



    public String getEmailValue(Object o) {
        String s = getStringValue(o);

        if (s == null) {
            return null;
        }

        if (!KValidator.isEmail(s)) {
            throw new BadRequestException("Invalid email address: " + s);
        }

        return s;
    }



    public String getUrlValue(Object o) {
        String s = getStringValue(o);

        if (s == null) {
            return null;
        }

        if (!KValidator.isUrl(s)) {
            throw new BadRequestException("Invalid url: " + s);
        }

        return s;
    }




    public String[] splitKey(String key) {
        String prefix = "";

        String[] symbols = {
                // Greater than or equal
                ">=",

                // Less than or equal
                "<=",

                // Greater than
                ">",

                // Less than
                "<",

                // Not equal to
                "!",

                // Like
                "~",

                // Not like
                "!~",

                // Between
                "%",

                // Not Between
                "!%",

                // In List
                "|",

                // Not In List
                "!|"
        };

        for (String symbol : symbols) {
            if (key.startsWith(symbol)) {
                prefix = symbol;
                key = key.substring(symbol.length());
                break;
            }
        }


        String[] result = {prefix, key};

        return result;
    }



    public Object copyBean(Object source, Object target, boolean skipNullValues) {
        try {
            KClassUtil.copy(source, target, skipNullValues);
            return target;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new SystemException("Error occurred processing request", e);
        }
    }



    public void copyMap(Map<String, Object> source, Map<String, Object> target, boolean skipNullValues) {

        for (String key : source.keySet()) {
            Object value = source.get(key);

            if (value == null && skipNullValues) {
                continue;
            }

            target.put(key, value);
        }
    }



    /**
     * Copy the initialized properties of a model to the target object.
     * <p>
     * If a matching property is not found, it is silently ignored.
     *
     * @param model  Source model object
     * @param target Target object
     */
    public <MODEL extends KJsonModel> void copyModelToObject(MODEL model, Object target) {

        // TODO: target object not likely to have all the keys of the model.  For example the 'user' property
        // in the model may refer to a userId field in the object.
        try {
            for (String key : model.initializedKeys()) {
                Object value = model.get(key);

                KClassUtil.setProperty(target, key, value);
            }
        } catch (IllegalAccessException | InvocationTargetException | SecurityException e) {
            throw new SystemException("Error occurred processing request", e);
        }
    }

    public List<Map<String,Object>> camelCaseKeys(List<Map<String,Object>> mapList) {
        if (mapList == null) return null;

        List<Map<String,Object>> result = new ArrayList<>();

        for (Map<String, Object> map : mapList) {
            result.add(camelCaseKeys(map));
        }

        return result;
    }

    public List<Map<String,Object>> snakeCaseKeys(List<Map<String,Object>> mapList) {
        if (mapList == null) return null;

        List<Map<String,Object>> result = new ArrayList<>();

        for (Map<String, Object> map : mapList) {
            result.add(snakeCaseKeys(map));
        }

        return result;
    }


    public Map<String, Object> camelCaseKeys(Map<String, Object> map) {

        if (map == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();

        for (String key : map.keySet()) {
            String camelCaseKey = KInflector.getInstance().camelCase(key.trim().toLowerCase(), false);

            Object value = map.get(key);

            if (value instanceof Map) {
                value = camelCaseKeys((Map)value);
            }

            result.put(camelCaseKey, value);
        }

        return result;
    }



    public Map<String, Object> snakeCaseKeys(Map<String, Object> map) {

        if (map == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<String, Object>();

        for (String key : map.keySet()) {
            String snakeCaseKey = KInflector.getInstance().underscore(key.trim());
            result.put(snakeCaseKey, map.get(key));
        }

        return result;
    }


    public EmailFooter getEmailFooter() {
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

        EmailFooter footer = new EmailFooter();

        footer.setType(EmailFooter.Type.TRANSACTIONAL);

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




    public String toGender(String gender) {
        if (gender == null) return null;

        if (gender.toLowerCase().startsWith("m")) {
            return "male";
        }

        if (gender.toLowerCase().startsWith("f")) {
            return "female";
        }

        throw new IllegalArgumentException("Invalid gender: " + gender);
    }



    public String uuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }



    public String year() {
        Integer year = KDateUtil.getYear(new Date());
        return year.toString();
    }



    public String toString(Object obj) {
        if (obj == null) {
            return "[null]";
        }
        return KClassUtil.toString(obj);
    }



    public String toHtml(String s) {
        if (s == null) {
            return "";
        }
        return KStringUtil.toHtml(s);
    }



    public String emailFirstName(EmailAddress email) {
        if (email == null) return "";
        if (email.getFirstName() != null) {
            return email.getFirstName();
        }

        return "";
    }



    public String formatFirstName(User user) {
        if (user == null) return "";

        if (user.getFirstName() != null) {
            return user.getFirstName();
        }

        if (user.getUsername() != null) {
            return user.getUsername();
        }

        if (user.getEmail() != null) {
            return user.getEmail();
        }

        return "";
    }



    public String createLink(String baseUrl, String url) {
        if (baseUrl == null) baseUrl = "";
        if (url == null) url = "/";

        if (baseUrl.endsWith("/")) {
            baseUrl = KStringUtil.chop(baseUrl);
        }

        if (!url.startsWith("/")) {
            url = "/" + url;
        }

        String link = baseUrl + url;

        logger.debug("LINK: " + link);

        return link;
    }



    public String formatDate(Date date) {
        String f = "MM/dd/yyyy";
        return formatDate(date, f);
    }



    public String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }



    public String formatDate(Date date, String format, String timeZone) {
        if (date == null) return "";

        Locale locale = Locale.getDefault();

        if (timeZone == null) {
            timeZone = "America/New_York";
        }

        TimeZone tz = TimeZone.getTimeZone(timeZone);
        return KDateUtil.format(date, format, locale, tz);
    }



    public String formatCurrency(BigDecimal bd) {
        //Locale locale = new Locale("en_US");
        return formatCurrency(bd, Locale.US);
    }



    public String formatCurrency(BigDecimal bd, Locale locale) {
        if (bd == null)
            return (null);

        double d = bd.doubleValue();
        NumberFormat form = NumberFormat.getCurrencyInstance(locale);
        return (form.format(d));
    }



    public void sleep(Long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { }
    }



    public List<byte[]> splitArray(byte[] source, int chunkSize) {
        List<byte[]> result = new ArrayList<byte[]>();

        int start = 0;
        while (start < source.length) {
            int end = Math.min(source.length, start + chunkSize);
            result.add(Arrays.copyOfRange(source, start, end));
            start += chunkSize;
        }

        return result;
    }

    public File toFile(byte[] data, String contentType, String filename) {
        return toFile(null, data, contentType, filename);
    }

    public File toFile(
            User user,
            byte[] data,
            String contentType,
            String filename
    ) {
        String srcFilename = filename;

        String srcHostname = "127.0.0.1";

        boolean tempFile = false;

        return toFile(user, data, contentType, filename, srcFilename, srcHostname, tempFile);
    }



    public File toFile(
            User user,
            byte[] data,
            String contentType,
            String filename,
            String srcFilename,
            String srcHostname,
            boolean tempFile) {
        File.Type type = File.getTypeByContentType(contentType);

        if (type == null) {
            type = File.Type.OTHER;
        }

        if (user == null) {
            user = system.getSystemUser();
        }

        File file = new File();

        file.setType(type);
        file.setAccess(File.Access.PUBLIC);
        file.setUserId(user.getId());
        file.setAccountId(user.getAccountId());
        file.setTokenId(null);
        file.setSrcFilename(srcFilename);
        file.setSrcHostname(srcHostname);
        file.setName(filename);
        file.setContentType(contentType);
        file.setSize(new Long(data.length));
        file.setEnabled(true);
        file.setHidden(false);
        file.setTempFile(tempFile);
        file.setCreatedDate(new Date());

        logger.debug("KUtil.toFile: file: " + KJsonUtil.toJson(file));

        file.setData(data);

        return file;
    }


    public File saveUrlToFile(User user, String url) throws IOException {
        URL u = new URL(url);

        URLConnection uc = u.openConnection();

        byte[] data = KFileUtil.toByteArray(uc.getInputStream());

        return toFile(user, data, uc.getContentType(), u.getPath());
    }
}
