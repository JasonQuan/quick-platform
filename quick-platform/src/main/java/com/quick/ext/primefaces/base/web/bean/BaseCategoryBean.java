package com.quick.ext.primefaces.base.web.bean;

import com.quick.ext.primefaces.base.util.MessageBundle;
import com.quick.ext.primefaces.base.web.view.dao.BaseCategoryDao;
import com.quick.ext.primefaces.base.web.view.entity.Category;
import com.quick.ext.primefaces.base.web.view.entity.Category_;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        Map<String, Object> atts = event.getComponent().findComponent("eid").getAttributes();
        String entityId = atts.get("title").toString();
        String fieldName = atts.get("styleClass").toString();
        if (newValue != null && !newValue.equals(oldValue)) {
            dao.update(entityId, fieldName, newValue);
            MessageBundle.showInfo("Cell Changed");
        }
    }

    public TreeNode getAllTreeNode(boolean expanded) {
        if (allTreeNode == null) {
        }
        allTreeNode = BaseWebUtils.getAllTreeNodeByCategorys(expanded, new DefaultTreeNode("Root", null), dao().getRootNode());
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
