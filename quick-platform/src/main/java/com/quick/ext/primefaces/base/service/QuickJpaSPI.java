package com.quick.ext.primefaces.base.service;

import java.util.ServiceLoader;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QuickJpaSPI {

    public static QuickJpaProvider getEntityManager() {

        QuickJpaProvider factory = null;

        ServiceLoader<QuickJpaProvider> loader = ServiceLoader.load(QuickJpaProvider.class);
        for (QuickJpaProvider currentFactory : loader) {
            factory = currentFactory;
            break;
        }

        if (factory == null) {
            factory = new DefaultQuickJpaSPI();
        }

        return factory;
    }
}
//@Default
class DefaultQuickJpaSPI implements QuickJpaProvider {

    @Override
    public EntityManager getEntityManager() {
        EntityManager em = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
            em = emf.createEntityManager();
        } catch (Exception e) {
            throw (e);
        }

        return em;
    }

}
