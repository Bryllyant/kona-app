package com.bryllyant.kona.app;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.web.service.CampaignLandingPageResolver;
import com.bryllyant.kona.app.web.service.PreviewLandingPageResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;


@Configuration
@EnableWebMvc
public class App extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    KConfig config;

    @Autowired
    CampaignLandingPageResolver campaignLandingPageResolver;

    @Autowired
    PreviewLandingPageResolver previewLandingPageResolver;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String landingPageUrlPath = config.getString("landingPage.urlPath");
        String landingPageUrlPathPattern = landingPageUrlPath + "/**";

        String previewLandingPageUrlPath = config.getString("landingPage.previewUrlPath");
        String previewLandingPageUrlPathPattern = previewLandingPageUrlPath + "/**";

        logger.debug("addResourceHandlers: setting landingPageUrlPathPattern: " + landingPageUrlPathPattern);


        try {
            String prefix = config.getString("landingPage.tmpDirPrefix");
            Path tempPath = Files.createTempDirectory(prefix);

            // make sure to end temp directory with '/'
            String landingPagesFileUrl = "file://" + tempPath.toAbsolutePath().toString() + "/";

            logger.debug("App: landingPagesFileUrl: {}", landingPagesFileUrl);

            prefix = config.getString("landingPage.previewTmpDirPrefix");
            tempPath = Files.createTempDirectory(prefix);

            String previewLandingPagesFileUrl = "file://" + tempPath.toAbsolutePath().toString() + "/";

            logger.debug("App: previewLandingPagesFileUrl: {}", landingPagesFileUrl);


//            registry
//                // make sure to following to spring-security.xml:  <http pattern="/hello/**" security="none" />
//                //.addResourceHandler("/hello", "/hello/", "/hello/**")
//                .addResourceHandler(landingPageUrlPathPattern, previewLandingPageUrlPathPattern)
//                .addResourceLocations(landingPagesFileUrl, previewLandingPagesFileUrl)
//                //.setCachePeriod(3600)
//                .setCachePeriod(0)
//                .resourceChain(false )
//                .addResolver(landingPageResolver);


            registry
                    // make sure to following to spring-security.xml:  <http pattern="/hello/**" security="none" />
                    .addResourceHandler(landingPageUrlPathPattern)
                    .addResourceLocations(landingPagesFileUrl)
                    //.setCachePeriod(3600) // seconds
                    .setCachePeriod(30) // seconds
                    //.resourceChain(true )
                    .resourceChain(false ) // if set to true then resolver only called if content changed
                    .addResolver(campaignLandingPageResolver);

            registry
                    // make sure to following to spring-security.xml:  <http pattern="/hello/**" security="none" />
                    //.addResourceHandler("/hello", "/hello/", "/hello/**")
                    .addResourceHandler(previewLandingPageUrlPathPattern)
                    .addResourceLocations(previewLandingPagesFileUrl)
                    .setCachePeriod(0)
                    .resourceChain(false )
                    .addResolver(previewLandingPageResolver);

        } catch (Throwable t) {
            throw new RuntimeException("Unable to create temporary directory");
        }

    }



    @PostConstruct
    public void init() {
        logger.debug("App class loaded.");
    }


    public static void main(String[] args) throws Exception {
    }
}