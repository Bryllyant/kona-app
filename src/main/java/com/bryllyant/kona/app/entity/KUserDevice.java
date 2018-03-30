package com.bryllyant.kona.app.entity;

/**
 * Core UserDevice object for framework interaction.
 */
    
public interface KUserDevice extends KDevice {

    /** 
     * UserDevice Id
     */
    public Long getId(); 
    public void setId(Long id);

    public Long getUserId(); 
    public void setUserId(Long userId);

    public Long getDeviceId(); 
    public void setDeviceId(Long deviceId);

    public String getName(); 
    public void setName(String name);

    public String getSlug();
    public void setSlug(String slug);
    

}
