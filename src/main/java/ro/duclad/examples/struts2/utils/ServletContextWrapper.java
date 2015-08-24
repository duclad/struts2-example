package ro.duclad.examples.struts2.utils;

import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;


public class ServletContextWrapper implements ServletContextAware {

    private ServletContext context;

    public ServletContextWrapper() {
        ;
    }

	/* -- VALUE ACCESSORS:
     */

    public ServletContext getServletContext() {
        return context;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
