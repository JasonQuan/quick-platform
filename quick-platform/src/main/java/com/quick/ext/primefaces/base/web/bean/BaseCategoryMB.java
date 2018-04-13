package com.quick.ext.primefaces.base.web.bean;

import com.quick.ext.primefaces.base.util.MessageBundle;
import com.quick.ext.primefaces.base.web.BaseMB;
import com.quick.ext.primefaces.base.web.view.dao.BaseCategoryDao;
import com.quick.ext.primefaces.base.web.view.entity.Category;
import com.quick.ext.primefaces.base.web.view.entity.Category_;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Jason
 */
@ManagedBean
@ViewScoped
public class BaseCategoryMB extends BaseMB<Category, Category> {

    private final BaseCategoryDao dao = new BaseCategoryDao();
    private String type;
    @Override
    protected BaseCategoryDao dao() {
        return dao;
    }

    @Override
    protected SingularAttribute selectItemLabel() {
        return Category_.name;
    }

    @Override
    protected String getDataModelJpql() {
        return super.getDataModelJpql() + " and o.type = '" + getType() + "' ";
    }

    @Override
    public void newEmptyEntity() {
        try {
            Category c = new Category();
            c.setType(getType());
            MessageBundle.autoMessage(dao().create(c));
        } catch (Exception e) {
            MessageBundle.showWarning("create", "error");
            logger.error(e);
        }
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
