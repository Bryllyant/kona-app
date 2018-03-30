package com.bryllyant.kona.app.entity;

public class KBaseUserDevice extends KBaseDevice implements KUserDevice {
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;
    private Long deviceId;

	private String name;
	private String slug;

    @Override
	public Long getId() {
		return id;
	}

    @Override
	public void setId(Long id) {
		this.id = id;
	}

    @Override
	public Long getUserId() {
		return userId;
	}

    @Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}

    @Override
	public Long getDeviceId() {
		return deviceId;
	}

    @Override
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

    @Override
	public String getName() {
		return name;
	}

    @Override
	public void setName(String name) {
		this.name = name;
	}

    @Override
	public String getSlug() {
		return slug;
	}

    @Override
	public void setSlug(String slug) {
		this.slug = slug;
	}
    
}
