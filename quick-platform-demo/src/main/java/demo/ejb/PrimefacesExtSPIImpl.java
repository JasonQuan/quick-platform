package demo.ejb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.quick.ext.primefaces.base.service.QuickJpaProvider;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jason
 */
//@Dependent
//@Named
public class PrimefacesExtSPIImpl implements QuickJpaProvider {

    private EntityManager em = null;

    @Override
    public EntityManager getEntityManager() {
        try {
            if (em == null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
                em = emf.createEntityManager();
            }
            if (em == null) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw (e);
        }

        return em;
    }

}
