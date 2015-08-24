package ro.duclad.examples.struts2.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.ServletContext;
import java.io.File;


public class ConfigResourceFactory {

    /* -- VARIABLES:
     */
    public static final String DEFAULT_FILENAME = "authors.properties";

    /* -- INITIALIZERS:
     */
    private ConfigResourceFactory() {
        ;
    }

    public static Resource createLocation(String key, String webappPath, ServletContext context) {
        String path = System.getProperty(key);
        if (path != null) {
            File file = new File(path);

			/* If a directory path is provided:
             */
            if (file.isDirectory())
                file = new File(file.getAbsolutePath() + File.separator + DEFAULT_FILENAME);

            if (file.canRead())
                return new FileSystemResource(file);
        }

        path = System.getenv(key);
        if (path != null) {
            File file = new File(path);

			/* If a directory path is provided:
             */
            if (file.isDirectory())
                file = new File(file.getAbsolutePath() + File.separator + DEFAULT_FILENAME);

            if (file.canRead())
                return new FileSystemResource(file);
        }

        return new ServletContextResource(context, webappPath);
    }
}
