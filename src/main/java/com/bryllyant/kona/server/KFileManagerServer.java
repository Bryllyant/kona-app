package com.bryllyant.kona.server;

import com.bryllyant.kona.servlet.FileManagerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class KFileManagerServer {
    private static Logger logger = LoggerFactory.getLogger(KFileManagerServer.class);

    Server server = null;

    public KFileManagerServer(Integer port, String contextPath, 
            String springContextConfig, String fileManagerRef) {
        server = new Server(port);
        /*
        context = new Context(server, contextPath, Context.SESSIONS);

        ServletHolder servlet = new ServletHolder(FileManagerServlet.class);
        servlet.setInitParameter("fileManagerRef", fileManagerRef);

        //context.addServlet(FileManagerServlet.class, "/*");
        context.addServlet(servlet, "/*");

        HashMap<String,String> contextParams = new HashMap<String,String>();
        contextParams.put("contextConfigLocation", springContextConfig);
        context.setInitParams(contextParams);
        */

        ServletHolder servlet = new ServletHolder(FileManagerServlet.class);
        servlet.setInitParameter("fileManagerRef", fileManagerRef);
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(contextPath);
        //context.setResourceBase(System.getProperty("java.io.tmpdir"));
        context.addServlet(servlet, "/*");
        context.setInitParameter("contextConfigLocation", springContextConfig);

        server.setHandler(context);
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
    }


    /**
     * Starts Jetty web application server to handle download requests.
     * <p>
     * Requires 4 params:
     * </p>
     * <ol>
     *  <li>port (e.g. 8080)</li>
     *  <li>context path (e.g. /)</li>
     *  <li>URI of spring context file (e.g. classpath:spring-context.xml)</li>
     *  <li>Spring reference to Class that implements KFileManager interface</li>
     * </ol>
     */
    public static void main(String args[]) {
        if (args.length == 4) {
            System.err.println("\nUsage: KFileManagerServer <port> "
                + "<context path> <springContextConfig> "
                + "<spring fileManagerRef>\n");

            System.exit(1);
        }

        Integer port = Integer.parseInt(args[0]);
        String contextPath = args[1];
        String springContextConfig = args[2];
        String fileManagerRef = args[3];

        KFileManagerServer server = new KFileManagerServer(port, contextPath, 
                springContextConfig, fileManagerRef);

        try { server.start(); } catch (Exception e) { logger.error(e.getMessage(), e); }
    }
}
