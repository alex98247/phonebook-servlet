package com.digdes.school.phonebook.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerTest {

    public static final String WEBAPP_RESOURCES_LOCATION = "src/main/webapp";
    public static final int START_PORT = 8085;

    private static Logger LOGGER = LoggerFactory.getLogger(ServerTest.class);

    @Test
    public void testConverter(){
        try {
            URL url = new URL("localhost:8085/convert");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            Path path = Paths.get("/Users/Alex/Desktop/person.dbf");
            byte[] data = Files.readAllBytes(path);
            wr.write(data);
            wr.flush();
            wr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        LOGGER.info("Starting jetty...");
        Server server = new Server(START_PORT);
        WebAppContext root = new WebAppContext(WEBAPP_RESOURCES_LOCATION, "/phonebook-servlet");

       /* root.setConfigurations(new Configuration[] {
                new AnnotationConfiguration(), new WebXmlConfiguration(),
                new WebInfConfiguration(),
                new PlusConfiguration(), new MetaInfConfiguration(),
                new FragmentConfiguration(), new EnvConfiguration() });*/
        //root.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/classes/.*");
        //
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault( server );
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration" );

        // Set the ContainerIncludeJarPattern so that jetty examines these
        // container-path jars for tlds, web-fragments etc.
        // If you omit the jar that contains the jstl .tlds, the jsp engine will
        // scan for them instead.
        root.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$|.*/classes/.*" );

        root.setParentLoaderPriority(true);

        server.setHandler(root);
        server.start();
        LOGGER.info("Jetty server started");
        server.join();
    }

}
