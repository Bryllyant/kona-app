package com.bryllyant.kona.app.service;

import com.bryllyant.kona.remote.service.KService;

public interface GeocodingService extends KService, KGeocodingService {
	public static final String SERVICE_PATH = "rpc/GeocodingService";

}
