package com.bryllyant.kona.app.util;

import com.bryllyant.kona.app.entity.KBaseFile;
import com.bryllyant.kona.app.entity.KEmailAddress;
import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KFileUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public class KUtil {
    private static Logger logger = LoggerFactory.getLogger(KUtil.class);

    private static KUtil instance = null;



    public static KUtil getInstance() {
        if (instance == null) {
            instance = new KUtil();
        }
        return instance;
    }



    public static String toGender(String gender) {
        if (gender == null) return null;

        if (gender.toLowerCase().startsWith("m")) {
            return "male";
        }

        if (gender.toLowerCase().startsWith("f")) {
            return "female";
        }

        throw new IllegalArgumentException("Invalid gender: " + gender);
    }



    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }



    public static String year() {
        Integer year = KDateUtil.getYear(new Date());
        return year.toString();
    }



    public static String toString(Object obj) {
        if (obj == null) {
            return "[null]";
        }
        return KClassUtil.toString(obj);
    }



    public static String toHtml(String s) {
        if (s == null) {
            return "";
        }
        return KStringUtil.toHtml(s);
    }



    public static <Email extends KEmailAddress> String emailFirstName(Email email) {
        if (email == null) return "";
        if (email.getFirstName() != null) {
            return email.getFirstName();
        }

        return "";
    }



    public static <User extends KUser> String formatFirstName(User user) {
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



    public static String formatDate(Date date) {
        String f = "MM/dd/yyyy";
        return formatDate(date, f);
    }



    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }



    public static String formatDate(Date date, String format, String timeZone) {
        if (date == null) return "";

        Locale locale = Locale.getDefault();

        if (timeZone == null) {
            timeZone = "America/New_York";
        }

        TimeZone tz = TimeZone.getTimeZone(timeZone);
        return KDateUtil.format(date, format, locale, tz);
    }



    public static String formatCurrency(BigDecimal bd) {
        //Locale locale = new Locale("en_US");
        return formatCurrency(bd, Locale.US);
    }



    public static String formatCurrency(BigDecimal bd, Locale locale) {
        if (bd == null)
            return (null);

        double d = bd.doubleValue();
        NumberFormat form = NumberFormat.getCurrencyInstance(locale);
        return (form.format(d));
    }



    public static void sleep(Long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { }
    }



    public static List<byte[]> splitArray(byte[] source, int chunkSize) {
        List<byte[]> result = new ArrayList<byte[]>();

        int start = 0;
        while (start < source.length) {
            int end = Math.min(source.length, start + chunkSize);
            result.add(Arrays.copyOfRange(source, start, end));
            start += chunkSize;
        }

        return result;
    }


    
    public static <FILE extends KFile,USER extends KUser> FILE toFile(
            Class<FILE> clazz, 
            USER user, 
            byte[] data, 
            String contentType, 
            String filename
            ) throws InstantiationException, IllegalAccessException {

        String srcFilename = filename;

        String srcHostname = "127.0.0.1";

        boolean tempFile = false;

        return toFile(clazz, user, data, contentType, filename, srcFilename, srcHostname, tempFile);
    }



    public static <FILE extends KFile,USER extends KUser> FILE toFile(
            Class<FILE> clazz, 
            USER user, 
            byte[] data, 
            String contentType, 
            String filename, 
            String srcFilename, 
            String srcHostname, 
            boolean tempFile
            ) throws InstantiationException, IllegalAccessException {

        KFile.Type type = KBaseFile.getTypeByContentType(contentType);

        if (type == null) {
            type = KFile.Type.OTHER;
        }

        FILE file = clazz.newInstance();

        file.setType(type);
        file.setAccess(KFile.Access.PUBLIC);
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



    public static <FILE extends KFile,USER extends KUser> FILE saveUrlToFile(
            Class<FILE> clazz, 
            USER user, 
            String url) throws IOException, InstantiationException, IllegalAccessException {

        URL u = new URL(url);

        URLConnection uc = u.openConnection();

        byte[] data = KFileUtil.toByteArray(uc.getInputStream());

        return toFile(clazz, user, data, uc.getContentType(), u.getPath());
    }
}
