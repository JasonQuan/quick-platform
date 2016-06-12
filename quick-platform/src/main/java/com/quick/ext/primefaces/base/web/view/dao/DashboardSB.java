package com.quick.ext.primefaces.base.web.view.dao;

import java.io.Serializable;


import com.quick.ext.primefaces.base.web.view.entity.BaseDashboardModel;
//@Dependent
//@Named
public class DashboardSB extends QuickDataService<BaseDashboardModel, BaseDashboardModel> implements Serializable {
    @Override
    public Class<BaseDashboardModel> getEntityClass() {
        return BaseDashboardModel.class;
    }

    @Override
    public Class<BaseDashboardModel> getVOClass() {
        return BaseDashboardModel.class;
    }
}
