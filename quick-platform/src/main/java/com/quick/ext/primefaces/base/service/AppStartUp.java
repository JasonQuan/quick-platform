package com.quick.ext.primefaces.base.service;

import javax.annotation.PostConstruct;

import com.quick.ext.primefaces.base.web.view.dao.BaseColumnModelSB;

/**
 *
 * @author Jason
 * @date 2015-7-3
 */
 
public class AppStartUp {

//    @Inject
    private BaseColumnModelSB baseColumnModelSB;
    public final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppStartUp.class);

    @PostConstruct
    protected void initCache() {
        long start = System.currentTimeMillis();
        logger.info("start init dynamic column configuration");
        baseColumnModelSB.findAll();
        long end = System.currentTimeMillis();
        logger.info("end init dynamic column configuration , using " + (end - start) / 1000 + " ms");

    }
}
