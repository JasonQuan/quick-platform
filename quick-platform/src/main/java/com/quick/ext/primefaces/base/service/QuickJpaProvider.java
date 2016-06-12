package com.quick.ext.primefaces.base.service;

import javax.persistence.EntityManager;

public interface QuickJpaProvider {

    EntityManager getEntityManager();
}
