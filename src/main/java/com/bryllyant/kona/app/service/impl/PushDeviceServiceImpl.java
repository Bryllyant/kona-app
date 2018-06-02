/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushDeviceMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.PushDeviceExample;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PushException;
import com.bryllyant.kona.app.service.PushDeviceService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(PushDeviceService.SERVICE_PATH)
public class PushDeviceServiceImpl
        extends KAbstractService<PushDevice, PushDeviceExample, PushDeviceMapper>
        implements PushDeviceService {

    private static Logger logger = LoggerFactory.getLogger(PushDeviceServiceImpl.class);

    @Autowired
    private PushDeviceMapper mapper;

    @Autowired
    private PushProviderService pushProviderService;


    @Autowired
    private UserDeviceService userDeviceService;

    @Override @SuppressWarnings("unchecked")
    protected PushDeviceMapper getMapper() {
        return mapper;
    }


    @Override
    protected PushDeviceExample getEntityExampleObject() { return new PushDeviceExample(); }
    
    @Override @Transactional
    public PushDevice add(PushDevice pushDevice) {
        return this.save(pushDevice);
    }

    @Override @Transactional
    public PushDevice update(PushDevice pushDevice) {
        return this.save(pushDevice);
    }

    @Override @Transactional
    public void remove(PushDevice pushDevice) {
        String endpoint = pushDevice.getEndpoint();

        super.remove(pushDevice);

        if (endpoint != null) {
            pushProviderService.deleteDeviceEndpoint(endpoint);
        }
    }

    @Override
    public void validate(PushDevice pushDevice) {
        if (pushDevice.getCreatedDate() == null) {
            pushDevice.setCreatedDate(new Date());
        }

        if (pushDevice.getUid() == null) {
            pushDevice.setUid(uuid());
        }

        pushDevice.setUpdatedDate(new Date());


        if (pushDevice.getEndpoint() == null
                && pushDevice.getProviderId() != null
                && pushDevice.getUserId() != null
                && pushDevice.getDeviceId() != null
                && pushDevice.getPlatform() != null
                && pushDevice.getToken() != null) {
            String endpoint = createEndpoint(pushDevice, true);
            pushDevice.setEndpoint(endpoint);
        }
    }


    protected List<PushDevice> fetchAllByUserIds(List<Long> userIdList, boolean sandbox) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("|userId", userIdList)
                .and("sandbox", sandbox)
                .build();

        return fetchByCriteria(filter);
    }



    @Override
    public List<PushDevice> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<PushDevice> fetchByDeviceId(Long deviceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("deviceId", deviceId);
        return fetchByCriteria(filter);
    }


    @Override
    public PushDevice fetchByUserIdAndDeviceId(Long userId, Long deviceId, boolean sandbox) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("deviceId", deviceId);
        filter.put("sandbox", sandbox);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public PushDevice fetchByToken(String token) {
        // token is a nullable column.
        // without this check, it would look for push_device where push_token is null
        if (token == null) return null;
        Map<String,Object> filter = KMyBatisUtil.createFilter("token", token);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public PushDevice fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }



    @Override @Transactional
    public PushDevice save(
            Long userId,
            Long deviceId,
            String token,
            Push.Platform platform,
            boolean sandbox
    ) {

        PushProvider provider = pushProviderService.fetchByPlatformAndSandbox(platform, sandbox);

        PushDevice pushDevice = new PushDevice();
        pushDevice.setUserId(userId);
        pushDevice.setDeviceId(deviceId);
        pushDevice.setProviderId(provider.getId());
        pushDevice.setToken(token);
        pushDevice.setPlatform(platform);
        pushDevice.setSandbox(sandbox);
        pushDevice.setEnabled(true);

        return save(pushDevice);
    }


    /**
     * Device object is not intended to be a saved entity but just a collection of properties.
     * osName property must be set.  All others are optional.
     */
    @Override @Transactional
    public PushDevice save(User user, Device device, String token, boolean sandbox) {
        Long deviceId = null;

        token = token.trim();

        String osName = device.getOsName();

        osName = osName.trim().toLowerCase();

        String advertiserId = device.getAdvertiserId();

        if (advertiserId != null) {
            advertiserId = advertiserId.trim().toUpperCase();
        }

        String deviceUuid = device.getDeviceUuid();

        if (deviceUuid != null) {
            deviceUuid = deviceUuid.trim().toUpperCase();
        }

        Push.Platform platform = pushProviderService.getPlatform(osName, false);

        if (platform == null) {
            throw new PushException("Invalid OS: " + osName);
        }

        device.setAdvertiserId(advertiserId);
        device.setDeviceUuid(deviceUuid);
        device.setOsName(osName);
        device.setEnabled(true);

        UserDevice userDevice = null;

        PushDevice pushDevice = null;

        List<UserDevice> userDeviceList = userDeviceService.fetchByUserId(user.getId());

        if (userDeviceList == null || userDeviceList.size() == 0) {
            userDevice = userDeviceService.create(user, device, null);
        } else if (advertiserId != null) {
            for (UserDevice ud : userDeviceList) {
                if (ud.getAdvertiserId() != null && ud.getAdvertiserId().equalsIgnoreCase(advertiserId)) {
                    userDevice = ud;
                    break;
                }
            }
        } else if (deviceUuid != null) {
            for (UserDevice ud : userDeviceList) {
                if (ud.getDeviceUuid() != null && ud.getAdvertiserId().equalsIgnoreCase(deviceUuid)) {
                    userDevice = ud;
                    break;
                }
            }
        } else {
            // we have at least one device and no advertiserId or deviceUuid
            // check if we have a matching platform, if so just grab the first match.
            for (UserDevice ud : userDeviceList) {
                if (ud.getOsName() != null && ud.getOsName().equalsIgnoreCase(osName)) {
                    userDevice = ud;

                    // check if this device is already assigned to this token
                    pushDevice = fetchByUserIdAndDeviceId(user.getId(), ud.getDeviceId(), sandbox);

                    if (pushDevice != null
                            && pushDevice.getToken() != null
                            && pushDevice.getToken().equalsIgnoreCase(token)) {
                        break;
                    }

                    pushDevice = null;
                }
            }
        }

        // if don't have a device at this point, create a new one for this user and platform
        if (userDevice == null) {

            userDevice = userDeviceService.create(user, device, null);
        }

        deviceId = userDevice.getDeviceId();


        if (pushDevice != null) {
            pushDevice = fetchByUserIdAndDeviceId(user.getId(), deviceId, sandbox);
        }

        // sanity check - see if this push token is tied to another
        PushDevice otherPushDevice = fetchByToken(token);

        if (otherPushDevice != null && (pushDevice == null || !pushDevice.getId().equals(otherPushDevice.getId()))) {
            logger.info("Push token already assigned to different device:\n other device: ${otherPushDevice}\n current push device: ${pushDevice}");
            throw new PushException("Push token already assigned to a different device");
        }

        if (pushDevice == null) {
            PushProvider provider =
                    pushProviderService
                            .fetchByPlatformAndSandbox(platform, sandbox);

            pushDevice = new PushDevice();
            pushDevice.setUserId(user.getId());
            pushDevice.setDeviceId(deviceId);
            pushDevice.setProviderId(provider.getId());
        }

        pushDevice.setToken(token);
        pushDevice.setSandbox(sandbox);

        return save(pushDevice);
    }


    @Override @Transactional
    public PushDevice save(PushDevice pushDevice) {
        Long userId = pushDevice.getUserId();
        Long deviceId = pushDevice.getDeviceId();
        String token = pushDevice.getToken();
        boolean sandbox = pushDevice.isSandbox();

        String oldToken = null;

        PushDevice current = fetchByUserIdAndDeviceId(userId, deviceId, sandbox);

        if (current != null) {
            if (pushDevice.getId() == null) {
                pushDevice.setId(current.getId());
            } else {
                if (!current.getId().equals(pushDevice.getId())) {
                    throw new PushException("Existing PushDevice exists: ${pushDevice}");
                }
            }

            oldToken = current.getToken();

            if (oldToken != null && token != null && (token == null || oldToken.equals(token))) {
                pushDevice.setToken(oldToken);

                if (pushDevice.getEndpoint() == null) {
                    pushDevice.setEndpoint(current.getEndpoint());
                } else {
                    if (!pushDevice.getEndpoint().equalsIgnoreCase(current.getEndpoint())) {
                        throw new PushException("Push endpoint mismatch:\ndevice: ${pushDevice}\ncurrent: ${current}");
                    }
                }

                if (pushDevice.getProviderId() == null) {
                    pushDevice.setProviderId(current.getProviderId());
                } else {
                    if (!pushDevice.getProviderId().equals(current.getProviderId())) {
                        throw new PushException("Push provider mismatch:\ndevice: ${pushDevice}\ncurrent: ${current}");
                    }
                }
            }
        }

        // sanity check
        // see if we already have an endpoint for this token
        if (token != null) {
            PushDevice device = fetchByToken(token);

            // if token exists and doesn't already belong to us then throw an exception
            if (device != null && (pushDevice.getId() == null || !pushDevice.getId().equals(device.getId()))) {
                throw new PushException("Token already assigned to another device: " + device);
            }
        }

        if (pushDevice.getId() == null) {
            pushDevice.setEnabled(true);
            pushDevice = super.add(pushDevice);
        } else {
            pushDevice = super.update(pushDevice);
        }

        return pushDevice;
    }


    @Override @Transactional
    public String createEndpoint(PushDevice device, boolean deleteIfExists) {
        String token = device.getToken();

        if (token == null) {
            throw new PushException("AppId and Token must be set");
        }

        PushProvider provider = pushProviderService.fetchById(device.getProviderId());

        String pushEndpoint = provider.getEndpoint();

        if (pushEndpoint == null) {
            throw new PushException("PushProvider is null");
        }

        // if we have an existing endpoint, delete it first
        String endpoint = device.getEndpoint();

        if (endpoint != null && deleteIfExists) {
            pushProviderService.deleteDeviceEndpoint(endpoint);
        }

        endpoint = pushProviderService.createDeviceEndpoint(device.getPlatform(), pushEndpoint, token, null);

        if (endpoint == null) {
            throw new PushException("Unable to create endpoint for device: " + device);
        }

        return endpoint;
    }


    @Override
    public List<PushDevice> fetchByUserIds(List<Long> userIdList, boolean sandbox) {
        List<PushDevice> allDevices = fetchAllByUserIds(userIdList, sandbox);

        Map<Long, PushDevice> map = new HashMap<>();

        for (PushDevice pushDevice : allDevices) {
            if (!pushDevice.isEnabled()) {
                continue;
            }

            if (!map.containsKey(pushDevice.getDeviceId())) {
                map.put(pushDevice.getDeviceId(), pushDevice);
            } else {
                PushDevice nextDevice = map.get(pushDevice.getDeviceId());
                nextDevice = chooseDevice(pushDevice, nextDevice);
                map.put(pushDevice.getDeviceId(), nextDevice);
            }
        }

        return new ArrayList<PushDevice>(map.values());
    }
//    @Override
//    public List<PushDevice> fetchByUserIds(List<Long> userIdList, boolean sandbox) {
//        List<PushDevice> all = fetchAllByUserIds(userIdList, sandbox);
//
//        List<PushDevice> pushDevices = new ArrayList<>(all.size());
//        Map<Long,PushDevice> deviceMap = new HashMap<>();
//        for (PushDevice pushDevice : all) {
//            if (!deviceMap.containsKey(pushDevice.getDeviceId())) {
//                deviceMap.put(pushDevice.getDeviceId(), pushDevice);
//            }
//        }
//    }

    private PushDevice chooseDevice(PushDevice prevDevice, PushDevice nextDevice) {
        // choose the last one created
        if (prevDevice.getCreatedDate().getTime() > nextDevice.getCreatedDate().getTime()) {
            return prevDevice;
        } else {
            return nextDevice;
        }
    }
}
