package com.bryllyant.kona.app.web.service;

import com.bryllyant.kona.util.KFileUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class LandingPageResourceResolver extends PathResourceResolver{
    private static final Logger logger = LoggerFactory.getLogger(LandingPageResourceResolver.class);

    @Override
    protected boolean checkResource(Resource resource, Resource location) throws IOException {
        Boolean result = null;
        try {
            result = super.checkResource(resource, location);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw t;
        } finally {
            logger.debug("checkResource called for:\nresource: {}\nlocation: {}\nresult: {}", resource, location, result);
        }
        return result;
    }

    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {
        Resource result = null;

        // we do not handle the /hello or /hello/

        // when a request comes in as /hello/1234
        // resourcePath will be: 1234
        // location will hold configured temp dir: e.g. URL [file:/opt/tomcat/temp/landing-pages-334882952382199632]

        // Since we're serving an entire site under /hello/1234, we want to serve /hello/1234/index.html

        // index.html may then call relative resources such as /hello/1234/css/main.css

        // If the top level directory exists, then this site has already been downloaded and unpacked into this folder

        // If top level dir does not exist then we need to create it

        try {
            // get root path of resourcePath
            String rootPath = resourcePath;

            Path _resourcePath = Paths.get(resourcePath);

            if (_resourcePath.getRoot() != null) {
                // /hello or /hello/ was passed in so show error page:
                return super.getResource(resourcePath, location);
            }

            int nameCount = _resourcePath.getNameCount();

            if (_resourcePath.getNameCount() == 0) {
                // we should never get here since we should have at least one path name
                return super.getResource(resourcePath, location);
            }

            rootPath = _resourcePath.getName(0).toString();

            logger.debug("getResource: rootPath: {}", rootPath);


            String rootDir = location.getFile().getAbsolutePath() + "/" + rootPath;

            logger.debug("getResource: rootDir: {}", rootDir);

            File rootFolder = new File(rootDir);

            if (!rootFolder.exists()) {
                rootFolder.mkdirs();

                // TODO: download and unpack template
                String html = "<html><head></head><body><h1>TEST</h1></body></html>";

                Map<String,Object> config = new HashMap<>();
                config.put("apiBaseUrl", "http://dev.moodcast.cc/api/");
                config.put("apiClientId", "8389799ace254d62afc9e693f100ab7c");
                config.put("landingPageUid", rootPath);
                String jsonConfig = KJsonUtil.toJson(config);
                String jsText = "CONFIG = " + jsonConfig ;

                Document doc = Jsoup.parse(html);

                Element head = doc.head();

                head.appendElement("script")
                    .attr("type","text/javascript")
                    .appendChild(new DataNode(jsText,""));

                html = doc.toString();

                KFileUtil.writeFile(rootDir + "/index.html", html);
            }

            if (_resourcePath.getNameCount() == 1) {
                // we requested /hello/1234 but we need to return /hello/1234/index.html
                resourcePath = _resourcePath.resolve("index.html").toString();
            }

            logger.debug("getResource: result resourcePath: {}", resourcePath);

            Resource resource = location.createRelative(resourcePath);

            logger.debug("getResource: result resource: {}", resource);

            if (!resource.exists()) {
                logger.debug("getResource: resource does not exist resource: {}", resource);
            } else {
                logger.debug("getResource: resource exists! resource: {}", resource);
            }

            if (!resource.isReadable()) {
                logger.debug("getResource: resource is not readable:  resource: {}", resource);
            } else {
                logger.debug("getResource: resource is readable! resource: {}", resource);
            }


            result = super.getResource(resourcePath, location);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw t;
        } finally {
            logger.debug("getResource called for:\nresourcePath: {}\nlocation: {}\nresult: {}", resourcePath, location, result);
        }
        return result;
    }

    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request, String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {
        Resource result = null;
        try {
            result = super.resolveResourceInternal(request, requestPath, locations, chain);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw t;
        } finally {
            logger.debug("resolveResourceInternal called for:\nrequestPath: {}\n result: {}", requestPath, result);
        }
        return result;
    }

    @Override
    protected String resolveUrlPathInternal(String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {
        String result = null;
        try {
            result = super.resolveUrlPathInternal(resourcePath, locations, chain);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw t;
        } finally {
            logger.debug("resolveUrlPathInternal called for:\nresourcePath: {}\n result: {}", resourcePath, result);
        }

        return result;
    }
}
