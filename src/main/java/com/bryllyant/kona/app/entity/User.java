package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseUser.Gender;
import com.bryllyant.kona.app.entity.BaseUser.Presence;
import com.bryllyant.kona.app.entity.BaseUser.Type;
import java.io.Serializable;
import java.util.Date;

public class User extends BaseUser implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.parent_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.type
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.account_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.position_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long positionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.photo_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long photoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.photo_url
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String photoUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.thumbnail_url
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String thumbnailUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.username
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.email
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.mobile_number
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String mobileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.first_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.last_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.display_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String displayName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.gender
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Gender gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.birth_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date birthDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.locale
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String locale;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.time_zone
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String timeZone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.presence
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Presence presence;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.enabled
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.deleted_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date deletedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.last_login_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date lastLoginDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__user
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.uid
     *
     * @return the value of kona__user.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.uid
     *
     * @param uid the value for kona__user.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.parent_id
     *
     * @return the value of kona__user.parent_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.parent_id
     *
     * @param parentId the value for kona__user.parent_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.type
     *
     * @return the value of kona__user.type
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.type
     *
     * @param type the value for kona__user.type
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.account_id
     *
     * @return the value of kona__user.account_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.account_id
     *
     * @param accountId the value for kona__user.account_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.position_id
     *
     * @return the value of kona__user.position_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.position_id
     *
     * @param positionId the value for kona__user.position_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.photo_id
     *
     * @return the value of kona__user.photo_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.photo_id
     *
     * @param photoId the value for kona__user.photo_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.photo_url
     *
     * @return the value of kona__user.photo_url
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.photo_url
     *
     * @param photoUrl the value for kona__user.photo_url
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.thumbnail_url
     *
     * @return the value of kona__user.thumbnail_url
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.thumbnail_url
     *
     * @param thumbnailUrl the value for kona__user.thumbnail_url
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl == null ? null : thumbnailUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.username
     *
     * @return the value of kona__user.username
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.username
     *
     * @param username the value for kona__user.username
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.email
     *
     * @return the value of kona__user.email
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.email
     *
     * @param email the value for kona__user.email
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.mobile_number
     *
     * @return the value of kona__user.mobile_number
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.mobile_number
     *
     * @param mobileNumber the value for kona__user.mobile_number
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.first_name
     *
     * @return the value of kona__user.first_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.first_name
     *
     * @param firstName the value for kona__user.first_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.last_name
     *
     * @return the value of kona__user.last_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.last_name
     *
     * @param lastName the value for kona__user.last_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.display_name
     *
     * @return the value of kona__user.display_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.display_name
     *
     * @param displayName the value for kona__user.display_name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.gender
     *
     * @return the value of kona__user.gender
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.gender
     *
     * @param gender the value for kona__user.gender
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.birth_date
     *
     * @return the value of kona__user.birth_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.birth_date
     *
     * @param birthDate the value for kona__user.birth_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.locale
     *
     * @return the value of kona__user.locale
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getLocale() {
        return locale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.locale
     *
     * @param locale the value for kona__user.locale
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setLocale(String locale) {
        this.locale = locale == null ? null : locale.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.time_zone
     *
     * @return the value of kona__user.time_zone
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.time_zone
     *
     * @param timeZone the value for kona__user.time_zone
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone == null ? null : timeZone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.presence
     *
     * @return the value of kona__user.presence
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Presence getPresence() {
        return presence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.presence
     *
     * @param presence the value for kona__user.presence
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.enabled
     *
     * @return the value of kona__user.enabled
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.enabled
     *
     * @param enabled the value for kona__user.enabled
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.deleted_date
     *
     * @return the value of kona__user.deleted_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.deleted_date
     *
     * @param deletedDate the value for kona__user.deleted_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.last_login_date
     *
     * @return the value of kona__user.last_login_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.last_login_date
     *
     * @param lastLoginDate the value for kona__user.last_login_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.created_date
     *
     * @return the value of kona__user.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.created_date
     *
     * @param createdDate the value for kona__user.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user.updated_date
     *
     * @return the value of kona__user.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user.updated_date
     *
     * @param updatedDate the value for kona__user.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}