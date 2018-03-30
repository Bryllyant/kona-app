/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KShortUrl;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface KShortUrlService<SHORT_URL extends KShortUrl>
        extends KService, KEntityService<SHORT_URL> {

    SHORT_URL fetchByUid(String uid);

    SHORT_URL fetchByPath(String path);

    SHORT_URL fetchByShortUrl(String shortUrl);

    List<SHORT_URL> fetchByLongUrl(String longUrl);

    List<SHORT_URL> fetchByUserId(Long userId);


    String getChannelRedirectShortUrl(Long campaignId,
                   Long groupId,
                   Long channelId);

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
            Date expirationDate
    );

    String explode(HttpServletRequest req, String url);

    String explode(HttpServletRequest req, SHORT_URL shortUrl);
}
