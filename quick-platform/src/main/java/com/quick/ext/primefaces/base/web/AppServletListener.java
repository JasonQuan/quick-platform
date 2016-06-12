package com.quick.ext.primefaces.base.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.quick.ext.primefaces.base.util.BaseLogger;
import com.quick.ext.primefaces.base.util.BaseObject;

/**
 * Web application life cycle listener.
 *
 * @author Jason
 */
@WebListener
public class AppServletListener extends BaseObject implements ServletContextListener {
	private final BaseLogger logger = new BaseLogger(this.getClass());
	//public abstract void excuest(ServletContextEvent sce);

	@Override
	public final void contextInitialized(ServletContextEvent sce) {
		logger.info("Running on Primefaces Ext lib 1.0");
		//excuest(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
