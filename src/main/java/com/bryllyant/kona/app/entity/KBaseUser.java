package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseUser extends KBaseEntity implements KUser {
    //private static Logger logger = LoggerFactory.getLogger(KBaseUser.class);

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String uid;
    private Long parentId;
    private Type type;
    private Long accountId;
    private Presence presence;
    private Long positionId;
    private Long photoId;
    private String photoUrl;
    private String thumbnailUrl;
    private String username;
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String mobileNumber;
    private Gender gender;
    private Date birthDate;
    private String locale;
    private String timeZone;
    private boolean enabled;
    private Date createdDate;
    private Date deletedDate;
    private Date loginDate;
    private Date lastLoginDate;
    private Date updatedDate;
    
    // in meters
    private Double distance;


    // hold distance in search results
    @Override
    public Double getDistance() {
        return distance;
    }


    @Override
    public void setDistance(Double distance) {
        this.distance = distance;
    }


//    @Override
//    public boolean isEnabled() {
//        return (getStatusId() != null && getStatusId().equals(KUserStatus.ENABLED.getId()));
//    }
//
//
//    @Override
//    public void setEnabled(boolean enabled) {
//        if (enabled) {
//            setStatusId(KUserStatus.ENABLED.getId());
//        } else {
//            setStatusId(KUserStatus.LOCKED.getId());
//        }
//    }
//
//
//    @Override
//    public boolean isLoggedIn() {
//        return (getLoginDate() != null);
//    }
//
//
//    @Override
//    public void setLoggedIn(boolean loggedIn) {
//        if (loggedIn && getLoginDate() == null) {
//            setLoginDate(new Date());
//        } else if (!loggedIn && getLoginDate() != null) {
//            setLastLoginDate(getLoginDate());
//            setLoginDate(null);
//            setPresenceId(KUserPresence.OFFLINE.getId());
//        }
//    }
//
//
//    @Override
//    public boolean isOnline() {
//        return (isLoggedIn() && getPresenceId() != null && getPresenceId().equals(KUserPresence.ONLINE.getId()));
//    }
//
//
//    @Override
//    public void setOnline(boolean online) {
//        if (online) {
//            setPresenceId(KUserPresence.ONLINE.getId());
//        } else {
//            setPresenceId(KUserPresence.OFFLINE.getId());
//        }
//    }

//
//    @Override
//    public Set<String> getRoleList() {
//        return KUserRole.toStringList(this.roles);
//    }
//
//
//    @Override
//    public String getRolesAsString() {
//        String roleNames = null;
//
//        Set<String> set = KUserRole.toStringList(this.roles);
//
//        if (set != null) {
//            roleNames = KStringUtil.join(set, ",");
//        }
//
//        return roleNames;
//    }
//
//    @Override
//    public void removeRole(String roleName) {
//        Set<String> list = new HashSet<>();
//
//        list.add(roleName);
//
//        removeRoles(list);
//    }
//
//
//    @Override
//    public void removeRoles(Collection<String> roleNames) {
//        Set<KUserRole> roleList = KUserRole.parse(this.roles);
//
//        // have no roles, return;
//        if (roleList == null) return;
//
//        if (roleNames == null) return;
//
//        Set<KUserRole> removeRoles = new HashSet<>();
//
//        for (String roleName : roleNames) {
//            Set<KUserRole> tmpRoles = KUserRole.parse(roleName);
//
//            if (tmpRoles != null) {
//                for (KUserRole tmpRole : tmpRoles) {
//                    removeRoles.add(tmpRole);
//                }
//            }
//        }
//
//        // nothing to remove; return.
//        if (removeRoles.size() == 0) return;
//
//        roleList.removeAll(removeRoles);
//
//        setRoles(KUserRole.toLong(roleList));
//    }
//
//
//    @Override
//    public void addRole(String roleName) {
//        Set<String> list = new HashSet<>();
//
//        list.add(roleName);
//
//        addRoles(list);
//    }
//
//
//    @Override
//    public void addRoles(Collection<String> roleNames) {
//        if (roleNames == null) return;
//
//        Set<KUserRole> newRoles = new HashSet<>();
//
//        for (String roleName : roleNames) {
//            Set<KUserRole> tmpRoles = KUserRole.parse(roleName);
//
//            if (tmpRoles != null) {
//                for (KUserRole tmpRole : tmpRoles) {
//                    newRoles.add(tmpRole);
//                }
//            }
//        }
//
//        if (newRoles.size() == 0) return;
//
//        Set<KUserRole> roleList = KUserRole.parse(this.roles);
//
//        if (roleList == null) {
//            roleList = new HashSet<>();
//        }
//
//        for (KUserRole role : newRoles) {
//            roleList.add(role);
//        }
//
//        //logger.debug("addRoles: roleList: " + KJsonUtil.toJson(roleList, false, true));
//
//        setRoles(KUserRole.toLong(roleList));
//
//        //logger.debug("addRoles: this.roles: " + getRoles());
//    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
    
    @Override
    public Long getAccountId() {
        return accountId;
    }
    
    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    
    @Override
    public Long getPhotoId() {
        return photoId;
    }
    
    @Override
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public Presence getPresence() {
        return presence;
    }

    @Override
    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    @Override
    public Long getPositionId() {
        return positionId;
    }

    @Override
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Override
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender; 
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String getLocale() {
        return locale;
    }

    @Override
    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getDeletedDate() {
        return deletedDate;
    }

    @Override
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public Date getLoginDate() {
        return loginDate;
    }

    @Override
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    @Override
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
