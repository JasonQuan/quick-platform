package com.quick.ext.primefaces.base.web.view.dao;

import com.quick.ext.primefaces.base.web.view.entity.BasePanelModel;
import java.io.Serializable;

//@Dependent
//@Named
public class PanelModelSB extends QuickDataService<BasePanelModel, BasePanelModel> implements Serializable {

    @Override
    public Class<BasePanelModel> getEntityClass() {
        return BasePanelModel.class;
    }

    @Override
    public Class<BasePanelModel> getVOClass() {
        return BasePanelModel.class;
    }
}
