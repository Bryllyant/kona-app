/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.amazonaws.services.sns.model.AmazonSNSException;
import com.bryllyant.kona.app.dao.PushMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.PushExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PushException;
import com.bryllyant.kona.app.service.PushDeviceService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.PushService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.PositionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(PushService.SERVICE_PATH)
public class PushServiceImpl extends KAbstractService<Push, PushExample, PushMapper>
        implements PushService {

    private static Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    @Autowired
    private PushMapper mapper;

    @Autowired
    private AppService appService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PushDeviceService pushDeviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private PushProviderService pushProviderService;


    @Override @SuppressWarnings("unchecked")
    protected PushMapper getMapper() {
        return mapper;
    }

    
    @Override
    public void validate(Push push) {
        if (push.getCreatedDate() == null) {
            push.setCreatedDate(new Date());
        }

        if (push.getUid() == null) {
            push.setUid(uuid());
        }

        push.setUpdatedDate(new Date());
    }


    @Override
    public Push fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<Push> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<Push> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public Push fetchByProviderMessageId(String providerMessageId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("providerMessageId", providerMessageId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<Push> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public void broadcast(Push push, List<Long> userIds, Double latitude, Double longitude, Double radius) {

        if (radius != null) {
            radius = PositionUtil.toMeters(radius);
        }

        Map<String,Object> filter = new HashMap<>();
        filter.put("userIds", userIds);
        filter.put("latitude", latitude);
        filter.put("longitude", longitude);
        filter.put("radius", radius);

        broadcast(push, filter);
    }


    @Override
    // filter currently only supports: latitude, longitude, radius, userIds
    public void broadcast(
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            Map<String, Object> filter,
            boolean sandbox
    ) {

        Push push = new Push();

        push.setSandbox(sandbox);
        push.setTitle(title);
        push.setMessage(message);
        push.setImageUrl(imageUrl);
        push.setActionUrl(actionUrl);

        broadcast(push, filter);
    }


    @Override
    public void broadcast(final Push push, final Map<String, Object> filter) {

        push.setId(null); // make sure we're creating a new object

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    logger.debug("blast thread running ...");
                    blast(push, filter);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        };

        t.start();
    }


    protected Map<String, Object> createPayLoadData(Push push) {
        App app = appService.getSystemApp();

        Map<String, Object> data = new HashMap<>();

        data.put("source", app.getName());

        if (push.getTitle() != null) {
            data.put("title", push.getTitle());
        }

        if (push.getActionUrl() != null) {
            data.put("action_url", push.getActionUrl());
        }

        if (push.getImageUrl() != null) {
            data.put("image_url", push.getImageUrl());
        }

        data.put("push_uid", push.getUid());

        return data;
    }

    @Override
    public Push send (
            Long userId,
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            boolean sandbox
    ) {
        Push push = new Push();

        push.setUserId(userId);
        push.setSandbox(sandbox);
        push.setTitle(title);
        push.setMessage(message);
        push.setImageUrl(imageUrl);
        push.setActionUrl(actionUrl);

        return send(push);
    }

    // TODO: make sure push object contains required fields
    private void validateMessage(Push push) {

    }

    @Override
    public Push send(Push push) {

        validateMessage(push);

        List<Long> userIds = new ArrayList<>();

        userIds.add(push.getUserId());

        Map<String, Object> filter = new HashMap<>();

        filter.put("userIds", userIds);

        List<Push> result = blast(push, filter);

        if (result.size() != 1) {
            throw new PushException("Error sending push notification");
        }

        return result.get(0);
    }

    public Push send(PushDevice device, Push push) {
        logger.debug("send: push device: " + device);
        logger.debug("send: push object: " + push);

        Push.Platform platform = device.getPlatform();

        String endpoint = device.getEndpoint();

        if (platform == null || endpoint == null) {
            return null;
        }

        if (push.getUserId() == null) {
            push.setUserId(device.getUserId());
        }

        // sanity check
        if (!push.getUserId().equals(device.getUserId())) {
            throw new PushException("Error: Push user does not match device owner.\npush: ${push}\ndevice: ${device}");
        }


        push.setId(null); // ensure new object
        push.setPlatform(device.getPlatform());
        push.setSandbox(device.isSandbox()); // force sandbox value to device's state
        push.setDeviceId(device.getId());
        push.setSentDate(new Date());

        validateMessage(push);

        push = add(push);

        // Make sure to add the object before creating the payload since createPayLoadData will use push.getUid()
        Map<String, Object> data = createPayLoadData(push);

        try {
            String providerMessageId = pushProviderService.publish(platform, endpoint, push.getMessage(), data);

            push.setProviderMessageId(providerMessageId);

            logger.debug("\n--------------------------\n"
                    + "\npush message:"
                    + "\nplatform: " + platform
                    + "\nendpoint: " + endpoint
                    + "\nproviderMessageId: " + providerMessageId
                    + "\npush: " + push
                    + "\ndata: " + data
                    + "\n--------------------------\n"
            );
        } catch (Throwable t) {
            if (t instanceof AmazonSNSException) {
                AmazonSNSException ex = (AmazonSNSException) t;
                push.setErrorCode(ex.getErrorCode());
                push.setErrorMessage(ex.getErrorMessage());
            } else {
                push.setErrorMessage(t.getMessage());
            }
        }

        return update(push);
    }


    protected List<Push> blast(Push push, Map<String, Object> filter) {
        logger.debug("blast called for push: " + push);

        if (filter == null) {
            throw new PushException("Error blast filter not defined.");
        }

        List<PushDevice> deviceList = getDeviceList(filter, push.isSandbox());

        List<Push> deliveries = new ArrayList<>();

        for (PushDevice device : deviceList) {
            logger.debug("blast push device: " + device);

            Push delivery = send(device, push);
            deliveries.add(delivery);
        }

        return deliveries;
    }

    private List<PushDevice> getDeviceList(Map<String,Object> filter, boolean sandbox) {
        logger.debug("getDeviceList called for filter: " + KJsonUtil.toJson(filter));

        List<Long> userIdList = getUserIdList(filter);

        if (userIdList == null) {
            logger.debug("getDeviceList: getUserIdList returned null");
            return null;
        }

        List<PushDevice> result = pushDeviceService.fetchByUserIds(userIdList, sandbox);

        logger.debug("getDeviceList: fetchByUserIds: result size: " + result.size());

        return result;
    }


    @SuppressWarnings("unchecked")
    private List<Long> getUserIdList(Map<String,Object> filter) {
        logger.debug("getUserIdList called for filter: " + KJsonUtil.toJson(filter));

        List<Long> userIdList = null;
        Double latitude = null;
        Double longitude = null;
        Double radius = null; // in meters

        // NOTE: filter is serialized as Map<String,String> so explicitly convert types
        if (filter != null) {
            Object value = filter.get("latitude");
            if (value != null) {
                latitude = Double.valueOf(value.toString());
            }

            value = filter.get("longitude");
            if (value != null) {
                longitude = Double.valueOf(value.toString());
            }

            value = filter.get("radius");
            if (value != null) {
                radius = Double.valueOf(value.toString());
            }

            value = filter.get("userIds");
            if (value != null) {
                userIdList = new ArrayList<Long>();

                List<Object> list = (List<Object>) value;

                for (Object o : list) {
                    userIdList.add(Long.valueOf(o.toString()));
                }
            }
        }

        if (userIdList == null) {
            userIdList = new ArrayList<Long>();
        }

        List<User> userList = null;


        // first see if we have a geofence
        if (latitude != null && longitude != null && radius != null) {
            // see if we have users who were within the geofence within the last hour
            Date startDate = KDateUtil.addHours(new Date(), 1);

            userList = userService.fetchProximate(latitude, longitude, radius, startDate, null);

            logger.debug("getUserIdList: haveGeoFence: matched userList count: " + userList.size());
        } else {
            userList = userService.fetchAllRegistered(null);
        }

        if (userList != null) {
            for (User user : userList) {
                userIdList.add(user.getId());
            }
        }

        return userIdList;
    }

}
