package com.bryllyant.kona.config;

import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class KConfig {
    private static Logger logger = LoggerFactory.getLogger(KConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private SystemService system;

    private Map<String,Object> getConfig() {

        Long appId = system.getSystemApp().getId();

        String _env = System.getProperty("env", "dev");

        AppConfig.Env  env = AppConfig.Env.valueOf(_env.toUpperCase());

        Map<String,Object> config = appConfigService.getConfig(appId, env);

        if (config == null) {
            throw new IllegalStateException("Configuration not found for appId: " + appId);
        }

        //return new MapConfiguration(config);
        return config;
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        if (key == null) {
            throw new KConfigException("Key is null");
        }

        key = key.trim();

        String value = env.getProperty(key);

        if (value == null) {
            // FIXME: string values in KConfig are not interpolated
            Object obj = getConfig().get(key);
            if (obj != null) {
                if (obj instanceof List || obj instanceof Map) {
                    value = KJsonUtil.toJson(obj);
                } else {
                    value = obj.toString();
                }
            }
        }

        if (value == null || value.length() == 0) {
            value = defaultValue;
        }

        if (value != null) {
            value = value.trim();
        }

        return value;
    }

    public User.Gender getGender(String key) {
        return getGender(key, null);
    }

    public User.Gender getGender(String key, String defaultValue) {
        String value = getString(key, defaultValue);

        if (value == null) return null;

        return User.Gender.valueOf(value.toUpperCase());
    }

    public Double getDouble(String key) {
        return getDouble(key, null);

    }

    public Double getDouble(String key, Double defaultValue) {
        String s = getString(key);

        if (s == null) {
            return defaultValue;
        }

        return Double.valueOf(s);
    }

    public Integer getInteger(String key) {
        return getInteger(key, null);

    }

    public Integer getInteger(String key, Integer defaultValue) {
        String s = getString(key);

        if (s == null) {
            return defaultValue;
        }

        return Integer.valueOf(s);
    }

    public Long getLong(String key) {
        return getLong(key, null);

    }

    public Long getLong(String key, Long defaultValue) {
        String s = getString(key);

        if (s == null) {
            return defaultValue;
        }

        return Long.valueOf(s);
    }

    public Boolean getBoolean(String key) {
        return getBoolean(key, null);

    }


    public Boolean getBoolean(String key, Boolean defaultValue) {
        String s = getString(key);

        if (s == null) {
            return defaultValue;
        }

        return Boolean.valueOf(s);
    }


    public Date getDate(String key) {
        return getDate(key, null);
    }

    public Date getDate(String key, Date defaultValue) {
        logger.debug("getDate called for key: " + key);

        String s = getString(key);

        if (s == null) {
            return defaultValue;
        }


        try {
            Date d = KDateUtil.parseJson(s);

            logger.debug("getDateValue called: "
                    + "\n\tString value: " + s
                    + "\n\tDate value: " + d);

            return d;
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KConfigException("Invalid date: " + s);
        }
    }

    public String getPhoneNumber(String key) {
        return getPhoneNumber(key, null);
    }


    public String getPhoneNumber(String key, String defaultValue) {
        String s = getString(key, defaultValue);

        if (s == null) {
            return null;
        }

        // Remove whitespace from the number
        s = s.replaceAll("\\s+", "");

        if (!system.isTestPhoneNumber(s) && !KValidator.isE164PhoneNumber(s)) {
            throw new KConfigException("Invalid mobile number [" + s + "]. Phone numbers must be in E.164 format.");
        }

        return s;
    }

    public String getEmailValue(String key) {
        return getEmail(key, null);
    }

    public String getEmail(String key, String defaultValue) {
        String s = getString(key, defaultValue);

        if (s == null) {
            return null;
        }

        if (!KValidator.isEmail(s)) {
            throw new KConfigException("Invalid email address: " + s);
        }

        return s;
    }


    public String getUrl(String key) {
        return getUrl(key, null);

    }

    public String getUrl(String key, String defaultValue) {
        String s = getString(key, defaultValue);

        if (s == null) {
            return null;
        }

        if (!KValidator.isUrl(s)) {
            throw new KConfigException("Invalid url: " + s);
        }

        return s;
    }

    public List<String> getList(String key) {
        return getList(key, ",", String.class);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        return getList(key, ",", clazz);
    }


    public <T> List<T> getList(String key, String delimiter, Class<T> clazz) {
        List<T> list = new ArrayList<>();

        String value = getString(key);

        if (value == null) {
            return list;
        }

        String[] parts = value.split(delimiter);

        for (String part : parts) {
            T partValue = KClassUtil.valueOf(clazz, part);
            list.add(partValue);
        }


        return list;
    }
}
