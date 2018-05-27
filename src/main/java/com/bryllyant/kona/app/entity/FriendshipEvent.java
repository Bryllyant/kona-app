package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseFriendshipEvent.Type;
import java.io.Serializable;
import java.util.Date;

public class FriendshipEvent extends BaseFriendshipEvent implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.type
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.friendship_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Long friendshipId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.user_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.friend_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Long friendId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.event
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String event;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.event_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date eventDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_event.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__friendship_event
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.id
     *
     * @return the value of kona__friendship_event.id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.id
     *
     * @param id the value for kona__friendship_event.id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.uid
     *
     * @return the value of kona__friendship_event.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.uid
     *
     * @param uid the value for kona__friendship_event.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.type
     *
     * @return the value of kona__friendship_event.type
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.type
     *
     * @param type the value for kona__friendship_event.type
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.friendship_id
     *
     * @return the value of kona__friendship_event.friendship_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Long getFriendshipId() {
        return friendshipId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.friendship_id
     *
     * @param friendshipId the value for kona__friendship_event.friendship_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.user_id
     *
     * @return the value of kona__friendship_event.user_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.user_id
     *
     * @param userId the value for kona__friendship_event.user_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.friend_id
     *
     * @return the value of kona__friendship_event.friend_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Long getFriendId() {
        return friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.friend_id
     *
     * @param friendId the value for kona__friendship_event.friend_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.event
     *
     * @return the value of kona__friendship_event.event
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getEvent() {
        return event;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.event
     *
     * @param event the value for kona__friendship_event.event
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.event_date
     *
     * @return the value of kona__friendship_event.event_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.event_date
     *
     * @param eventDate the value for kona__friendship_event.event_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.created_date
     *
     * @return the value of kona__friendship_event.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.created_date
     *
     * @param createdDate the value for kona__friendship_event.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_event.updated_date
     *
     * @return the value of kona__friendship_event.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_event.updated_date
     *
     * @param updatedDate the value for kona__friendship_event.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}