package com.quick.ext.primefaces.base.service;

import javax.annotation.PostConstruct;

import com.quick.ext.primefaces.base.util.BaseLogger;
import com.quick.ext.primefaces.base.web.view.dao.BaseColumnModelSB;

/**
 *
 * @author Jason
 * @date 2015-7-3
 */
 
public class AppStartUp {

//    @Inject
    private BaseColumnModelSB baseColumnModelSB;
    private BaseLogger LOGGER = new BaseLogger(this.getClass());

    @PostConstruct
    protected void initCache() {
        long start = System.currentTimeMillis();
        LOGGER.info("start init dynamic column configuration");
        baseColumnModelSB.findAll();
        long end = System.currentTimeMillis();
        LOGGER.info("end init dynamic column configuration , using " + (end - start) / 1000 + " ms");

    }
}
