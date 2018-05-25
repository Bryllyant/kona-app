package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BasePush.Platform;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PushDeviceExample implements com.bryllyant.kona.data.mybatis.KEntityExample<PushDeviceExample.Criteria> {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table users
     *
     * @mbggenerated
     */
    private String fts;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public PushDeviceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
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
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
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
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    public void setFts(String fts) {
        this.fts = fts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    public String getFts() {
        return fts;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
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

        public Criteria andProviderIdIsNull() {
            addCriterion("`provider_id` is null");
            return (Criteria) this;
        }

        public Criteria andProviderIdIsNotNull() {
            addCriterion("`provider_id` is not null");
            return (Criteria) this;
        }

        public Criteria andProviderIdEqualTo(Long value) {
            addCriterion("`provider_id` =", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotEqualTo(Long value) {
            addCriterion("`provider_id` <>", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThan(Long value) {
            addCriterion("`provider_id` >", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`provider_id` >=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThan(Long value) {
            addCriterion("`provider_id` <", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThanOrEqualTo(Long value) {
            addCriterion("`provider_id` <=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdIn(List<Long> values) {
            addCriterion("`provider_id` in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotIn(List<Long> values) {
            addCriterion("`provider_id` not in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdBetween(Long value1, Long value2) {
            addCriterion("`provider_id` between", value1, value2, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotBetween(Long value1, Long value2) {
            addCriterion("`provider_id` not between", value1, value2, "providerId");
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

        public Criteria andPlatformIsNull() {
            addCriterion("`platform` is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("`platform` is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(Platform value) {
            addCriterion("`platform` =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(Platform value) {
            addCriterion("`platform` <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(Platform value) {
            addCriterion("`platform` >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(Platform value) {
            addCriterion("`platform` >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(Platform value) {
            addCriterion("`platform` <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(Platform value) {
            addCriterion("`platform` <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLike(Platform value) {
            addCriterion("`platform` like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotLike(Platform value) {
            addCriterion("`platform` not like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<Platform> values) {
            addCriterion("`platform` in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<Platform> values) {
            addCriterion("`platform` not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(Platform value1, Platform value2) {
            addCriterion("`platform` between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(Platform value1, Platform value2) {
            addCriterion("`platform` not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("`token` is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("`token` is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("`token` =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("`token` <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("`token` >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("`token` >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("`token` <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("`token` <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("`token` like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("`token` not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("`token` in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("`token` not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("`token` between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("`token` not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andEndpointIsNull() {
            addCriterion("`endpoint` is null");
            return (Criteria) this;
        }

        public Criteria andEndpointIsNotNull() {
            addCriterion("`endpoint` is not null");
            return (Criteria) this;
        }

        public Criteria andEndpointEqualTo(String value) {
            addCriterion("`endpoint` =", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointNotEqualTo(String value) {
            addCriterion("`endpoint` <>", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointGreaterThan(String value) {
            addCriterion("`endpoint` >", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointGreaterThanOrEqualTo(String value) {
            addCriterion("`endpoint` >=", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointLessThan(String value) {
            addCriterion("`endpoint` <", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointLessThanOrEqualTo(String value) {
            addCriterion("`endpoint` <=", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointLike(String value) {
            addCriterion("`endpoint` like", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointNotLike(String value) {
            addCriterion("`endpoint` not like", value, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointIn(List<String> values) {
            addCriterion("`endpoint` in", values, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointNotIn(List<String> values) {
            addCriterion("`endpoint` not in", values, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointBetween(String value1, String value2) {
            addCriterion("`endpoint` between", value1, value2, "endpoint");
            return (Criteria) this;
        }

        public Criteria andEndpointNotBetween(String value1, String value2) {
            addCriterion("`endpoint` not between", value1, value2, "endpoint");
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
     * This class corresponds to the database table kona__push_device
     *
     * @mbg.generated do_not_delete_during_merge Sat May 19 06:25:18 EDT 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__push_device
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
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