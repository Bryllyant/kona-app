package com.bryllyant.kona.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendshipExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__friendship
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
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public FriendshipExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
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
     * This method corresponds to the database table kona__friendship
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
     * This method corresponds to the database table kona__friendship
     *
     * @mbg.generated Sun Feb 04 14:51:05 EST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__friendship
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
     * This class corresponds to the database table kona__friendship
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

        public Criteria andFriendIdIsNull() {
            addCriterion("`friend_id` is null");
            return (Criteria) this;
        }

        public Criteria andFriendIdIsNotNull() {
            addCriterion("`friend_id` is not null");
            return (Criteria) this;
        }

        public Criteria andFriendIdEqualTo(Long value) {
            addCriterion("`friend_id` =", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotEqualTo(Long value) {
            addCriterion("`friend_id` <>", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdGreaterThan(Long value) {
            addCriterion("`friend_id` >", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`friend_id` >=", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdLessThan(Long value) {
            addCriterion("`friend_id` <", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdLessThanOrEqualTo(Long value) {
            addCriterion("`friend_id` <=", value, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdIn(List<Long> values) {
            addCriterion("`friend_id` in", values, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotIn(List<Long> values) {
            addCriterion("`friend_id` not in", values, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdBetween(Long value1, Long value2) {
            addCriterion("`friend_id` between", value1, value2, "friendId");
            return (Criteria) this;
        }

        public Criteria andFriendIdNotBetween(Long value1, Long value2) {
            addCriterion("`friend_id` not between", value1, value2, "friendId");
            return (Criteria) this;
        }

        public Criteria andCircleIdIsNull() {
            addCriterion("`circle_id` is null");
            return (Criteria) this;
        }

        public Criteria andCircleIdIsNotNull() {
            addCriterion("`circle_id` is not null");
            return (Criteria) this;
        }

        public Criteria andCircleIdEqualTo(Long value) {
            addCriterion("`circle_id` =", value, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdNotEqualTo(Long value) {
            addCriterion("`circle_id` <>", value, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdGreaterThan(Long value) {
            addCriterion("`circle_id` >", value, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`circle_id` >=", value, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdLessThan(Long value) {
            addCriterion("`circle_id` <", value, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdLessThanOrEqualTo(Long value) {
            addCriterion("`circle_id` <=", value, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdIn(List<Long> values) {
            addCriterion("`circle_id` in", values, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdNotIn(List<Long> values) {
            addCriterion("`circle_id` not in", values, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdBetween(Long value1, Long value2) {
            addCriterion("`circle_id` between", value1, value2, "circleId");
            return (Criteria) this;
        }

        public Criteria andCircleIdNotBetween(Long value1, Long value2) {
            addCriterion("`circle_id` not between", value1, value2, "circleId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("`status_id` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("`status_id` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(Long value) {
            addCriterion("`status_id` =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(Long value) {
            addCriterion("`status_id` <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(Long value) {
            addCriterion("`status_id` >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`status_id` >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(Long value) {
            addCriterion("`status_id` <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(Long value) {
            addCriterion("`status_id` <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<Long> values) {
            addCriterion("`status_id` in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<Long> values) {
            addCriterion("`status_id` not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(Long value1, Long value2) {
            addCriterion("`status_id` between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(Long value1, Long value2) {
            addCriterion("`status_id` not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedIsNull() {
            addCriterion("`friendship_requested` is null");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedIsNotNull() {
            addCriterion("`friendship_requested` is not null");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedEqualTo(boolean value) {
            addCriterion("`friendship_requested` =", value, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedNotEqualTo(boolean value) {
            addCriterion("`friendship_requested` <>", value, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedGreaterThan(boolean value) {
            addCriterion("`friendship_requested` >", value, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedGreaterThanOrEqualTo(boolean value) {
            addCriterion("`friendship_requested` >=", value, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedLessThan(boolean value) {
            addCriterion("`friendship_requested` <", value, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedLessThanOrEqualTo(boolean value) {
            addCriterion("`friendship_requested` <=", value, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedIn(List<Boolean> values) {
            addCriterion("`friendship_requested` in", values, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedNotIn(List<Boolean> values) {
            addCriterion("`friendship_requested` not in", values, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedBetween(boolean value1, boolean value2) {
            addCriterion("`friendship_requested` between", value1, value2, "friendshipRequested");
            return (Criteria) this;
        }

        public Criteria andFriendshipRequestedNotBetween(boolean value1, boolean value2) {
            addCriterion("`friendship_requested` not between", value1, value2, "friendshipRequested");
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
     * This class corresponds to the database table kona__friendship
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
     * This class corresponds to the database table kona__friendship
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