package com.bryllyant.kona.web.service;

import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageModel;
import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageParamModel;
import com.bryllyant.kona.api.service.LandingPageModelService;
import com.bryllyant.kona.api.service.LandingPageParamModelService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.app.service.LandingPageTemplateService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.encryption.KEncryptUtil;
import com.bryllyant.kona.encryption.KZipUtil;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.util.KFileUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LandingPageResolver extends PathResourceResolver{
    private static final Logger logger = LoggerFactory.getLogger(LandingPageResolver.class);

    @Autowired
    private KConfig config;

    @Autowired
    private LandingPageService landingPageService;

    @Autowired
    private LandingPageParamService landingPageParamService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private LandingPageParamModelService landingPageParamModelService;

    @Autowired
    private LandingPageTemplateService landingPageTemplateService;

    @Autowired
    private AppUtil util;



    @Autowired
    private FileService fileService;

    @Autowired
    private AppService appService;

    @Autowired
    private AppCredsService appCredsService;

    private static String appClientId = null;


    protected abstract String getBaseUrlPath();

    protected abstract LandingPage getLandingPage(String baseResourcePath);

    protected String getAppClientId() {
        if (appClientId == null) {
            App app = appService.getSystemApp();
            List<AppCreds> creds = appCredsService.fetchByAppId(app.getId());
            appClientId = creds.get(0).getClientId();
        }

        return appClientId;
    }

    protected String getChecksum(String baseResourcePath) {
        try {
//            LandingPage page = getLandingPage(baseResourcePath);
//            LandingPageTemplate template = landingPageTemplateService.fetchById(page.getTemplateId());
//            List paramList = landingPageParamService.fetchByLandingPageId(page.getId());
//            String s = page.toJSON() + template.toJSON() + KJsonUtil.toJson(paramList);

            Map<String, Object> config = getPageConfig(null, baseResourcePath);

            return KEncryptUtil.MD5(KJsonUtil.toJson(config));
        } catch (Throwable t) {
            throw new SystemException(t);
        }
    }

    protected Map<String, Object> getPageConfig(LandingPage page, String baseResourcePath) {
        String apiBaseUrl = config.getString("system.api.baseUrl");

        if (page == null) {
            page = getLandingPage(baseResourcePath);
        }

        LandingPageModel landingPageModel = landingPageModelService.toModel(page);

        List paramList = landingPageParamService.fetchByLandingPageId(page.getId());

        List<LandingPageParamModel> params = landingPageParamModelService.toModelList(paramList);

        Map<String, LandingPageParamModel> paramMap = new HashMap<>();

        for (LandingPageParamModel param : params) {
            paramMap.put(param.getTemplateParam().getName(), param);
        }

        Map<String, Object> api = new HashMap<>();
        api.put("baseUrl", apiBaseUrl);
        api.put("clientId", getAppClientId());

        Map<String, Object> config = new HashMap<>();

        config.put("api", api);
        config.put("landingpage", landingPageModel);
        config.put("params", paramMap);

        return config;
    }

    protected boolean deployTemplate(File rootFolder, String baseResourcePath) {
        try {
            LandingPage page = getLandingPage(baseResourcePath);

            if (page == null || page.getTemplateId() == null) {
                logger.info("Landing page not found for url path: {}", baseResourcePath);
                return false;
            }

            String rootDir = rootFolder.getAbsolutePath();

            LandingPageTemplate template = landingPageTemplateService.fetchById(page.getTemplateId());

            if (template == null) {
                logger.error("Landing page template not found for uid: {}", page.getUid());
                return false;
            }

            rootFolder.mkdirs();

            com.bryllyant.kona.app.entity.File file = fileService.fetchById(template.getFileId(), true);

            KZipUtil.unzip(file.getData(), rootDir);

            // at this point we should have:   rootDir + "/index.html"
            File htmlFile = new File(rootDir + "/index.html");

            if (!htmlFile.exists()) {
                logger.error("Template does not include index.html. Aborting.");
                return false;
            }

            String html = KFileUtil.readFile(htmlFile);

            Map<String, Object> config = getPageConfig(page, baseResourcePath);

            String jsText = "CONFIG = " + KJsonUtil.toJson(config);

            Document doc = Jsoup.parse(html);

            Element head = doc.head();

            head.appendElement("script")
                    .attr("type", "text/javascript")
                    .appendChild(new DataNode(jsText));

            html = doc.toString();

            KFileUtil.writeFile(rootDir + "/index.html", html);

            KFileUtil.writeFile(rootDir + "/.checksum", getChecksum(baseResourcePath));

            return true;
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            return false;
        }
    }


    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {

        // we do not handle the /hello or /hello/

        // when a request comes in as /hello/1234
        // resourcePath will be: 1234
        // location will hold configured temp dir: e.g. URL [file:/opt/tomcat/temp/landing-pages-334882952382199632]

        // Since we're serving an entire site under /hello/1234, we want to serve /hello/1234/index.html

        // index.html may then call relative resources such as /hello/1234/css/main.css

        // If the top level directory exists, then this site has already been downloaded and unpacked into this folder

        // If top level dir does not exist then we need to create it

        logger.debug("getResource: resourcePath: {}   location: {}",resourcePath, location);

        Path _resourcePath = Paths.get(resourcePath);

        if (_resourcePath.getRoot() != null) {
            // /hello or /hello/ was passed in so show error page:
            return super.getResource(resourcePath, location);
        }

        int nameCount = _resourcePath.getNameCount();

        logger.debug("getResource: resourcePath nameCount: {}", nameCount);

        if (_resourcePath.getNameCount() == 0) {
            // we should never get here since we should have at least one path name
            return super.getResource(resourcePath, location);
        }

        String baseResourcePath = _resourcePath.getName(0).toString();

        logger.debug("getResource: baseResourcePath: {}", baseResourcePath);

        String rootDir = location.getFile().getAbsolutePath() + "/" + baseResourcePath;

        logger.debug("getResource: rootDir: {}", rootDir);

        File rootFolder = new File(rootDir);

        //|| (location.getFile().getAbsolutePath().contains(landingPagePreviewTmpDirPrefix)) && nameCount == 1) {

        if (!rootFolder.exists()) {
            if (!deployTemplate(rootFolder, baseResourcePath)) {
                return null;
            }
        }

        // if the folder exists, make sure its contents don't need to be refreshed

        if (nameCount == 1) {
            File checksumFile = new File(rootDir + "/.checksum");

            boolean checksumMatch = false;

            if (checksumFile.exists()) {
                String checksum = KFileUtil.readFile(checksumFile);
                logger.debug("existing path checksum: [{}]", checksum);

                String currentChecksum = getChecksum(baseResourcePath);
                logger.debug("current checksum: [{}]", currentChecksum);

                if (checksum.equalsIgnoreCase(currentChecksum)) {
                    checksumMatch = true;
                }

                logger.debug("checksum: match: [{}]", checksumMatch);
            }

            if (!checksumMatch) {
                KFileUtil.deleteFolderRecursive(rootFolder);

                if (!deployTemplate(rootFolder, baseResourcePath)) {
                    return null;
                }
            }
        }

        if (_resourcePath.getNameCount() == 1) {
            // we requested /hello/1234 but we need to return /hello/1234/index.html
            resourcePath = _resourcePath.resolve("index.html").toString();
        }

        logger.debug("getResource: result resourcePath: {}", resourcePath);

//            Resource resource = location.createRelative(resourcePath);
//
//            logger.debug("getResource: result resource: {}", resource);
//
//            if (!resource.exists()) {
//                logger.debug("getResource: resource does not exist resource: {}", resource);
//            } else {
//                logger.debug("getResource: resource exists! resource: {}", resource);
//            }
//
//            if (!resource.isReadable()) {
//                logger.debug("getResource: resource is not readable:  resource: {}", resource);
//            } else {
//                logger.debug("getResource: resource is readable! resource: {}", resource);
//            }


        return super.getResource(resourcePath, location);
    }


    @Override
    protected Resource resolveResourceInternal(
            HttpServletRequest request,
            String requestPath,
            List<? extends Resource> locations,
            ResourceResolverChain chain
    ) {
        logger.debug(
                "resolveResourceInternal: requestPath: {}"
                        + "\nrequest: {}"
                        + "\npathInfo: {}"
                        + "\nrequestURL: {}"
                , requestPath
                , request
                , request.getPathInfo()
                , request.getRequestURL().toString()
        );

        String urlPath = getBaseUrlPath();

        String pathInfo = request.getPathInfo();

        if (!pathInfo.startsWith(urlPath)) {
            logger.info("Aborting: Unknown pathInfo: " + pathInfo);
            return null;
        }

        pathInfo = pathInfo.substring(urlPath.length() + 1);

        logger.debug("resolveResourceInternal: rawRequestPath: {}", pathInfo);

        String[] pathParts = pathInfo.split("/");

        if (pathParts.length == 0) {
            logger.info("Aborting: Landing Page identifier must be specified");
            return null;
        }

        if (pathParts.length == 1 && !pathInfo.endsWith("/")) {
            logger.debug("resolveResourceInternal: requestPath must end with '/' to serve index.html");
            return null;
        }

        return super.resolveResourceInternal(request, requestPath, locations, chain);
    }


}
