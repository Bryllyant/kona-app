/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.app.util;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.model.KEmailFooter;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KInflector;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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



    public Map<String, Object> camelCaseKeys(Map<String, Object> map) {

        if (map == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<String, Object>();

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


    public KEmailFooter getEmailFooter() {
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
