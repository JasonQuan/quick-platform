package com.quick.ext.primefaces.base.web.view.dao;

import com.quick.ext.primefaces.base.web.view.entity.Category;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jason
 */
public class BaseCategoryDao extends QuickDataService<Category, Category> {

    public BaseCategoryDao() {
    }

    public List<Category> getRootNode(String type) {
        // getEntityManager().getEntityManagerFactory().getCache().evict(Category.class);
        //  clearCache();
        List<Category> outcome = findByJPQL("SELECT o FROM Category o WHERE o.type = '" + type + "' and o.parentCategory is null ORDER BY o.leave ASC, o.sort ASC");
        if(outcome.isEmpty()){
            Category category = new Category();
            category.setName("ROOT");
            category.setType(type);
            super.create(category);
            outcome = findByJPQL("SELECT o FROM Category o WHERE o.type = '" + type + "' and o.parentCategory is null ORDER BY o.leave ASC, o.sort ASC");
        }
        //query.setHint(QueryHints.CACHE_STORE_MODE, QueryHints.REFRESH);
//        return query.getResultList();
        return outcome;
    }

    public List<Category> getRootNode() {
        // getEntityManager().getEntityManagerFactory().getCache().evict(Category.class);
        //  clearCache();
        return findByJPQL("SELECT o FROM Category o WHERE o.parentCategory is null ORDER BY o.leave ASC, o.sort ASC");
        //query.setHint(QueryHints.CACHE_STORE_MODE, QueryHints.REFRESH);
//        return query.getResultList();
    }

    public Category findByParentCategoryAndName(String parentCategoryId, String name) throws Exception {
        Map<String, Object> ps = new HashMap<>();
        ps.put("parentCategory.id", parentCategoryId);
        ps.put("name", name);
        List<Category> outcome = super.findByFields(ps);
        if (outcome == null || outcome.size() > 0) {
            throw new Exception("the query result is incorrect.");
        }
        return outcome.get(0);
    }
}
