package com.bryllyant.kona.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailAttachmentExample implements com.bryllyant.kona.data.mybatis.KEntityExample<EmailAttachmentExample.Criteria> {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
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
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public EmailAttachmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
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
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
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
     * This class corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
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

        public Criteria andContentIdIsNull() {
            addCriterion("`content_id` is null");
            return (Criteria) this;
        }

        public Criteria andContentIdIsNotNull() {
            addCriterion("`content_id` is not null");
            return (Criteria) this;
        }

        public Criteria andContentIdEqualTo(Long value) {
            addCriterion("`content_id` =", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotEqualTo(Long value) {
            addCriterion("`content_id` <>", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThan(Long value) {
            addCriterion("`content_id` >", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`content_id` >=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThan(Long value) {
            addCriterion("`content_id` <", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThanOrEqualTo(Long value) {
            addCriterion("`content_id` <=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdIn(List<Long> values) {
            addCriterion("`content_id` in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotIn(List<Long> values) {
            addCriterion("`content_id` not in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdBetween(Long value1, Long value2) {
            addCriterion("`content_id` between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotBetween(Long value1, Long value2) {
            addCriterion("`content_id` not between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNull() {
            addCriterion("`file_id` is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("`file_id` is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Long value) {
            addCriterion("`file_id` =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Long value) {
            addCriterion("`file_id` <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Long value) {
            addCriterion("`file_id` >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`file_id` >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Long value) {
            addCriterion("`file_id` <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Long value) {
            addCriterion("`file_id` <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Long> values) {
            addCriterion("`file_id` in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Long> values) {
            addCriterion("`file_id` not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Long value1, Long value2) {
            addCriterion("`file_id` between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Long value1, Long value2) {
            addCriterion("`file_id` not between", value1, value2, "fileId");
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
     * This class corresponds to the database table kona__email_attachment
     *
     * @mbg.generated do_not_delete_during_merge Mon Mar 05 08:53:26 EST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Mon Mar 05 08:53:26 EST 2018
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