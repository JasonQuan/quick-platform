package com.quick.ext.primefaces.base.web.view.dao;

import javax.persistence.EntityManager;

import com.quick.ext.primefaces.base.service.BaseEJB;
import com.quick.ext.primefaces.base.entity.AbstractEntity;
import com.quick.ext.primefaces.base.service.QuickJpaSPI;

public abstract class QuickDataService<T extends AbstractEntity, E extends AbstractEntity> extends BaseEJB<T, E> {

    public QuickDataService() {
    }
    private static final long serialVersionUID = 1L;

    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        try {
            if (em == null) {
                em = QuickJpaSPI.getEntityManager().getEntityManager();
            }
        } catch (Exception e) {
            logger.error("call QuickDataService getEntityManager() error");
            logger.error(e);
            throw new RuntimeException();
        }

        return em;
    }
}
