package ro.duclad.examples.struts2.utils;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;


public class ServletContextWrapper implements ServletContextAware {
	
	private ServletContext context;

	public ServletContextWrapper() {
		;
	}
	
	/* -- VALUE ACCESSORS:
	 */
	
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public ServletContext getServletContext() {
		return context;
	}
}
