package com.quick.ext.primefaces.base.web.view.entity;

import com.quick.ext.primefaces.base.entity.AbstractEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Jason
 */
@Entity
@Table(name = "CATEGORY")
@XmlRootElement
//@XmlType
//@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
    @NamedQuery(name = "Category.findByRemark", query = "SELECT c FROM Category c WHERE c.remark = :remark")})
public class Category extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Basic
    @Column(name = "ID", nullable = false, length = 32)
    protected String id;
    //@Size(max = 45)
    @NotEmpty
    private String name;
    private int sort;
    //@Size(max = 45)
    @Column(length = 45)
    private String remark;
    @Column(columnDefinition = "VARCHAR(1024) default '#'")
    private String url;
    @Column(columnDefinition = "VARCHAR(255) default '_self'")
    private String target;
    private String style;
    private String styleClass;
    private String onclick;
    private String updateRender;
    private String process;
    private String onstart;
    @Column(columnDefinition = "TINYINT(1) default 0")
    private boolean disabled;
    private String oncomplete;
    private String onerror;
    private String onsuccess;
    private boolean global;
    private boolean async;
    private boolean ajax;
    private String icon;
    private boolean partialSubmit;
    private String title;
    private String outcome;
    private String action;
    private String actionListener;
    private String immediate;
    private boolean includeViewParams;
    private String fragment;
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderBy("sort ASC")
    private List<Category> categoryList;
    @JoinColumn(name = "PARENT_CATEGORY", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = true)
    private Category parentCategory;
    @Column(name = "CATEGORY_LEAVE")
    private String leave;

    public Category() {
    }

    public int getSort() {
        return sort;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionListener() {
        return actionListener;
    }

    public void setActionListener(String actionListener) {
        this.actionListener = actionListener;
    }

    public String getImmediate() {
        return immediate;
    }

    public void setImmediate(String immediate) {
        this.immediate = immediate;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        if (url == null || "".equals(url)) {
            url = "#";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getParentCategory() {
//        if(parentCategory == null){
//            parentCategory = new Category();
//        }
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getUpdateRender() {
        return updateRender;
    }

    public void setUpdateRender(String updateRender) {
        this.updateRender = updateRender;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getOnstart() {
        return onstart;
    }

    public void setOnstart(String onstart) {
        this.onstart = onstart;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getOnerror() {
        return onerror;
    }

    public void setOnerror(String onerror) {
        this.onerror = onerror;
    }

    public String getOnsuccess() {
        return onsuccess;
    }

    public void setOnsuccess(String onsuccess) {
        this.onsuccess = onsuccess;
    }

    public boolean getGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public boolean getAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public boolean getAjax() {
        return ajax;
    }

    public void setAjax(boolean ajax) {
        this.ajax = ajax;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean getPartialSubmit() {
        return partialSubmit;
    }

    public void setPartialSubmit(boolean partialSubmit) {
        this.partialSubmit = partialSubmit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public boolean getIncludeViewParams() {
        return includeViewParams;
    }

    public void setIncludeViewParams(boolean includeViewParams) {
        this.includeViewParams = includeViewParams;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    @XmlTransient
    public List<Category> getCategoryList() {
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (getId() != null) {
            hash += getId().hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if (getId() == null ? other.getId() != null : !getId().equals(other.getId())) {
            return false;
        }
        return true;
    }
}
