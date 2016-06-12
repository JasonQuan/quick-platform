package com.quick.ext.primefaces.base.web.view.dao;

import javax.persistence.EntityManager;

import com.quick.ext.primefaces.base.service.BaseEJB;
import com.quick.ext.primefaces.base.entity.AbstractEntity;
import com.quick.ext.primefaces.base.service.QuickJpaProvider;
import com.quick.ext.primefaces.base.service.QuickJpaSPI;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public abstract class QuickDataService<T extends AbstractEntity, E extends AbstractEntity> extends BaseEJB<T, E> {

    public QuickDataService() {
    }
    //@Inject
    //private Instance<QuickJpaProvider> services;
    private static final long serialVersionUID = 1L;
//    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        try {
            //if (services != null && em == null) {
             //   em = services.get().getEntityManager();
           // }
            if (em == null) {
                em = QuickJpaSPI.getEntityManager().getEntityManager();
            }
        } catch (Exception e) {
            logger.error("TODO: message");
            logger.error(e);
            throw new RuntimeException();
        }

        return em;
    }
}
