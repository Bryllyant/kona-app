package com.bryllyant.kona.app;

import com.bryllyant.kona.app.web.service.LandingPageResourceResolver;
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
    LandingPageResourceResolver landingPageResourceResolver;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path tempPath = null;

        try {
            tempPath = Files.createTempDirectory("landing-pages-");
        } catch (Throwable t) {
            throw new RuntimeException("Unable to create temporary directory");
        }

        // make sure to end temp directory with '/'
        String landingPagesFileUrl = "file://" + tempPath.toAbsolutePath().toString() + "/";

        logger.debug("App: landingPagesFileUrl: {}", landingPagesFileUrl);

        registry
                // make sure to following to spring-security.xml:  <http pattern="/hello/**" security="none" />
                //.addResourceHandler("/hello", "/hello/", "/hello/**")
                .addResourceHandler("/hello/**")
                .addResourceLocations(landingPagesFileUrl)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(landingPageResourceResolver);
    }



    @PostConstruct
    public void init() {
        logger.debug("App class loaded.");
    }


    public static void main(String[] args) throws Exception {
    }
}