package com.bryllyant.kona.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.bryllyant.kona.api.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller		
@RequestMapping("/router")
public class RouterController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(RouterController.class);

	   @Autowired
	    private Environment env;


    
	@RequestMapping(method=RequestMethod.GET)
	public final String routeDefault(HttpServletRequest req) {
        
		String defaultKeyword = env.getProperty("router.defaultKeyword");
        logger.debug("routeDefault: defaultKeyword: {}", defaultKeyword);
        return routeKeyword(req, defaultKeyword);
	}
    


	
	@RequestMapping(value="/{keyword}", method=RequestMethod.GET)
	public final String routeKeyword(HttpServletRequest req,
			@PathVariable String keyword) {
		
		if (keyword.startsWith("index")) {
			keyword = env.getProperty("router.defaultKeyword");
		}

        logger.debug("routeKeyword: keyword: {}", keyword);
        
        //return "redirect:/app/#/?keyword=" + keyword;
        return "redirect:/app?keyword=" + keyword;
	}
    

    
}
