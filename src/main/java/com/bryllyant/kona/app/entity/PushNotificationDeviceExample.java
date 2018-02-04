package com.bryllyant.kona.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PushNotificationDeviceExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table users
     *
     * @mbggenerated
     */
    private Integer limit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table users
     *
     * @mbggenerated
     */
    private Integer offset;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public PushNotificationDeviceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("`uid` is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("`uid` is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("`uid` =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("`uid` <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("`uid` >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("`uid` >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("`uid` <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("`uid` <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("`uid` like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("`uid` not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("`uid` in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("`uid` not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("`uid` between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("`uid` not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("`app_id` is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("`app_id` is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(Long value) {
            addCriterion("`app_id` =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(Long value) {
            addCriterion("`app_id` <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(Long value) {
            addCriterion("`app_id` >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`app_id` >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(Long value) {
            addCriterion("`app_id` <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(Long value) {
            addCriterion("`app_id` <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<Long> values) {
            addCriterion("`app_id` in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<Long> values) {
            addCriterion("`app_id` not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(Long value1, Long value2) {
            addCriterion("`app_id` between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(Long value1, Long value2) {
            addCriterion("`app_id` not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdIsNull() {
            addCriterion("`push_notification_provider_id` is null");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdIsNotNull() {
            addCriterion("`push_notification_provider_id` is not null");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdEqualTo(Long value) {
            addCriterion("`push_notification_provider_id` =", value, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdNotEqualTo(Long value) {
            addCriterion("`push_notification_provider_id` <>", value, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdGreaterThan(Long value) {
            addCriterion("`push_notification_provider_id` >", value, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`push_notification_provider_id` >=", value, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdLessThan(Long value) {
            addCriterion("`push_notification_provider_id` <", value, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdLessThanOrEqualTo(Long value) {
            addCriterion("`push_notification_provider_id` <=", value, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdIn(List<Long> values) {
            addCriterion("`push_notification_provider_id` in", values, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdNotIn(List<Long> values) {
            addCriterion("`push_notification_provider_id` not in", values, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdBetween(Long value1, Long value2) {
            addCriterion("`push_notification_provider_id` between", value1, value2, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andPushNotificationProviderIdNotBetween(Long value1, Long value2) {
            addCriterion("`push_notification_provider_id` not between", value1, value2, "pushNotificationProviderId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("`user_id` is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("`user_id` is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("`user_id` =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("`user_id` <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("`user_id` >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`user_id` >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("`user_id` <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("`user_id` <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("`user_id` in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("`user_id` not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("`user_id` between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("`user_id` not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNull() {
            addCriterion("`device_id` is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("`device_id` is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(Long value) {
            addCriterion("`device_id` =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(Long value) {
            addCriterion("`device_id` <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(Long value) {
            addCriterion("`device_id` >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`device_id` >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(Long value) {
            addCriterion("`device_id` <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(Long value) {
            addCriterion("`device_id` <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<Long> values) {
            addCriterion("`device_id` in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<Long> values) {
            addCriterion("`device_id` not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(Long value1, Long value2) {
            addCriterion("`device_id` between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(Long value1, Long value2) {
            addCriterion("`device_id` not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andPushPlatformIsNull() {
            addCriterion("`push_platform` is null");
            return (Criteria) this;
        }

        public Criteria andPushPlatformIsNotNull() {
            addCriterion("`push_platform` is not null");
            return (Criteria) this;
        }

        public Criteria andPushPlatformEqualTo(String value) {
            addCriterion("`push_platform` =", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformNotEqualTo(String value) {
            addCriterion("`push_platform` <>", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformGreaterThan(String value) {
            addCriterion("`push_platform` >", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformGreaterThanOrEqualTo(String value) {
            addCriterion("`push_platform` >=", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformLessThan(String value) {
            addCriterion("`push_platform` <", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformLessThanOrEqualTo(String value) {
            addCriterion("`push_platform` <=", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformLike(String value) {
            addCriterion("`push_platform` like", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformNotLike(String value) {
            addCriterion("`push_platform` not like", value, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformIn(List<String> values) {
            addCriterion("`push_platform` in", values, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformNotIn(List<String> values) {
            addCriterion("`push_platform` not in", values, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformBetween(String value1, String value2) {
            addCriterion("`push_platform` between", value1, value2, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushPlatformNotBetween(String value1, String value2) {
            addCriterion("`push_platform` not between", value1, value2, "pushPlatform");
            return (Criteria) this;
        }

        public Criteria andPushTokenIsNull() {
            addCriterion("`push_token` is null");
            return (Criteria) this;
        }

        public Criteria andPushTokenIsNotNull() {
            addCriterion("`push_token` is not null");
            return (Criteria) this;
        }

        public Criteria andPushTokenEqualTo(String value) {
            addCriterion("`push_token` =", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenNotEqualTo(String value) {
            addCriterion("`push_token` <>", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenGreaterThan(String value) {
            addCriterion("`push_token` >", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenGreaterThanOrEqualTo(String value) {
            addCriterion("`push_token` >=", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenLessThan(String value) {
            addCriterion("`push_token` <", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenLessThanOrEqualTo(String value) {
            addCriterion("`push_token` <=", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenLike(String value) {
            addCriterion("`push_token` like", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenNotLike(String value) {
            addCriterion("`push_token` not like", value, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenIn(List<String> values) {
            addCriterion("`push_token` in", values, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenNotIn(List<String> values) {
            addCriterion("`push_token` not in", values, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenBetween(String value1, String value2) {
            addCriterion("`push_token` between", value1, value2, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushTokenNotBetween(String value1, String value2) {
            addCriterion("`push_token` not between", value1, value2, "pushToken");
            return (Criteria) this;
        }

        public Criteria andPushEndpointIsNull() {
            addCriterion("`push_endpoint` is null");
            return (Criteria) this;
        }

        public Criteria andPushEndpointIsNotNull() {
            addCriterion("`push_endpoint` is not null");
            return (Criteria) this;
        }

        public Criteria andPushEndpointEqualTo(String value) {
            addCriterion("`push_endpoint` =", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointNotEqualTo(String value) {
            addCriterion("`push_endpoint` <>", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointGreaterThan(String value) {
            addCriterion("`push_endpoint` >", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointGreaterThanOrEqualTo(String value) {
            addCriterion("`push_endpoint` >=", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointLessThan(String value) {
            addCriterion("`push_endpoint` <", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointLessThanOrEqualTo(String value) {
            addCriterion("`push_endpoint` <=", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointLike(String value) {
            addCriterion("`push_endpoint` like", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointNotLike(String value) {
            addCriterion("`push_endpoint` not like", value, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointIn(List<String> values) {
            addCriterion("`push_endpoint` in", values, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointNotIn(List<String> values) {
            addCriterion("`push_endpoint` not in", values, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointBetween(String value1, String value2) {
            addCriterion("`push_endpoint` between", value1, value2, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andPushEndpointNotBetween(String value1, String value2) {
            addCriterion("`push_endpoint` not between", value1, value2, "pushEndpoint");
            return (Criteria) this;
        }

        public Criteria andSandboxIsNull() {
            addCriterion("`sandbox` is null");
            return (Criteria) this;
        }

        public Criteria andSandboxIsNotNull() {
            addCriterion("`sandbox` is not null");
            return (Criteria) this;
        }

        public Criteria andSandboxEqualTo(boolean value) {
            addCriterion("`sandbox` =", value, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxNotEqualTo(boolean value) {
            addCriterion("`sandbox` <>", value, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxGreaterThan(boolean value) {
            addCriterion("`sandbox` >", value, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxGreaterThanOrEqualTo(boolean value) {
            addCriterion("`sandbox` >=", value, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxLessThan(boolean value) {
            addCriterion("`sandbox` <", value, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxLessThanOrEqualTo(boolean value) {
            addCriterion("`sandbox` <=", value, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxIn(List<Boolean> values) {
            addCriterion("`sandbox` in", values, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxNotIn(List<Boolean> values) {
            addCriterion("`sandbox` not in", values, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxBetween(boolean value1, boolean value2) {
            addCriterion("`sandbox` between", value1, value2, "sandbox");
            return (Criteria) this;
        }

        public Criteria andSandboxNotBetween(boolean value1, boolean value2) {
            addCriterion("`sandbox` not between", value1, value2, "sandbox");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("`enabled` is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("`enabled` is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(boolean value) {
            addCriterion("`enabled` =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(boolean value) {
            addCriterion("`enabled` <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(boolean value) {
            addCriterion("`enabled` >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(boolean value) {
            addCriterion("`enabled` >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(boolean value) {
            addCriterion("`enabled` <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(boolean value) {
            addCriterion("`enabled` <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Boolean> values) {
            addCriterion("`enabled` in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Boolean> values) {
            addCriterion("`enabled` not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(boolean value1, boolean value2) {
            addCriterion("`enabled` between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(boolean value1, boolean value2) {
            addCriterion("`enabled` not between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNull() {
            addCriterion("`created_date` is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNotNull() {
            addCriterion("`created_date` is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateEqualTo(Date value) {
            addCriterion("`created_date` =", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotEqualTo(Date value) {
            addCriterion("`created_date` <>", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThan(Date value) {
            addCriterion("`created_date` >", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`created_date` >=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThan(Date value) {
            addCriterion("`created_date` <", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
            addCriterion("`created_date` <=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIn(List<Date> values) {
            addCriterion("`created_date` in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotIn(List<Date> values) {
            addCriterion("`created_date` not in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateBetween(Date value1, Date value2) {
            addCriterion("`created_date` between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
            addCriterion("`created_date` not between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIsNull() {
            addCriterion("`updated_date` is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIsNotNull() {
            addCriterion("`updated_date` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateEqualTo(Date value) {
            addCriterion("`updated_date` =", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotEqualTo(Date value) {
            addCriterion("`updated_date` <>", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThan(Date value) {
            addCriterion("`updated_date` >", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`updated_date` >=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThan(Date value) {
            addCriterion("`updated_date` <", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThanOrEqualTo(Date value) {
            addCriterion("`updated_date` <=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIn(List<Date> values) {
            addCriterion("`updated_date` in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotIn(List<Date> values) {
            addCriterion("`updated_date` not in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateBetween(Date value1, Date value2) {
            addCriterion("`updated_date` between", value1, value2, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotBetween(Date value1, Date value2) {
            addCriterion("`updated_date` not between", value1, value2, "updatedDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated do_not_delete_during_merge Sun Feb 04 14:51:05 EST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__push_notification_device
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}