/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailAddressMapper;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailAddressExample;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KResultList;
import com.bryllyant.kona.util.MailboxValidator;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service(EmailAddressService.SERVICE_PATH)
public class EmailAddressServiceImpl
        extends KAbstractService<EmailAddress, EmailAddressExample, EmailAddressMapper>
        implements EmailAddressService {

    private static Logger logger = LoggerFactory.getLogger(EmailAddressServiceImpl.class);

    @Autowired
    private EmailAddressMapper emailAddressMapper;

    @Autowired
    private EmailGroupService emailGroupService;

    @Autowired
    private EmailGroupAddressService emailGroupAddressService;

    @Autowired
    private AppUtil util;

    private Cache<String, Optional<List<Map<String,Object>>>> cache = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    private boolean stopScrubThread = false;

    @Override
    protected EmailAddressMapper getMapper() {
        return emailAddressMapper;
    }

    protected List<EmailAddress> daoFetchRandom(
            Long count,
            List<String> includeSourceList,
            List<String> excludeSourceList,
            List<Long> includeGroupIdList,
            List<Long> excludeGroupIdList
    ) {
        return emailAddressMapper.fetchRandom(
                count,
                includeSourceList,
                excludeSourceList,
                includeGroupIdList,
                excludeGroupIdList
        );
    }


    @Override
    public void validate(EmailAddress emailAddress) {
        if (emailAddress.getCreatedDate() == null) {
            emailAddress.setCreatedDate(new Date());
        }

        emailAddress.setUpdatedDate(new Date());

        if (emailAddress.getUid() == null) {
            emailAddress.setUid(uuid());
        }
    }



    @Override
    public EmailAddress fetchByEmail(String email) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("email", email);
        List<EmailAddress> result = fetchByCriteria(filter);
        return KMyBatisUtil.fetchOne(result);
    }


    @Override
//    [
//        {
//            name:
//            count:
//        }
//    ]
    public List<Map<String,Object>> fetchSources(boolean nocache) {
        Optional<List<Map<String,Object>>> result = cache.getIfPresent("sources");

        if (result == null || nocache) {
            List<Map<String,Object>> sources = emailAddressMapper.fetchSources();

            if (sources == null) {
                sources = new ArrayList<>();
            }

            sources = util.camelCaseKeys(sources);

            result = Optional.of(sources);

            cache.put("sources", result);
        }

        return result.get();
    }


    @Override
    public List<EmailAddress> fetchAll(Boolean scrubbed, Boolean enabled) {
        Map<String, Object> filter = new HashMap<>();

        if (enabled != null) {
            filter.put("enabled", enabled);
        }

        if (scrubbed != null) {
            if (scrubbed) {
                filter.put("!scrubbedDate", null);
            } else {
                filter.put("scrubbedDate", null);
            }
        }

        return fetchByCriteria(filter);
    }



    @Override
    public List<Long> fetchAllIds(Boolean scrubbed, Boolean enabled) {
        List<EmailAddress> result = fetchAll(scrubbed, enabled);

        List<Long> list = new ArrayList<>();

        for (EmailAddress address : result) {
            list.add(address.getId());
        }

        return list;
    }

    @Override
    public EmailAddress create(EmailAddress address) {
        if (address.getEmail() == null) {
            throw new KServiceException("Invalid address: email not set.");
        }

        EmailAddress current = fetchByEmail(address.getEmail());

        if (current != null) {
            // already have a record for this email.  merge any non-null fields as we assume this an update
            if (address.getFirstName() != null) {
                current.setFirstName(address.getFirstName());
            }

            if (address.getLastName() != null) {
                current.setLastName(address.getLastName());
            }

            if (address.getAdvertiserId() != null) {
                current.setAdvertiserId(address.getAdvertiserId());
            }

            if (address.getBirthDate() != null) {
                current.setBirthDate(address.getBirthDate());
            }

            if (address.getCity() != null) {
                current.setCity(address.getCity());
            }

            if (address.getState() != null) {
                current.setState(address.getState());
            }

            if (address.getCountry() != null) {
                current.setCountry(address.getCountry());
            }

            if (address.getStreet1() != null) {
                current.setStreet1(address.getStreet1());
            }

            if (address.getStreet2() != null) {
                current.setStreet2(address.getStreet2());
            }

            if (address.getPostalCode() != null) {
                current.setPostalCode(address.getPostalCode());
            }

            if (address.getCompany() != null) {
                current.setCompany(address.getCompany());
            }

            if (address.getExtra() != null) {
                current.setExtra(address.getExtra());
            }

            if (address.getGender() != null) {
                current.setGender(address.getGender());
            }

            if (address.getMobileNumber() != null) {
                current.setMobileNumber(address.getMobileNumber());
            }

            if (address.getPlatform() != null) {
                current.setPlatform(address.getPlatform());
            }

            if (address.getSource() != null) {
                current.setSource(address.getSource());
            }

            if (address.getTitle() != null) {
                current.setTitle(address.getTitle());
            }

            if (address.getUserId() != null) {
                current.setUserId(address.getUserId());
            }

            return save(current);
        }

        return save(address);
    }

//    @Override
//    public void scrub(String source, Long startId, Long endId, Date startDate, Date endDate, boolean force, boolean tryConnectMX) {
//        Map<String, Object> filter = KMyBatisUtil.createFilter("scrubbedDate", null);
//
//        if (source != null) {
//            filter.put("source", source);
//        }
//
//        if (startId != null) {
//            filter.put(">=id", startId);
//        }
//
//        if (endId != null) {
//            filter.put("<id", endId);
//        }
//
//        if (startDate != null) {
//            filter.put(">=createdDate", startDate);
//        }
//
//        if (endDate != null) {
//            filter.put("<createdDate", endDate);
//        }
//
//        List<EmailAddress> list = fetchByCriteria(filter);
//        if (list == null || list.size() == 0) return;
//
//
//        logger.debug("[scrub] Processing emails: " + list.size());
//
//        for (EmailAddress address : list) {
//            scrub(address, false, tryConnectMX);
//        }
//    }

    @Override
    public void scrubAll(boolean force, boolean tryConnectMX, long throttleTime) {
        scrub(null, force, tryConnectMX, throttleTime);
    }

    @Override
    public void scrub(String source, boolean force, boolean tryConnectMX, long throttleTime) {

        Map<String, Object> filter = KMyBatisUtil.createFilter();

        if (!force) {
            filter.put("scrubbedDate", null);
        }

        if (source != null) {
            filter.put("source", source);
        }

        stopScrubThread = false;

        new Thread(() -> {

            Integer offset = 0;
            Integer limit = 1000;
            String[] sortOrder = {
                    "id"
            };

            while (true) {
                KResultList<EmailAddress> result = fetchByCriteria(
                    offset,
                    limit,
                    sortOrder,
                    filter,
                    false
                );

                if (result == null || result.size() == 0) {
                    return;
                }

                for (EmailAddress address : result) {

                    scrub(address, force, tryConnectMX);

                    if (stopScrubThread) {
                        return;
                    }

                    if (throttleTime > 0) {
                        util.sleep(throttleTime);
                    }
                }

                if (result.getCurrentPage() >= result.getTotalPages()) {
                    return;
                }

                offset += limit;
            }
        }).start();
    }

    @Override
    public boolean scrub(String email, boolean force, boolean tryConnectMX) {
        EmailAddress address = new EmailAddress();
        address.setEmail(email);
        address = create(address);
        return scrub(address, force, tryConnectMX);
    }

    @Override
    public boolean scrub(EmailAddress address, boolean force, boolean tryConnectMX) {
        logger.debug("[scrub]: force: {}  tryConnectMX: {}", force, tryConnectMX);

        if (address.getScrubbedDate() != null && !force) {
            logger.debug("[scrub] force is false: returning isValid() for address: " + address);
            return isValid(address, false);
        }

        // do basic cleanup
        String value = address.getEmail();

        address.setEmail(value.toLowerCase());

        value = address.getFirstName();

        if (value != null) {
            value = scrubTitles(value);
            String[] parts = value.split("\\s+");
            value = parts[0].trim();
            value = KInflector.getInstance().capitalize(value);
            address.setFirstName(value);
        }

        value = address.getLastName();

        if (value != null) {
            value = scrubTitles(value);
            String[] parts = value.split("\\s+");
            value = parts[parts.length - 1].trim();
            value = KInflector.getInstance().capitalize(value);
            address.setLastName(value);
        }

        // MailboxValidator validator = new MailboxValidator("validator@gmail.com");

        String s = "[scrub] Processing id: " + address.getId() + "  email: " + address.getEmail() + " ... ";

        if (MailboxValidator.mailboxExists(address.getEmail(), tryConnectMX)) {
            address.setScrubbedDate(new Date());
            address.setEnabled(true);
            update(address);

            s += "valid";
            logger.debug(s);
            return true;
        } else {
            address.setScrubbedDate(new Date());
            address.setEnabled(false);
            update(address);

            s += "invalid. Disabled";
            logger.debug(s);
            return false;
        }


//        // address is not valid. if it's referenced, disable it
//        List<EmailGroupAddress> groups = emailGroupAddressService.fetchByAddressId(address.getId());
//
//        if (groups.size() > 0) {
//            address.setScrubbedDate(new Date());
//            address.setEnabled(false);
//            update(address);
//
//            s += "invalid. Disabled";
//            logger.debug(s);
//            return false;
//        }
//
//        // not referenced, so delete it
//        remove(address);
//        s += "invalid. Removed";

//        logger.debug(s);
//
//        return false;
    }



    @Override
    public List<EmailAddress> fetchRandom(
            Long count,
            List<String> includeSourceList,
            List<String> excludeSourceList,
            List<String> includeGroupSlugList,
            List<String> excludeGroupSlugList
    ) {
        List<Long> includeGroupIdList = null;

        if (includeGroupSlugList != null && includeGroupSlugList.size() > 0) {
            includeGroupIdList = new ArrayList<>();

            for (String slug : includeGroupSlugList) {
                EmailGroup group = emailGroupService.fetchBySlug(slug);

                if (group == null) {
                    logger.warn("Invalid group slug: " + slug);
                    continue;
                }

                includeGroupIdList.add(group.getId());
            }
        }

        List<Long> excludeGroupIdList = null;

        if (excludeGroupSlugList != null && excludeGroupSlugList.size() > 0) {
            excludeGroupIdList = new ArrayList<>();

            for (String slug : excludeGroupSlugList) {
                EmailGroup group = emailGroupService.fetchBySlug(slug);

                if (group == null) {
                    logger.warn("Invalid group slug: " + slug);
                    continue;
                }

                excludeGroupIdList.add(group.getId());
            }
        }

        return daoFetchRandom(count, includeSourceList, excludeSourceList, includeGroupIdList, excludeGroupIdList);
    }


    @Override
    public boolean isValid(EmailAddress address, boolean forceScrub) {
        logger.debug("[isValid] address: {}  forceScrub: {}", address, forceScrub);

        if (address.getScrubbedDate() == null || forceScrub) {
            boolean valid = scrub(address, forceScrub, false);
            if (!valid) return false;
        }

        if (!address.isEnabled()) return false;

        if (address.getOptedOutDate() != null) return false;

        if (address.getBouncedDate() != null) return false;

        if (address.getComplainedDate() != null) return false;

        return true;
    }



    // NOTE recordMap hold either a record index or a literal value. if the map value is an Integer
    // it's assumed to be a record index.  if it's a string, then it's a literal value.
    @Override
    public void importCSV(String csvFile, String errorFile, Map<String, Object> recordMap,
                          String source, boolean scrubbed, boolean skipFirstRecord) throws IOException {
        // TODO Auto-generated method stub
        KInflector inflector = KInflector.getInstance();

        CSVReader reader = new CSVReader(new FileReader(csvFile));

        List<String[]> records = reader.readAll();

        int startIndex = 0;

        if (skipFirstRecord) {
            startIndex = 1;
        }

        List<String[]> errors = new ArrayList<>();

        //for (String[] record : records) {
        for (int i = startIndex; i < records.size() - 1; i++) {
            String[] record = records.get(i);

            EmailAddress email = new EmailAddress();
            email.setSource(source);
            email.setEnabled(true);
            email.setConfirmedDate(null);
            email.setCreatedDate(new Date());

            if (scrubbed) {
                email.setScrubbedDate(new Date());
            }

            for (String key : recordMap.keySet()) {

                Object obj = recordMap.get(key);

                if (obj == null) {
                    continue;
                }

                Integer index = null;
                String value = null;
                if (obj instanceof Integer) {
                    index = Integer.valueOf(obj.toString());
                    value = record[index];
                } else {
                    value = obj.toString();
                }

                if (value == null || value.trim().length() == 0) {
                    continue;
                }

                value = value.trim();

                switch (key) {
                    case "email":
                        value = value.toLowerCase();
                        break;

                    case "firstName":
                        value = scrubTitles(value);
                        String[] parts = value.split("\\s+");
                        value = parts[0].trim();
                        value = inflector.capitalize(value);
                        break;

                    case "lastName":
                        value = scrubTitles(value);
                        parts = value.split("\\s+");
                        value = parts[parts.length - 1].trim();
                        value = inflector.capitalize(value);
                        break;

                    case "street1":
                    case "street2":
                    case "city":
                    case "country":
                        value = inflector.capitalize(value);
                        break;

                    case "state":
                        value = value.toUpperCase();
                        break;

                    // leave these alone for now
                    case "mobileNumber":
                    case "company":
                    case "title":
                    case "birth_year":
                    case "gender":
                    case "extra":
                        break;

                    // "John T. Doe"
                    case "fullName1":
                        value = scrubTitles(value);
                        parts = value.split("\\s+");
                        email.setFirstName(inflector.capitalize(parts[0].trim()));
                        email.setLastName(inflector.capitalize(parts[parts.length - 1].trim()));
                        continue;

                        // "Doe John"
                    case "fullName2":
                        value = scrubTitles(value);
                        parts = value.split("\\s+");
                        email.setFirstName(inflector.capitalize(parts[parts.length - 1].trim()));
                        email.setLastName(inflector.capitalize(parts[0].trim()));
                        continue;

                        // "Doe, John T."
                    case "fullName3":
                        value = scrubTitles(value);
                        parts = value.split(",");
                        email.setLastName(inflector.capitalize(parts[0].trim()));
                        String part = parts[parts.length - 1].trim();
                        parts = part.split("\\s+");
                        email.setFirstName(inflector.capitalize(parts[0].trim()));
                        continue;
                }


                try {
                    BeanUtils.setProperty(email, key, value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error(e.getMessage(), e);
                    continue;
                }
            }

            // check if email is valid
            if (email.getEmail() == null || !KValidator.isEmail(email.getEmail())) {
                errors.add(record);
                continue;
            }

            try {
                email = add(email);
                /*
				EmailAddress e = fetchByEmail(email.getEmail());
				if (e == null) {
					email = add(email);
				} else {
					e.setFirstName(email.getFirstName());
					e.setLastName(email.getLastName());
					e = update(e);
				}
				*/
                logger.debug("importCSV: added address:\n" + email);
            } catch (Exception e) {
                logger.debug("importCSV: error: " + e.getMessage());
                errors.add(record);
            }
        }

        logger.debug("importCSV: error count: " + errors.size());

        if (errorFile != null) {
            FileWriter fw = new FileWriter(errorFile);
            CSVWriter writer = new CSVWriter(fw);
            writer.writeAll(errors);
            writer.flush();
            writer.close();
        }
    }



    private String scrubTitles(String name) {
        logger.debug("scrubTitles: raw name: " + name);
        String[] prefixes = {
                "Dr ", "Dr. ", "Mr ", "Mr. ", "Ms ", "Ms. ", "Mrs ", "Mrs. "
        };

        String[] suffixes = {
                " Jr", " Jr.", " Sr", " Sr.", " II", " III", " IV", " MD", " M.D.", " MD.", " Ph.D.", " PhD", " PhD.", " Esq", " Esq."
        };

        for (String prefix : prefixes) {
            if (name.toLowerCase().startsWith(prefix.toLowerCase())) {
                name = name.replaceFirst(prefix, "");
                name = name.replaceFirst(prefix.toUpperCase(), "");
                name = name.replaceFirst(prefix.toLowerCase(), "");
            }
        }

        for (String suffix : suffixes) {
            if (name.toLowerCase().endsWith(suffix.toLowerCase())) {
                name = name.replace(suffix, "");
                name = name.replace(suffix.toUpperCase(), "");
                name = name.replace(suffix.toLowerCase(), "");
            }
        }

        logger.debug("scrubTitles: scrubbed name: " + name);
        return name;
    }
}
