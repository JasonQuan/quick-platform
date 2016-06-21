/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quick.ext.primefaces.base.web.bean;
 
import com.quick.ext.primefaces.base.web.view.entity.Category;
import java.util.List;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 * 20130915 upgrade to primefaces4.0RC1
 *
 * @author jason
 */
public class BaseWebUtils {

    public static Menubar convertCategoryToMenubar(List<Category> categorys) {
        Menubar menubara = new Menubar();
        menubara = (Menubar) convertCategoryToMenubar(menubara, categorys);
        return menubara;
    }

    private static UIComponent convertCategoryToMenubar(UIComponent ui, List<Category> categorys) {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //todo: SuperKungFund is only for local development
        
//           String requestpath = "http://"+origRequest.getServerName()+":"+origRequest.getServerPort()+"/SuperKungFund/";       
           String requestpath = "http://"+origRequest.getServerName()+":"+origRequest.getServerPort()+origRequest.getContextPath()+"/";
        for (Category c : categorys) {
            if (c.getDisabled()) {//TODO: primession
                continue;
            }
            if (c.getCategoryList().isEmpty()) {
                UIMenuItem menuItem = new UIMenuItem();
                menuItem.setValue(c.getName());
                menuItem.setIcon(c.getIcon());
                menuItem.setOnclick(c.getOnclick());
                menuItem.setOncomplete(c.getOncomplete());
                menuItem.setOnerror(c.getOnerror());
                menuItem.setOnstart(c.getOnstart());
                menuItem.setOnsuccess(c.getOnsuccess());
                menuItem.setStyle(c.getStyle());
                menuItem.setTitle(c.getTitle());
                menuItem.setStyleClass(c.getStyleClass());
                if (!"#".equals(c.getUrl())) {
                    menuItem.setTarget(c.getTarget());
                    menuItem.setUrl(requestpath + c.getUrl());
                }
                if(c.getActionListener() == null){
                    menuItem.setActionExpression(buildActionExpression(c.getActionListener()));
                }
                menuItem.setAjax(c.getAjax());
                menuItem.setAsync(c.getAsync());
                menuItem.setIncludeViewParams(c.getIncludeViewParams());
                menuItem.setPartialSubmit(c.getPartialSubmit());
                menuItem.setGlobal(c.getGlobal());
                //#{facesContext.externalContext.requestContextPath}
                menuItem.setFragment(c.getFragment());
                menuItem.setProcess(c.getProcess());
                menuItem.setUpdate(c.getUpdateRender());
//                LoggerUtils.out("[Category] " + c);
                ui.getChildren().add(menuItem);
            } else {
                UISubmenu submenu = new UISubmenu();
                submenu.setLabel(c.getName());
                submenu.setIcon(c.getIcon());
                submenu.setStyle(c.getStyle());
                submenu.setStyleClass(c.getStyleClass());
                ui.getChildren().add(submenu);
                convertCategoryToMenubar(submenu, c.getCategoryList());
            }
        }
        return ui;
    }

    private static MethodExpression buildActionExpression(String action) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        ELContext elCtx = facesCtx.getELContext();
        ExpressionFactory expFact = facesCtx.getApplication().getExpressionFactory();
        MethodExpression expr = expFact.createMethodExpression(elCtx, action, String.class, new Class[0]);
        return expr;
    }

    /**
     * 不 包 含 Disabled=true 的 节 点
     *
     * @param tree
     * @param cs
     * @return
     */
    public static TreeNode convertCategorysToTreeNode(boolean expanded, TreeNode tree, List<Category> cs) {
        for (Category c : cs) {
            if (c.getDisabled()) {//TODO: primession
                continue;
            }
            DefaultTreeNode node = new DefaultTreeNode(c, tree);
            node.setExpanded(expanded);
            if (!c.getCategoryList().isEmpty()) {
                convertCategorysToTreeNode(expanded, node, c.getCategoryList());
            }
        }

        return tree;
    }

    private static TreeNode convertCategorysToTreeNode(List<Category> categorys) {
        TreeNode tree = new DefaultTreeNode("ROOT", null);
        for (Category c : categorys) {
            if (c.getDisabled()) {//TODO: primession
                continue;
            }
            TreeNode node = new DefaultTreeNode(c, tree);

        }
        return tree;
    }

    /**
     * 所 有 节 点
     *
     * @param expanded 是 否 展 开
     * @param tree TreeNode
     * @param cs List<Category>
     * @return TreeNode
     */
    public static TreeNode getAllTreeNodeByCategorys(boolean expanded, TreeNode tree, List<Category> cs) {
        for (Category category : cs) {
            DefaultTreeNode node = new DefaultTreeNode(category, tree);
            node.setExpanded(expanded);
            if (!category.getCategoryList().isEmpty()) {
                getAllTreeNodeByCategorys(expanded, node, category.getCategoryList());
            }
        }
        return tree;
    }
}
