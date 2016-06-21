package com.quick.ext.primefaces.base.web.bean;

import com.quick.ext.primefaces.base.entity.AbstractEntity;
import com.quick.ext.primefaces.base.service.BaseEJB;
import com.quick.ext.primefaces.base.util.BaseKeyValue;
import com.quick.ext.primefaces.base.util.MessageBundle;
import com.quick.ext.primefaces.base.util.ObjectUtil;
import com.quick.ext.primefaces.base.web.BaseConverter;
import com.quick.ext.primefaces.base.web.ForeignConverter;
import com.quick.ext.primefaces.base.web.LazyEntityDataModel;
import com.quick.ext.primefaces.base.web.view.dao.BaseColumnModelSB;
import com.quick.ext.primefaces.base.web.view.entity.BaseColumnModel;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.log4j.Logger;
import org.primefaces.component.columns.Columns;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Jason
 * @param <T>
 */
public abstract class BaseController<T extends AbstractEntity> implements Serializable {

    protected static final Logger logger = Logger.getLogger("");

    /**
     * get BaseDao
     *
     * @return BaseDao
     */
    protected abstract BaseEJB<T, T> dao();

    /**
     * the value for select item label
     *
     * @return
     */
    protected SingularAttribute selectItemLabel() {
        return null;
    }
    private T entity;
    /**
     * 实 体 数 组，用 于 datatable 多 选
     */
    private List<T> entitys;
    //t est

    public BaseController() {
    }

    /**
     * get Request Parameter
     *
     * @param key
     * @return String
     */
    public String requestParameter(String key) {
        try {
            Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            return requestParameter.get(key);
        } catch (NullPointerException e) {
            logger.debug("[no key :]" + key);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 添加新增条件
     *
     * @return FacesMessage
     */
    protected FacesMessage createCondition() {
        return null;
    }

    protected void afterCreate() {
    }

    protected void afterCreateSuccess() {
    }

    protected void afterRemove() {
    }

    protected void afterUpdate() {
    }

    public void create() {
        logger.debug("[cell create] " + entity);
        FacesMessage message = createCondition();
        if (message != null) {
            MessageBundle.autoMessage(message);
            return;
        }
        message = dao().create(entity);
        afterCreate();
        if (message != null) {
            MessageBundle.autoMessage(message);
            if (message.getSeverity().equals(FacesMessage.SEVERITY_INFO)) {
                reset();
                afterCreateSuccess();
            }
        }
    }

    //TODO: final can not overwrite
    public final void remove() {
        remove(entity);
    }

    public void remove(T t) {
        //TODO:  delete condaition
        MessageBundle.autoMessage(dao().remove(t));
        afterRemove();
    }

    /**
     * go to list page, if update successful.
     *
     */
    public void update() {
        FacesMessage message = dao().update(entity);
        MessageBundle.autoMessage(message);
        afterUpdate();
    }

    public void persist() {
        update();
    }

    /**
     * get BaseConverter
     *
     * @return BaseConverter
     */
    public BaseConverter<T> getConverter() {
        return new BaseConverter<>(dao());
    }

    /**
     * 如果默认 lazyEntityDataModel 需要添加条件，请重写此方法<br/>
     *
     * if default lazyEntityDataModel need add condition, please Override this
     * method<br/>
     *
     *
     * @return datamodel filter, eg: id > 10 and o.id < 1000
     */
    protected String getDataModelFilter() {
        return null;
    }

    /**
     * @deprecated get all entitys
     *
     * @return ALL entity
     */
    public List<T> all() {
        return dao().findAll();
    }

    private void setEntityByRequestEntityId() {
        try {
//            Long id = Long.valueOf(requestParameter("id"));
            String key = requestParameter("id");
            if (key != null && !"".equals(key)) {
                entity = dao().find(requestParameter("id"));
            }
        } catch (Exception e) {
            logger.debug(e);
        }
    }

    /**
     * get getEntity()
     *
     * @return entity
     */
    public T getEntity() {
        setEntityByRequestEntityId();
        if (entity == null) {
            try {
                entity = dao().getEntityClass().newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                logger.error(e);
            }
        }
        return entity;
    }

    public List<T> getEntitys() {
        if (this.entitys == null) {
            entitys = java.util.Collections.EMPTY_LIST;
        }
        return entitys;
    }

    public void setEntitys(List<T> entitys) {
        this.entitys = entitys;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    /**
     * use for select on the view
     *
     * @param selectOne
     * @return SelectItem[]
     */
    //TODO: 抽象条件
    public SelectItem[] selectItemsI18n(Boolean selectOne) {
        return dao().getSelectItems(selectItemLabel(), null, null, selectOne, true);
    }

    public SelectItem[] selectItems(Boolean selectOne) {
        return dao().getSelectItems(selectItemLabel(), null, null, selectOne);
    }

    /**
     * clear up
     */
    public void reset() {
        entity = null;
    }

    /**
     * page redirect
     *
     * @param page page path
     */
    public void redirect(String page) {
        try {
            if (page != null && !"".equals(page)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(page);
            }
        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    /**
     * use for get customs columns
     *
     * default key is "default"
     *
     * if user need get customs column,just over write this method,
     *
     *
     *
     * @return customs key
     */
    protected String getCustomColumnsKey() {
        return "default";
    }
}
