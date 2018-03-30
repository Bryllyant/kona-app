package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

/**
 * Core User object for framework interaction.
 * Note that some attributes refer to the User itself
 * (e.g. firstName, lastName, etc.) while others refer to the
 * User's account (e.g.  enabled, active, etc.). 
 */
    
public interface KUser extends KEntityObject {
    enum Type {
        SYSTEM,
        USER,
        TEST,
    }

    enum Presence {
        ONLINE,
        AWAY,
        BUSY,
        INVISIBLE,
        STREAMING,
        OFFLINE
    }

    enum Gender {
        MALE,
        FEMALE,
        NON_BINARY,
        TRANSGENDER,
        INTERSEX,
        GENDER_NON_CONFORMING,
        OTHER,
        DECLINE
    }

//    Set<String> getRoleList();
//    String getRolesAsString();
//    void removeRole(String roleName);
//    void removeRoles(Collection<String> roleNames);
//    void addRole(String roleName);
//    void addRoles(Collection<String> roleNames);

    /**
     * Internal object identifier. Do not exported or referenced externally.
     */
    public Long getId(); 
    public void setId(Long id);
    
    /** 
     * The user's publicly visible identifier.
     */
    public String getUid(); 
    public void setUid(String uid);
    
    public Long getParentId(); 
    public void setParentId(Long parentId);

    /**
     * The a reference to the user's type.
     */
    public Type getType();
    public void setType(Type type);
    
    public Long getAccountId();
    public void setAccountId(Long accountId);
    
    public Presence getPresence();
    public void setPresence(Presence presence);

    public Long getPositionId();
    public void setPositionId(Long positionId);
    
    public Long getPhotoId();
    public void setPhotoId(Long photoId);

    public String getPhotoUrl();
    public void setPhotoUrl(String photoUrl);

    public String getThumbnailUrl();
    public void setThumbnailUrl(String thumbnailUrl);
    
    /** 
     * The user's username which is used to login to the system.
     */
    public String getUsername(); 
    public void setUsername(String username);

    /**
     * The user's first name.
     */
    public String getFirstName();
    public void setFirstName(String firstName);

    /**
     * The user's last name.
     */
    public String getLastName();
    public void setLastName(String lastName);

    /**
     * The user's preferred display name.  This is the publicly
     * visible name of this user to other users in the system.
     */
    public String getDisplayName();
    public void setDisplayName(String displayName);

    /**
     * The user's email address.
     */
    public String getEmail();
    public void setEmail(String email);

    /**
     * The users's mobile number.
     */
    public String getMobileNumber();
    public void setMobileNumber(String mobileNumber);

    
    /**
     * The user's gender.
     */
    public Gender getGender();
    public void setGender(Gender gender);

    /**
     * The user's date of birth.
     */
    public Date getBirthDate();
    public void setBirthDate(Date birthDate);


    /** 
     * The user's default locale.
     */
    public String getLocale();
    public void setLocale(String locale);

    /**
     * The users' default time zone.
     */
    public String getTimeZone();
    public void setTimeZone(String timeZone);


    // hold distance in search results
    Double getDistance();

    void setDistance(Double distance);

    /**
     * Flag to indicate if this user account is currently enabled.
     * This flag is set by the system to control the user's access
     * to the account.  For example, this flag may be set to false
     * if the user exceeds a specfic number of failed login attempts.
     */
    public boolean isEnabled();
    public void setEnabled(boolean enabled);





//    /**
//     * Flag to indicate if this user is currently logged in. If
//     * this flag is true, then getLoginDate() should return the
//     * date the user logged in.  If this flag is false, then
//     * getLoginDate() should return null.
//     */
//    public boolean isLoggedIn();
//    public void setLoggedIn(boolean loggedIn);

    /**
     * Flag to indicate if this user is available online. An online
     * user may be able to interact with the system (and other users).
     *
     * <p>
     * NOTE: This flag may (should) be set by the user to indicate
     * his/her online presence. 
     * </p>
     *
     * <p>
     * NOTE: If this flag is set to false, the user's presence may
     * be one of many other states such as busy, away, etc. and does
     * not necessarily indicate that the user is offline.
     * </p>
     */
//    public boolean isOnline();
//    public void setOnline(boolean online);

    /**
     * The date this user account was created.
     */
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);

    /**
     * The date this user account was retired.
     */
    public Date getDeletedDate();
    public void setDeletedDate(Date deletedDate);

    /**
     * The login date of this user's current session.  If the user
     * is not online
     */
    public Date getLoginDate();
    public void setLoginDate(Date loginDate);

    /**
     * The date the user last logged in prior to his or her current session.
     */
    public Date getLastLoginDate();
    public void setLastLoginDate(Date lastLoginDate);

    /**
     * The date this user record was last updated.
     */
    public Date getUpdatedDate();
    public void setUpdatedDate(Date updatedDate);
}
