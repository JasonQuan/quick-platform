package com.quick.ext.primefaces.base.web.bean;

import com.quick.ext.primefaces.base.util.MessageBundle;
import com.quick.ext.primefaces.base.web.view.dao.BaseCategoryDao;
import com.quick.ext.primefaces.base.web.view.entity.Category;
import com.quick.ext.primefaces.base.web.view.entity.Category_;
import java.util.Arrays;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.log4j.Logger;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Jason
 */
@ManagedBean
@ViewScoped
public class BaseCategoryBean extends BaseController<Category> {

    private static final Logger logger = Logger.getLogger("");
    private List<Category> categorys;
    private BaseCategoryDao dao;
    private TreeNode tree;
    private TreeNode allTreeNode;
    private Menubar menubar;
    private TreeNode selectNode;
    private boolean isEmpty;

    /**
     * demo: data-id field="name"
     * <p:column headerText="name" style="width:200px;" field="name">
     * <p:cellEditor data-id="#{tree.id}">
     * <f:facet name="output"><h:outputText value="#{tree.name}" /></f:facet>
     * <f:facet name="input"><p:inputText value="#{tree.name}" style="width:100%"/></f:facet>
     * </p:cellEditor>
     * </p:column>
     *
     * @param event
     */
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            String fieldName = event.getColumn().getCellEditor().getFacet("input").getValueExpression("value").getExpressionString();
            fieldName = fieldName.substring(fieldName.lastIndexOf(".") + 1, fieldName.length() - 1);

            FacesContext ctx = FacesContext.getCurrentInstance();
            ValueExpression idExpression = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{tree.id}", String.class);
            String entityId = idExpression.getValue(ctx.getELContext()).toString();

            logger.debug("call onCellEdit: [" + entityId + "][" + fieldName + "][" + newValue);
            FacesMessage update = dao().update(entityId, fieldName, newValue);
            update.setSummary("修改");
            MessageBundle.autoMessage(update);
        }

    }

    public void addNode(Category catecory) {

        Category cate = new Category();
        cate.setParentCategory(catecory);
        cate.setType(catecory.getType());
        dao().create(cate);
        addSubNode(allTreeNode.getChildren(), cate);
    }

    public void removeNode(Category category) {
        String[] id = {category.getId().toString()};
        dao().removeBatchByIds(Arrays.asList(id));
        BaseCategoryBean.this.removeNode(allTreeNode.getChildren(), category);
    }

    private void addSubNode(List<TreeNode> nodes, Category categroy) {
        for (TreeNode node : nodes) {
            if (((Category) node.getData()).getId().equals(categroy.getParentCategory().getId())) {
                node.setExpanded(true);
                node.getChildren().add(new DefaultTreeNode(categroy, node));
                return;
            }
            if (!node.getChildren().isEmpty()) {
                addSubNode(node.getChildren(), categroy);
            }
        }
    }

    private void removeNode(List<TreeNode> nodes, Category cate) {

        for (TreeNode node : nodes) {
            if (((Category) node.getData()).getId().equals(cate.getId())) {
                nodes.remove(node);
                return;
            }
            if (!node.getChildren().isEmpty()) {
                BaseCategoryBean.this.removeNode(node.getChildren(), cate);
            }
        }
    }

    public TreeNode getAllTreeNode(boolean expanded, String type) {
        if (allTreeNode == null) {
            allTreeNode = BaseWebUtils.getAllTreeNodeByCategorys(expanded, new DefaultTreeNode("Root", null), dao().getRootNode(type));
        }
        return allTreeNode;
    }

    public TreeNode getAllTreeNode(boolean expanded) {
        getAllTreeNode(expanded, null);
        return allTreeNode;
    }

    public void setAllTreeNode(TreeNode allTreeNode) {
        this.allTreeNode = allTreeNode;
    }

    public void addSameNode() {
        Category c = ((Category) selectNode.getData()).getParentCategory();
        Category entity = super.getEntity();
        entity.setParentCategory(c);
        super.setEntity(entity);
        logger.debug("addSameNode[entity.getName()] " + entity.getName());
        super.create();
    }

    //add 同级t
    public void addSubNode() {
        Category entity = super.getEntity();
        Category c = (Category) getSelectNode().getData();
        //      tree.getChildren().add(new DefaultTreeNode(selectNode.getParent(), (TreeNode) c));
        if (c != null) {
            entity.setParentCategory(c);
        }
        super.setEntity(entity);
        logger.debug("[entity.getName()] " + entity.getName());
        super.create();
    }

    public void editNode() {
        setEntity((Category) getSelectNode().getData());
        super.persist();
    }

    public void removeNode() {
        super.remove((Category) selectNode.getData()); //To change body of generated methods, choose Tools | Templates.
        selectNode.getChildren().clear();
        selectNode.getParent().getChildren().remove(selectNode);
        selectNode.setParent(null);
    }

    public Menubar getMenubar() {
        if (menubar == null) {
            menubar = BaseWebUtils.convertCategoryToMenubar(dao().getRootNode());
        }
        return menubar;
    }

    public void setMenubar(Menubar menubar) {
        this.menubar = menubar;
    }

    public List<Category> getAll() {
        return dao().findAll();
    }

    public TreeNode getTree(boolean expanded) {
        if (tree == null) {
            tree = BaseWebUtils.convertCategorysToTreeNode(expanded, new DefaultTreeNode("Root", null), dao().getRootNode());
        }
        return tree;
    }

    public List<Category> getCategorys() {
        categorys = dao().getRootNode();
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    @Override
    protected BaseCategoryDao dao() {
        dao = new BaseCategoryDao();
        return dao;
    }

    @Override
    protected SingularAttribute selectItemLabel() {
        return Category_.name;
    }

    public TreeNode getSelectNode() {
        if (selectNode == null) {
            selectNode = new DefaultTreeNode();
        }
        return selectNode;
    }

    public void setSelectNode(TreeNode selectNode) {
        //  setEntity((Category) selectNode.getData());
        this.selectNode = selectNode;
    }

    public boolean isIsEmpty() {
        isEmpty = dao().getCount() == 0;
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
