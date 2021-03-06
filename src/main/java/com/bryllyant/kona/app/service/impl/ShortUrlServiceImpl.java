/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ShortUrlMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.entity.ShortUrlExample;
import com.bryllyant.kona.app.service.CampaignAnalyticsService;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.app.service.ScriptService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.sequence.flake.KFlake;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KPassGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(ShortUrlService.SERVICE_PATH)
public class ShortUrlServiceImpl
        extends KAbstractService<ShortUrl,ShortUrlExample,ShortUrlMapper>
        implements ShortUrlService {

    private static Logger logger = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

    @Autowired
    private ShortUrlMapper shortUrlMapper;

    @Autowired
    private KConfig config;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private CampaignAnalyticsService campaignAnalyticsService;

    @Autowired
    private ScriptService scriptService;

    @Override @SuppressWarnings("unchecked")
    protected ShortUrlMapper getMapper() {
        return shortUrlMapper;
    }

    protected String getDefaultVanityDomain() {
        return config.getString("shortUrl.domain");
    }

    protected boolean useHttps() {
        return config.getBoolean("shortUrl.https", false);
    }

    protected String getLandingPageBaseUrl() {
        return config.getString("landingPage.baseUrl");
    }
    
    
    
        /*
    EXAMPLE short url script

    if (_req != null) {
		ua = _req.getHeader('User-Agent');
		if (ua.contains('iPhone') || ua.contains('iPod') || ua.contains('iPad')) {
			return 'https://itunes.apple.com/us/app/<app-name>/<app-id>?mt=8';
		} else if (ua.contains('Android')) {
			return 'https://play.google.com/store/apps/details?id=<app-package>';
		}
    }
    return 'https://<website-url>/#/download'
     */

    @Override
    public void validate(ShortUrl shortUrl) {
        if (shortUrl.getCreatedDate() == null) {
            shortUrl.setCreatedDate(new Date());
        }

        shortUrl.setUpdatedDate(new Date());

        if (shortUrl.getUid() == null) {
            shortUrl.setUid(uuid());
        }
    }


    @Override
    public ShortUrl fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public ShortUrl fetchByShortUrl(String shortUrl) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("shortUrl", shortUrl);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public ShortUrl fetchByPath(String path) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("path", path);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<ShortUrl> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0,9999, null, filter,  false);
    }

    @Override
    public List<ShortUrl> fetchByLongUrl(String longUrl) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("longUrl", longUrl);
        return fetchByCriteria(0,9999, null, filter,  false);
    }


    @Override
    public String createChannelRedirectShortUrl(Long campaignId, Long groupId, Long channelId) {
        return shorten(
                null,
                campaignId,
                groupId,
                channelId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                false,
                true,
                true,
                null
        );
    }

    @Override
    public String shorten(String longUrl) {
        return shorten(null, longUrl);
    }

    @Override
    public String shorten(Long userId, String longUrl) {
        return shorten(
                userId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                longUrl,
                null,
                null,
                false,
                false,
                false,
                null
        );
    }


    @Override
    public String shorten(
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
    ) {
        ShortUrl shortUrl = create(
            userId,
            campaignId,
            groupId,
            channelId,
            targetId,
            replyId,
            replyMessageId,
            scriptId,
            longUrl,
            vanityDomain,
            description,
            singleMapped,
            channelRedirect,
            queryParamsEnabled,
            expirationDate
        );

        return shortUrl.getShortUrl();
    }

    @Override
    public ShortUrl create(
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
    ){
        if (vanityDomain == null) {
            vanityDomain = getDefaultVanityDomain();
        }

        ShortUrl shortUrl = null;

        if (longUrl != null && singleMapped) {
            List<ShortUrl> shortUrlList = fetchByLongUrl(longUrl);

            if (shortUrlList != null) {
                if (shortUrlList.size()>1) {
                    throw new IllegalStateException(
                            "Multiple ShortUrl mappings exist and singleMapping enforced for url: " + longUrl
                    );
                }

                if (shortUrlList.size() == 1) {
                    shortUrl = shortUrlList.get(0);

                    if (shortUrl != null && !shortUrl.getDomain().equals(vanityDomain)) {
                        throw new IllegalStateException("ShortUrl exists for requested longUrl but for different domain: "
                                + "\nlongUrl: " + longUrl
                                + "\nvanityDomain: " + vanityDomain
                                + "\nShortUrl: " + KJsonUtil.toJson(shortUrl)
                        );
                    }
                }
            }
        }

        if (shortUrl == null) {
            Date now = new Date();

            // use the following apache directive to match this shorturl
            // ProxyPassMatch ^/(0[A-Z0-9]*)$ ajp://localhost:8009/redirect/$1

            //Long pathId = sequence.getNextNo("shortUrl.path");
            //String path = "0" + Long.toString(pathId, 36).toUpperCase();

            String path = generatePath();

            if (path == null) {
                throw new RuntimeException("Error generating random path for short url.");
            }

            String surl = "http://";
            if (useHttps()) {
                surl= "https://";
            }

            surl += vanityDomain + "/" + path;

            shortUrl = getEntityObject();

            shortUrl.setUserId(userId);
            shortUrl.setCampaignId(campaignId);
            shortUrl.setGroupId(groupId);
            shortUrl.setChannelId(channelId);
            shortUrl.setTargetId(targetId);
            shortUrl.setReplyId(replyId);
            shortUrl.setReplyMessageId(replyMessageId);
            shortUrl.setScriptId(scriptId);
            shortUrl.setDomain(vanityDomain);
            shortUrl.setPath(path);
            shortUrl.setShortUrl(surl);
            shortUrl.setLongUrl(longUrl);
            shortUrl.setDescription(description);
            shortUrl.setEnabled(true);
            shortUrl.setSingleMapped(singleMapped);
            shortUrl.setChannelRedirect(channelRedirect);
            shortUrl.setQueryParamsEnabled(queryParamsEnabled);
            shortUrl.setExpirationDate(expirationDate);

            shortUrl = add(shortUrl);
        }

        return shortUrl;
    }



    private String generatePath() {
        KPassGen pass = new KPassGen(KPassGen.LOWERCASE_LETTERS_AND_NUMBERS_ALPHABET);
        int i=0;
        String path = null;
        while (i<10) {
            path = "0" + pass.getPass(5);
            ShortUrl s = fetchByPath(path);
            if (s == null)  break;
            path = null;
            i += 1;
        }

        if (path == null) {
            //Long pathId = sequence.getNextNo("shortUrl.path");
            Long pathId = KFlake.getIdAsLong();
            path = "0" + Long.toString(pathId, 36).toUpperCase();
            ShortUrl s = fetchByPath(path);
            if (s != null)  path = null;
        }

        return path;
    }



    @Override
    public String explode(HttpServletRequest req, String url) {

        ShortUrl shortUrl;

        try {
            URL u = new URL(url);
            shortUrl = fetchByShortUrl(url);
        } catch (MalformedURLException e) {
            shortUrl = fetchByPath(url);
        }

        return explode(req, shortUrl);
    }


    protected KServiceClient getServiceClient(HttpServletRequest req) {
        KServiceClient client = new KServiceClient();
        client.setHostname(KServletUtil.getClientHostname(req));
        client.setUserAgent(KServletUtil.getClientUserAgent(req));
        client.setLatitude(KServletUtil.getClientLatitude(req));
        client.setLongitude(KServletUtil.getClientLongitude(req));
        client.setReferrerUrl(KServletUtil.getClientReferer(req));
        client.setRequestUrl(KServletUtil.getFullRequestURL(req));

        return client;
    }

    protected String getChannelRedirectUrl(HttpServletRequest req, ShortUrl shortUrl) {
        if (!shortUrl.isChannelRedirect()) {
            logger.info("ShortUrl is not a campaign channel redirect");
            return null;
        }

        Campaign campaign = campaignService.fetchById(shortUrl.getCampaignId());
        CampaignChannel channel = campaignChannelService.fetchById(shortUrl.getChannelId());

        CampaignTarget campaignTarget = campaignChannelService.nextCampaignTarget(channel);

        if (campaignTarget == null) {
            logger.info("No campaign targets for channel redirect");
            return null;
        }

        KServiceClient client = getServiceClient(req);

        boolean conversionEvent = false;

        if (campaign.getKpi() == Campaign.KPI.WEBSITE_VISIT) {
            conversionEvent = true;
        }

        // FIXME: this should be encapsulated in CampaignAnalyticsService. don't hardcode values here.
        CampaignAnalytics analytics = campaignAnalyticsService.create(
                client,
                campaignTarget.getId(),
                "screen-view",
                "engagement",
                campaignTarget.getUrl(),
                null,
                conversionEvent,
                null,
                null,
                null
        );

        return campaignTarget.getUrl();

//	    List<CampaignTarget> targets = getCampaignTargetService().fetchByChannelId(shortUrl.getChannelId());
//
//	    if (targets == null || targets.size() == 0) {
//	        logger.info("No campaign targets for channel redirect");
//	        return null;
//        }
//
//	    CampaignTarget campaignTarget = null;
//
//	    switch (channel.getTargetStrategy()) {
//            case ROUND_ROBIN:
//                Integer nextIndex = null;
//
//                if (lastTargetIndex == null) {
//                    nextIndex = 0;
//                } else {
//                    nextIndex = (lastTargetIndex + 1) % targets.size();
//                }
//
//                campaignTarget = targets.get(nextIndex);
//                break;
//
//            case RANDOM:
//                nextIndex = KRandomUtil.getRandomInt(0, targets.size() - 1);
//                campaignTarget = targets.get(nextIndex);
//
//            default:
//            case MAX_CONVERSIONS:
//                nextIndex = KRandomUtil.getRandomInt(0, targets.size() - 1);
//                campaignTarget = targets.get(nextIndex);
//        }
//
//        return campaignTarget.getUrl();
    }

    @Override
    public String explode(HttpServletRequest req, ShortUrl shortUrl) {
        String longUrl;

        if (shortUrl == null) {
            logger.info("ShortUrl is null");
            return null;
        }

        if (!shortUrl.isEnabled()) {
            logger.info("ShortUrl is disabled: " + shortUrl);
            return null;
        }

        Long now = new Date().getTime();

        // FIXME: return a 404 response?
        if (shortUrl.getExpirationDate() != null && shortUrl.getExpirationDate().getTime() <= now) {
            logger.info("ShortUrl has expired: " + shortUrl);
            return null;
        }

        if (shortUrl.isChannelRedirect()) {
            longUrl = getChannelRedirectUrl(req, shortUrl);
        } else if (shortUrl.getScriptId() != null) {
            longUrl = evalScript(req, shortUrl);
        } else {
            longUrl = shortUrl.getLongUrl();
        }

        // see if there are params that need to be passed on to the long url

        logger.debug("explode: pre-query-param longUrl: " + longUrl);

        logger.debug("explode: isQueryParamsEnabled: " + shortUrl.isQueryParamsEnabled());

        if (shortUrl.isQueryParamsEnabled()) {
            Map<String,String[]> map = req.getParameterMap();

            logger.debug("explode: req.parameterMap: " + KJsonUtil.toJson(map));

            URI uri = URI.create(longUrl);

            UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri);

            for (String key : map.keySet()) {

                String[] values = map.get(key);


                if (values != null) {
                    for (String value : values) {
                        logger.debug("explode: key: {}   value: {}", key, value);

                        if (value != null) {

                            // since fragments won't be passed to the shortUrl they must be sent as a special query param
                            // e.g. https://short.url/01234?_f=hello -> https://long.url/abc/def#hello
                            // the last fragment "wins"
                            if (key.equals("_f")) {
                                builder = builder.fragment(encode(value));
                            } else {
                                builder = builder.queryParam(key, encode(value));
                            }
                        }
                    }
                }
            }

            URI requestUri = URI.create(req.getRequestURL().toString());

            String requestPath = requestUri.getPath();

            if (requestPath != null) {
                logger.debug("explode: request requestPath: " + requestPath);

                Integer index = requestPath.indexOf(shortUrl.getPath()) + shortUrl.getPath().length();

                requestPath = requestPath.substring(index);

                logger.debug("explode: request requestPath 2: " + requestPath);

                if (requestPath.length() > 0 && requestPath.startsWith("/")) {
                    requestPath = requestPath.substring(1);
                }

                logger.debug("explode: request requestPath 3: " + requestPath);

                if (requestPath.length() > 0) {
                    builder = builder.path(requestPath);
                }
            }


            longUrl = builder.build().toUriString();

            logger.debug("explode: post-query-param longUrl: " + longUrl);
        }

        return longUrl;
    }

    @Override
    public String createAppStoreShortUrl(String appStoreUrl, String googlePlayUrl, String defaultUrl) {

        String _script = "if (req != null) {"
            + "\n   ua = req.getHeader('User-Agent');"
            + "\n   if (ua.contains('iPhone') || ua.contains('iPod') || ua.contains('iPad')) {"
            + "\n       return '" + appStoreUrl + "';"
            + "\n   } else if (ua.contains('Android')) {"
            + "\n       return '" + googlePlayUrl + "';"
		    + "\n   }"
            + "\n}"
            + "\nreturn '" + defaultUrl + "'";


        Script script = scriptService.create(
                "app-store-url-" + uuid(),
                _script,
                Script.Language.GROOVY,
                Script.ReturnType.STRING
        );

        return shorten(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                script.getId(),
                null,
                null,
                null,
                false,
                false,
                true,
                null
        );
    }

    private String encode(String term) {
        if (term == null) {
            return null;
        }

        try {
            return URLEncoder.encode(term, "UTF-8");
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t.getMessage(), t);
        }
    }

    protected String evalScript(HttpServletRequest req, ShortUrl shortUrl) {
        Script script = scriptService.fetchById(shortUrl.getScriptId());

        if (script == null) {
            logger.info("ShortUrl script is null");
            return null;
        }

        try {
            Bindings bindings = new SimpleBindings();
            bindings.put("req", req);
            bindings.put("shortUrl", shortUrl);
            String value = (String) scriptService.eval(script, bindings);
            return value;
        } catch (Throwable t) {
            throw new KServiceException(t.getMessage(), t);
        }
    }

//	private String evalScript(HttpServletRequest req, String script) {
//		Binding binding = new Binding();
//
//		GroovyShell shell = new GroovyShell(binding);
//
//		binding.setVariable("_req", req);
//
//        String result = null;
//
//		Object value = shell.evaluate(script);
//
//        if (value != null) {
//        	result = value.toString();
//        }
//
//        return result;
//	}
}
