package com.bryllyant.kona.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PushNotificationMessageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
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
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public PushNotificationMessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
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
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
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
     * This class corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
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

        public Criteria andTitleIsNull() {
            addCriterion("`title` is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("`title` is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("`title` =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("`title` <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("`title` >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("`title` >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("`title` <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("`title` <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("`title` like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("`title` not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("`title` in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("`title` not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("`title` between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("`title` not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("`message` is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("`message` is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("`message` =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("`message` <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("`message` >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("`message` >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("`message` <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("`message` <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("`message` like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("`message` not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("`message` in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("`message` not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("`message` between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("`message` not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("`image_url` is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("`image_url` is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("`image_url` =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("`image_url` <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("`image_url` >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("`image_url` >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("`image_url` <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("`image_url` <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("`image_url` like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("`image_url` not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("`image_url` in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("`image_url` not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("`image_url` between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("`image_url` not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNull() {
            addCriterion("`action_url` is null");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNotNull() {
            addCriterion("`action_url` is not null");
            return (Criteria) this;
        }

        public Criteria andActionUrlEqualTo(String value) {
            addCriterion("`action_url` =", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotEqualTo(String value) {
            addCriterion("`action_url` <>", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThan(String value) {
            addCriterion("`action_url` >", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("`action_url` >=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThan(String value) {
            addCriterion("`action_url` <", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThanOrEqualTo(String value) {
            addCriterion("`action_url` <=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLike(String value) {
            addCriterion("`action_url` like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotLike(String value) {
            addCriterion("`action_url` not like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlIn(List<String> values) {
            addCriterion("`action_url` in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotIn(List<String> values) {
            addCriterion("`action_url` not in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlBetween(String value1, String value2) {
            addCriterion("`action_url` between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotBetween(String value1, String value2) {
            addCriterion("`action_url` not between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andFilterIsNull() {
            addCriterion("`filter` is null");
            return (Criteria) this;
        }

        public Criteria andFilterIsNotNull() {
            addCriterion("`filter` is not null");
            return (Criteria) this;
        }

        public Criteria andFilterEqualTo(String value) {
            addCriterion("`filter` =", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotEqualTo(String value) {
            addCriterion("`filter` <>", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterGreaterThan(String value) {
            addCriterion("`filter` >", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterGreaterThanOrEqualTo(String value) {
            addCriterion("`filter` >=", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLessThan(String value) {
            addCriterion("`filter` <", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLessThanOrEqualTo(String value) {
            addCriterion("`filter` <=", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLike(String value) {
            addCriterion("`filter` like", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotLike(String value) {
            addCriterion("`filter` not like", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterIn(List<String> values) {
            addCriterion("`filter` in", values, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotIn(List<String> values) {
            addCriterion("`filter` not in", values, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterBetween(String value1, String value2) {
            addCriterion("`filter` between", value1, value2, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotBetween(String value1, String value2) {
            addCriterion("`filter` not between", value1, value2, "filter");
            return (Criteria) this;
        }

        public Criteria andDeviceCountIsNull() {
            addCriterion("`device_count` is null");
            return (Criteria) this;
        }

        public Criteria andDeviceCountIsNotNull() {
            addCriterion("`device_count` is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceCountEqualTo(Integer value) {
            addCriterion("`device_count` =", value, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountNotEqualTo(Integer value) {
            addCriterion("`device_count` <>", value, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountGreaterThan(Integer value) {
            addCriterion("`device_count` >", value, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("`device_count` >=", value, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountLessThan(Integer value) {
            addCriterion("`device_count` <", value, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountLessThanOrEqualTo(Integer value) {
            addCriterion("`device_count` <=", value, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountIn(List<Integer> values) {
            addCriterion("`device_count` in", values, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountNotIn(List<Integer> values) {
            addCriterion("`device_count` not in", values, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountBetween(Integer value1, Integer value2) {
            addCriterion("`device_count` between", value1, value2, "deviceCount");
            return (Criteria) this;
        }

        public Criteria andDeviceCountNotBetween(Integer value1, Integer value2) {
            addCriterion("`device_count` not between", value1, value2, "deviceCount");
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
     * This class corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated do_not_delete_during_merge Fri Nov 24 12:43:45 EST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
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