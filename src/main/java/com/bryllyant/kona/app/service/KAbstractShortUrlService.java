/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaign;
import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.app.entity.KScript;
import com.bryllyant.kona.app.entity.KShortUrl;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.sequence.flake.KFlake;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KPassGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractShortUrlService<
        SHORT_URL extends KShortUrl,
        SHORT_URL_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<SHORT_URL, SHORT_URL_EXAMPLE>,
        SCRIPT extends KScript,
        CAMPAIGN extends KCampaign,
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN_CHANNEL extends KCampaignChannel,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_REPLY extends KCampaignReply>
        extends KAbstractService<SHORT_URL,SHORT_URL_EXAMPLE,MAPPER>
        implements KShortUrlService<SHORT_URL> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractShortUrlService.class);
	private Integer lastTargetIndex = null;

    protected abstract String getDefaultVanityDomain();
    protected abstract String getLandingPageBaseUrl();

    protected abstract <S extends KCampaignChannelService<CAMPAIGN_CHANNEL,CAMPAIGN_GROUP,CAMPAIGN_TARGET,CAMPAIGN_REPLY>> S getCampaignChannelService();
    protected abstract <S extends KCampaignTargetService<CAMPAIGN_TARGET,CAMPAIGN_CHANNEL>> S getCampaignTargetService();
    protected abstract <S extends KScriptService<SCRIPT>> S getScriptService();

    protected boolean useHttps() {
        return true;
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
	public void validate(SHORT_URL shortUrl) {
		if (shortUrl.getCreatedDate() == null) {
			shortUrl.setCreatedDate(new Date());
		}

        shortUrl.setUpdatedDate(new Date());

		if (shortUrl.getUid() == null) {
		    shortUrl.setUid(uuid());
        }
	}


    @Override
    public SHORT_URL fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

	@Override
	public SHORT_URL fetchByShortUrl(String shortUrl) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("shortUrl", shortUrl);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    

	@Override
	public SHORT_URL fetchByPath(String path) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("path", path);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

    @Override
    public List<SHORT_URL> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0,9999, null, filter,  false);
    }

	@Override
	public List<SHORT_URL> fetchByLongUrl(String longUrl) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("longUrl", longUrl);
        return fetchByCriteria(0,9999, null, filter,  false);
	}


    @Override
    public String getChannelRedirectShortUrl(Long campaignId,
                                      Long groupId,
                                      Long channelId) {
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
            Date expirationDate
    ) {
        
    	if (vanityDomain == null) {
    		vanityDomain = getDefaultVanityDomain();
    	}
    	
    	SHORT_URL shortUrl = null;
    	
    	if (longUrl != null && singleMapped) {
    		List<SHORT_URL> shortUrlList = fetchByLongUrl(longUrl);
            
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
        	shortUrl.setExpirationDate(expirationDate);

            shortUrl = add(shortUrl);
        }
        
        return shortUrl.getShortUrl();
	}
    

    
    private String generatePath() {
    	KPassGen pass = new KPassGen(KPassGen.LOWERCASE_LETTERS_AND_NUMBERS_ALPHABET);
        int i=0;
        String path = null;
        while (i<10) {
        	path = "0" + pass.getPass(5);
        	SHORT_URL s = fetchByPath(path);
            if (s == null)  break;
            path = null;
            i += 1;
        }
        
        if (path == null) {
            //Long pathId = sequence.getNextNo("shortUrl.path");
            Long pathId = KFlake.getIdAsLong();
            path = "0" + Long.toString(pathId, 36).toUpperCase();
        	SHORT_URL s = fetchByPath(path);
            if (s != null)  path = null;
        }
        
        return path;
    }
    

    
    @Override
    public String explode(HttpServletRequest req, String url) {

    	SHORT_URL shortUrl;

    	try {
    		URL u = new URL(url);
    		shortUrl = fetchByShortUrl(url);
    	} catch (MalformedURLException e) {
    		shortUrl = fetchByPath(url);
    	}

    	return explode(req, shortUrl);
    }
    

    protected String getChannelRedirectUrl(HttpServletRequest req, SHORT_URL shortUrl) {
	    if (!shortUrl.isChannelRedirect()) {
	        logger.info("ShortUrl is not a campaign channel redirect");
	        return null;
        }

	    CAMPAIGN_CHANNEL channel = getCampaignChannelService().fetchById(shortUrl.getChannelId());

        CAMPAIGN_TARGET campaignTarget = getCampaignChannelService().nextCampaignTarget(channel);

        if (campaignTarget == null) {
	        logger.info("No campaign targets for channel redirect");
	        return null;
        }

        return campaignTarget.getUrl();

//	    List<CAMPAIGN_TARGET> targets = getCampaignTargetService().fetchByChannelId(shortUrl.getChannelId());
//
//	    if (targets == null || targets.size() == 0) {
//	        logger.info("No campaign targets for channel redirect");
//	        return null;
//        }
//
//	    CAMPAIGN_TARGET campaignTarget = null;
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
    public String explode(HttpServletRequest req, SHORT_URL shortUrl) {
	    if (shortUrl == null) {
            logger.info("ShortUrl is null");
            return null;
        }

	    if (!shortUrl.isEnabled()) {
            logger.info("ShortUrl is disabled: " + shortUrl);
            return null;
        }

	    Long now = new Date().getTime();

	    if (shortUrl.getExpirationDate() != null && shortUrl.getExpirationDate().getTime() <= now) {
	        logger.info("ShortUrl has expired: " + shortUrl);
        }

	    if (shortUrl.isChannelRedirect()) {
	        return getChannelRedirectUrl(req, shortUrl);
        }

        if (shortUrl.getScriptId() != null) {
	        return evalScript(req, shortUrl);
        }

        return shortUrl.getLongUrl();
    }


    protected String evalScript(HttpServletRequest req, SHORT_URL shortUrl) {
        SCRIPT script = getScriptService().fetchById(shortUrl.getScriptId());

        if (script == null) {
            logger.info("ShortUrl script is null");
            return null;
        }

        try {
            Bindings bindings = new SimpleBindings();
            bindings.put("req", req);
            String value = (String) getScriptService().eval(script, bindings);
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
