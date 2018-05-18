/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface ShortUrlService extends KService, KEntityService<ShortUrl> {
	String SERVICE_PATH = "rpc/ShortUrlService";

    ShortUrl fetchByPath(String path);

    ShortUrl fetchByShortUrl(String shortUrl);

    List<ShortUrl> fetchByLongUrl(String longUrl);

    List<ShortUrl> fetchByUserId(Long userId);


    String createChannelRedirectShortUrl(Long campaignId, Long groupId, Long channelId);

    String shorten(String url);

    String shorten(Long userId, String url);

    String shorten(
            Long userId,
            Long campaignId,
            Long groupId,
            Long channelId,
            Long targetId,
            Long replyId,
            Long replyMessageId,
            Long scriptId,
            String longUrl,
            String vanityDomain,
            String description,
            boolean singleMapped,
            boolean channelRedirect,
            boolean queryParamsEnabled,
            Date expirationDate
    );

    ShortUrl create(
            Long userId,
            Long campaignId,
            Long groupId,
            Long channelId,
            Long targetId,
            Long replyId,
            Long replyMessageId,
            Long scriptId,
            String longUrl,
            String vanityDomain,
            String description,
            boolean singleMapped,
            boolean channelRedirect,
            boolean queryParamsEnabled,
            Date expirationDate
    );

    String explode(HttpServletRequest req, String url);

    String explode(HttpServletRequest req, ShortUrl shortUrl);

    String createAppStoreShortUrl(String appStoreUrl, String googlePlayUrl, String defaultUrl);
}
