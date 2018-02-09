package com.bryllyant.kona.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAuthExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
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
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public UserAuthExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
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
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
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
     * This class corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
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

        public Criteria andVoiceSampleIdIsNull() {
            addCriterion("`voice_sample_id` is null");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdIsNotNull() {
            addCriterion("`voice_sample_id` is not null");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdEqualTo(Long value) {
            addCriterion("`voice_sample_id` =", value, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdNotEqualTo(Long value) {
            addCriterion("`voice_sample_id` <>", value, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdGreaterThan(Long value) {
            addCriterion("`voice_sample_id` >", value, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`voice_sample_id` >=", value, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdLessThan(Long value) {
            addCriterion("`voice_sample_id` <", value, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdLessThanOrEqualTo(Long value) {
            addCriterion("`voice_sample_id` <=", value, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdIn(List<Long> values) {
            addCriterion("`voice_sample_id` in", values, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdNotIn(List<Long> values) {
            addCriterion("`voice_sample_id` not in", values, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdBetween(Long value1, Long value2) {
            addCriterion("`voice_sample_id` between", value1, value2, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andVoiceSampleIdNotBetween(Long value1, Long value2) {
            addCriterion("`voice_sample_id` not between", value1, value2, "voiceSampleId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdIsNull() {
            addCriterion("`fingerprint_id` is null");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdIsNotNull() {
            addCriterion("`fingerprint_id` is not null");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdEqualTo(Long value) {
            addCriterion("`fingerprint_id` =", value, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdNotEqualTo(Long value) {
            addCriterion("`fingerprint_id` <>", value, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdGreaterThan(Long value) {
            addCriterion("`fingerprint_id` >", value, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`fingerprint_id` >=", value, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdLessThan(Long value) {
            addCriterion("`fingerprint_id` <", value, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdLessThanOrEqualTo(Long value) {
            addCriterion("`fingerprint_id` <=", value, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdIn(List<Long> values) {
            addCriterion("`fingerprint_id` in", values, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdNotIn(List<Long> values) {
            addCriterion("`fingerprint_id` not in", values, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdBetween(Long value1, Long value2) {
            addCriterion("`fingerprint_id` between", value1, value2, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andFingerprintIdNotBetween(Long value1, Long value2) {
            addCriterion("`fingerprint_id` not between", value1, value2, "fingerprintId");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPinIsNull() {
            addCriterion("`pin` is null");
            return (Criteria) this;
        }

        public Criteria andPinIsNotNull() {
            addCriterion("`pin` is not null");
            return (Criteria) this;
        }

        public Criteria andPinEqualTo(String value) {
            addCriterion("`pin` =", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinNotEqualTo(String value) {
            addCriterion("`pin` <>", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinGreaterThan(String value) {
            addCriterion("`pin` >", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinGreaterThanOrEqualTo(String value) {
            addCriterion("`pin` >=", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinLessThan(String value) {
            addCriterion("`pin` <", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinLessThanOrEqualTo(String value) {
            addCriterion("`pin` <=", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinLike(String value) {
            addCriterion("`pin` like", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinNotLike(String value) {
            addCriterion("`pin` not like", value, "pin");
            return (Criteria) this;
        }

        public Criteria andPinIn(List<String> values) {
            addCriterion("`pin` in", values, "pin");
            return (Criteria) this;
        }

        public Criteria andPinNotIn(List<String> values) {
            addCriterion("`pin` not in", values, "pin");
            return (Criteria) this;
        }

        public Criteria andPinBetween(String value1, String value2) {
            addCriterion("`pin` between", value1, value2, "pin");
            return (Criteria) this;
        }

        public Criteria andPinNotBetween(String value1, String value2) {
            addCriterion("`pin` not between", value1, value2, "pin");
            return (Criteria) this;
        }

        public Criteria andQuestion1IsNull() {
            addCriterion("`question1` is null");
            return (Criteria) this;
        }

        public Criteria andQuestion1IsNotNull() {
            addCriterion("`question1` is not null");
            return (Criteria) this;
        }

        public Criteria andQuestion1EqualTo(String value) {
            addCriterion("`question1` =", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1NotEqualTo(String value) {
            addCriterion("`question1` <>", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1GreaterThan(String value) {
            addCriterion("`question1` >", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1GreaterThanOrEqualTo(String value) {
            addCriterion("`question1` >=", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1LessThan(String value) {
            addCriterion("`question1` <", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1LessThanOrEqualTo(String value) {
            addCriterion("`question1` <=", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1Like(String value) {
            addCriterion("`question1` like", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1NotLike(String value) {
            addCriterion("`question1` not like", value, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1In(List<String> values) {
            addCriterion("`question1` in", values, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1NotIn(List<String> values) {
            addCriterion("`question1` not in", values, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1Between(String value1, String value2) {
            addCriterion("`question1` between", value1, value2, "question1");
            return (Criteria) this;
        }

        public Criteria andQuestion1NotBetween(String value1, String value2) {
            addCriterion("`question1` not between", value1, value2, "question1");
            return (Criteria) this;
        }

        public Criteria andAnswer1IsNull() {
            addCriterion("`answer1` is null");
            return (Criteria) this;
        }

        public Criteria andAnswer1IsNotNull() {
            addCriterion("`answer1` is not null");
            return (Criteria) this;
        }

        public Criteria andAnswer1EqualTo(String value) {
            addCriterion("`answer1` =", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotEqualTo(String value) {
            addCriterion("`answer1` <>", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1GreaterThan(String value) {
            addCriterion("`answer1` >", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1GreaterThanOrEqualTo(String value) {
            addCriterion("`answer1` >=", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1LessThan(String value) {
            addCriterion("`answer1` <", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1LessThanOrEqualTo(String value) {
            addCriterion("`answer1` <=", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1Like(String value) {
            addCriterion("`answer1` like", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotLike(String value) {
            addCriterion("`answer1` not like", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1In(List<String> values) {
            addCriterion("`answer1` in", values, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotIn(List<String> values) {
            addCriterion("`answer1` not in", values, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1Between(String value1, String value2) {
            addCriterion("`answer1` between", value1, value2, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotBetween(String value1, String value2) {
            addCriterion("`answer1` not between", value1, value2, "answer1");
            return (Criteria) this;
        }

        public Criteria andQuestion2IsNull() {
            addCriterion("`question2` is null");
            return (Criteria) this;
        }

        public Criteria andQuestion2IsNotNull() {
            addCriterion("`question2` is not null");
            return (Criteria) this;
        }

        public Criteria andQuestion2EqualTo(String value) {
            addCriterion("`question2` =", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2NotEqualTo(String value) {
            addCriterion("`question2` <>", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2GreaterThan(String value) {
            addCriterion("`question2` >", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2GreaterThanOrEqualTo(String value) {
            addCriterion("`question2` >=", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2LessThan(String value) {
            addCriterion("`question2` <", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2LessThanOrEqualTo(String value) {
            addCriterion("`question2` <=", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2Like(String value) {
            addCriterion("`question2` like", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2NotLike(String value) {
            addCriterion("`question2` not like", value, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2In(List<String> values) {
            addCriterion("`question2` in", values, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2NotIn(List<String> values) {
            addCriterion("`question2` not in", values, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2Between(String value1, String value2) {
            addCriterion("`question2` between", value1, value2, "question2");
            return (Criteria) this;
        }

        public Criteria andQuestion2NotBetween(String value1, String value2) {
            addCriterion("`question2` not between", value1, value2, "question2");
            return (Criteria) this;
        }

        public Criteria andAnswer2IsNull() {
            addCriterion("`answer2` is null");
            return (Criteria) this;
        }

        public Criteria andAnswer2IsNotNull() {
            addCriterion("`answer2` is not null");
            return (Criteria) this;
        }

        public Criteria andAnswer2EqualTo(String value) {
            addCriterion("`answer2` =", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotEqualTo(String value) {
            addCriterion("`answer2` <>", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2GreaterThan(String value) {
            addCriterion("`answer2` >", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2GreaterThanOrEqualTo(String value) {
            addCriterion("`answer2` >=", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2LessThan(String value) {
            addCriterion("`answer2` <", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2LessThanOrEqualTo(String value) {
            addCriterion("`answer2` <=", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2Like(String value) {
            addCriterion("`answer2` like", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotLike(String value) {
            addCriterion("`answer2` not like", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2In(List<String> values) {
            addCriterion("`answer2` in", values, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotIn(List<String> values) {
            addCriterion("`answer2` not in", values, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2Between(String value1, String value2) {
            addCriterion("`answer2` between", value1, value2, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotBetween(String value1, String value2) {
            addCriterion("`answer2` not between", value1, value2, "answer2");
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
     * This class corresponds to the database table kona__user_auth
     *
     * @mbg.generated do_not_delete_during_merge Fri Feb 09 05:50:00 EST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__user_auth
     *
     * @mbg.generated Fri Feb 09 05:50:00 EST 2018
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