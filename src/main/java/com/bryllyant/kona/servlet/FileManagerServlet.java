package com.bryllyant.kona.servlet;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.service.FileManager;
import com.bryllyant.kona.http.KForm;
import com.bryllyant.kona.http.KMultiPartFileSender;
import com.bryllyant.kona.http.KServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class FileManagerServlet extends HttpServlet {
	private static Logger logger = LoggerFactory.getLogger(FileManagerServlet.class);

	private WebApplicationContext context = null;
	private FileManager fileManager = null;
	private String clientIdHeaderKey = null;

	@Override
	public void init(ServletConfig servletConfig) {

		// first see if we have a fileManagerRef **SERVLET** param
		String fileManagerRef = 
				servletConfig.getInitParameter("fileManagerRef");

		clientIdHeaderKey = servletConfig.getInitParameter("clientIdHeaderKey");

		logger.debug("FileManagerServlet init(): fileManagerRef: " + fileManagerRef);

		context = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());

		if (context == null) {
			logger.debug("context is null; checking for contextConfigLocation ..."); 

			// see if we have a contextConfigLocation **CONTEXT** param
			String config = 
					servletConfig.getServletContext().getInitParameter("contextConfigLocation");

			if (config != null) {
				logger.debug("found config: " + config);
				XmlWebApplicationContext c = new XmlWebApplicationContext();
				c.setConfigLocation(config);
				c.afterPropertiesSet();
				context = c;
			} else {
				logger.debug("no context config location found");
			}

			if (context == null) {
				throw new IllegalStateException(
						"No Spring web application context found");
			}
		}

		fileManager = getFileManager(fileManagerRef);
		logger.debug("FileManagerServlet initialized");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		KForm form = new KForm(req);

		boolean attachment = form.getBoolean("force_attachment", false);

		String accessToken = KServletUtil.getAccessToken(req);
		String clientId = KServletUtil.getClientId(req, clientIdHeaderKey);

		String filePath = getFilePath(req);

		File file = null;

		try {
			file = fileManager.fetchFileByUrlPath(filePath);
		} catch (Exception e) {
			if (e.getMessage().contains("Is a directory")) {
				resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			} else {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			return;
		}

		if (!fileManager.isAuthorized(file, clientId, accessToken)) {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		if (file.getData() == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}


		/*
        boolean isMedia = false;
        if (file.getContentType() != null) {
        	isMedia = KMimeTypes.isMedia(file.getContentType());
        	if (!isMedia) {
        		isMedia = KImageUtil.isImage(file);
        	}
        }

        String filename = null;
        if (attachment || !isMedia) {
            filename = file.getName();
        }
		 */
        
		try {
			//KServletUtil.writeObject(resp, file.getContentType(),
			//    file.getData(), filename, true);


			KMultiPartFileSender.fromBytes(file.getData(), file.getContentType(), file.getName())
			.withRequest(req)
			.withResponse(resp)
			.serveResource();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		logger.debug("file download request completed: " + filePath);
	}


	/**
	 * Determine Spring bean to handle request based on request URL, e.g. a
	 * request ending in /myService will be handled by bean with name
	 * "myService".
	 * 
	 * @param
	 * @return handler bean
	 */
	@SuppressWarnings("unchecked")
	protected FileManager getFileManager(String fileManagerRef) {
		if (fileManagerRef == null) {
		    throw new IllegalArgumentException("fileManagerRef is null");
		}

		Object bean = getBean(fileManagerRef);

		if (bean == null) {
			bean = getBean("fileManager");
		}

		if (bean == null) {
			throw new IllegalArgumentException(
					"Spring bean for FileManager not found.");
		}

		if (!(bean instanceof FileManager)) {
			throw new IllegalArgumentException(
					"Spring bean is not a KFileManager: (" + bean + ")");
		}

		logger.debug("Found KFileManager bean: " + bean);

		return (FileManager) bean;
	}



	/**
	 * Parse the service name from the request URL.
	 * 
	 * @param request
	 * @return bean name
	 */
	protected String getFilePath(HttpServletRequest request) {
		String url = request.getRequestURI();
		//String service = url.substring(url.lastIndexOf("/") + 1);

		String path = request.getPathInfo();
		if (path.startsWith("/")) {
			path = path.substring(1);
		}

		logger.debug("File Path for URL [" + url + "] is: " + path);
		return (path);
	}

	/**
	 * Look up a spring bean with the specified name in the current web
	 * application context.
	 * 
	 * @param name
	 *            bean name
	 * @return the bean
	 */
	protected Object getBean(String name) {
		if (!context.containsBean(name)) {
			throw new IllegalArgumentException("Bean not found: " + name);
		}

		return context.getBean(name);
	}


	/*
    protected String getAccessToken() {
        String accessToken = null;
    	Object obj = SecurityContextHolder.getContext().getAuthentication();

    	if (obj != null) {

    		logger.debug("found user authentication object; checking principal");

    		Object principal = SecurityContextHolder
    				.getContext()
    				.getAuthentication()
    				.getPrincipal();

    		if (principal != null) {
    			logger.debug("found principal object: " + principal);

    			if (principal instanceof UserDetails) {
    				UserDetails details = (UserDetails) principal;
    				accessToken = details.getUsername();
    			} else if (principal instanceof String) {
    				accessToken = principal.toString();
    			}
    		}
    		else {
    			logger.debug("principal object NOT found");
    		}
    	}
    	logger.debug("SecurityContext accessToken: " + accessToken);
        return accessToken;
    }
	 */
}
