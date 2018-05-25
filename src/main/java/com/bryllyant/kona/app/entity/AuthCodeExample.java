package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseAuthCode.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthCodeExample implements com.bryllyant.kona.data.mybatis.KEntityExample<AuthCodeExample.Criteria> {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__auth_code
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
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public AuthCodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
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
     * This method corresponds to the database table kona__auth_code
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
     * This method corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__auth_code
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
     * This class corresponds to the database table kona__auth_code
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

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Type value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Type value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Type value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Type value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Type value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Type value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(Type value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(Type value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Type> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Type> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Type value1, Type value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Type value1, Type value2) {
            addCriterion("`type` not between", value1, value2, "type");
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

        public Criteria andCodeIsNull() {
            addCriterion("`code` is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("`code` is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("`code` =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("`code` <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("`code` >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("`code` >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("`code` <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("`code` <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("`code` like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("`code` not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("`code` in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("`code` not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("`code` between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("`code` not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("`valid` is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("`valid` is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(boolean value) {
            addCriterion("`valid` =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(boolean value) {
            addCriterion("`valid` <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(boolean value) {
            addCriterion("`valid` >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(boolean value) {
            addCriterion("`valid` >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(boolean value) {
            addCriterion("`valid` <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(boolean value) {
            addCriterion("`valid` <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Boolean> values) {
            addCriterion("`valid` in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Boolean> values) {
            addCriterion("`valid` not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(boolean value1, boolean value2) {
            addCriterion("`valid` between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(boolean value1, boolean value2) {
            addCriterion("`valid` not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andUseCountIsNull() {
            addCriterion("`use_count` is null");
            return (Criteria) this;
        }

        public Criteria andUseCountIsNotNull() {
            addCriterion("`use_count` is not null");
            return (Criteria) this;
        }

        public Criteria andUseCountEqualTo(Integer value) {
            addCriterion("`use_count` =", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountNotEqualTo(Integer value) {
            addCriterion("`use_count` <>", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountGreaterThan(Integer value) {
            addCriterion("`use_count` >", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("`use_count` >=", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountLessThan(Integer value) {
            addCriterion("`use_count` <", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountLessThanOrEqualTo(Integer value) {
            addCriterion("`use_count` <=", value, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountIn(List<Integer> values) {
            addCriterion("`use_count` in", values, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountNotIn(List<Integer> values) {
            addCriterion("`use_count` not in", values, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountBetween(Integer value1, Integer value2) {
            addCriterion("`use_count` between", value1, value2, "useCount");
            return (Criteria) this;
        }

        public Criteria andUseCountNotBetween(Integer value1, Integer value2) {
            addCriterion("`use_count` not between", value1, value2, "useCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountIsNull() {
            addCriterion("`max_use_count` is null");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountIsNotNull() {
            addCriterion("`max_use_count` is not null");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountEqualTo(Integer value) {
            addCriterion("`max_use_count` =", value, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountNotEqualTo(Integer value) {
            addCriterion("`max_use_count` <>", value, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountGreaterThan(Integer value) {
            addCriterion("`max_use_count` >", value, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("`max_use_count` >=", value, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountLessThan(Integer value) {
            addCriterion("`max_use_count` <", value, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountLessThanOrEqualTo(Integer value) {
            addCriterion("`max_use_count` <=", value, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountIn(List<Integer> values) {
            addCriterion("`max_use_count` in", values, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountNotIn(List<Integer> values) {
            addCriterion("`max_use_count` not in", values, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountBetween(Integer value1, Integer value2) {
            addCriterion("`max_use_count` between", value1, value2, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andMaxUseCountNotBetween(Integer value1, Integer value2) {
            addCriterion("`max_use_count` not between", value1, value2, "maxUseCount");
            return (Criteria) this;
        }

        public Criteria andExpirationDateIsNull() {
            addCriterion("`expiration_date` is null");
            return (Criteria) this;
        }

        public Criteria andExpirationDateIsNotNull() {
            addCriterion("`expiration_date` is not null");
            return (Criteria) this;
        }

        public Criteria andExpirationDateEqualTo(Date value) {
            addCriterion("`expiration_date` =", value, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateNotEqualTo(Date value) {
            addCriterion("`expiration_date` <>", value, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateGreaterThan(Date value) {
            addCriterion("`expiration_date` >", value, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`expiration_date` >=", value, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateLessThan(Date value) {
            addCriterion("`expiration_date` <", value, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateLessThanOrEqualTo(Date value) {
            addCriterion("`expiration_date` <=", value, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateIn(List<Date> values) {
            addCriterion("`expiration_date` in", values, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateNotIn(List<Date> values) {
            addCriterion("`expiration_date` not in", values, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateBetween(Date value1, Date value2) {
            addCriterion("`expiration_date` between", value1, value2, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andExpirationDateNotBetween(Date value1, Date value2) {
            addCriterion("`expiration_date` not between", value1, value2, "expirationDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateIsNull() {
            addCriterion("`confirmed_date` is null");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateIsNotNull() {
            addCriterion("`confirmed_date` is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateEqualTo(Date value) {
            addCriterion("`confirmed_date` =", value, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateNotEqualTo(Date value) {
            addCriterion("`confirmed_date` <>", value, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateGreaterThan(Date value) {
            addCriterion("`confirmed_date` >", value, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`confirmed_date` >=", value, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateLessThan(Date value) {
            addCriterion("`confirmed_date` <", value, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateLessThanOrEqualTo(Date value) {
            addCriterion("`confirmed_date` <=", value, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateIn(List<Date> values) {
            addCriterion("`confirmed_date` in", values, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateNotIn(List<Date> values) {
            addCriterion("`confirmed_date` not in", values, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateBetween(Date value1, Date value2) {
            addCriterion("`confirmed_date` between", value1, value2, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andConfirmedDateNotBetween(Date value1, Date value2) {
            addCriterion("`confirmed_date` not between", value1, value2, "confirmedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateIsNull() {
            addCriterion("`last_accessed_date` is null");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateIsNotNull() {
            addCriterion("`last_accessed_date` is not null");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateEqualTo(Date value) {
            addCriterion("`last_accessed_date` =", value, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateNotEqualTo(Date value) {
            addCriterion("`last_accessed_date` <>", value, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateGreaterThan(Date value) {
            addCriterion("`last_accessed_date` >", value, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`last_accessed_date` >=", value, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateLessThan(Date value) {
            addCriterion("`last_accessed_date` <", value, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateLessThanOrEqualTo(Date value) {
            addCriterion("`last_accessed_date` <=", value, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateIn(List<Date> values) {
            addCriterion("`last_accessed_date` in", values, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateNotIn(List<Date> values) {
            addCriterion("`last_accessed_date` not in", values, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateBetween(Date value1, Date value2) {
            addCriterion("`last_accessed_date` between", value1, value2, "lastAccessedDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessedDateNotBetween(Date value1, Date value2) {
            addCriterion("`last_accessed_date` not between", value1, value2, "lastAccessedDate");
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
     * This class corresponds to the database table kona__auth_code
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
     * This class corresponds to the database table kona__auth_code
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