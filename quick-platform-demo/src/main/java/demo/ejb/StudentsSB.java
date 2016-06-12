package demo.ejb;

import java.io.Serializable;
import com.quick.ext.primefaces.base.web.view.dao.QuickDataService;
import demo.entity.Students;
import javax.enterprise.context.Dependent;

@Dependent
//@Named
public class StudentsSB extends QuickDataService<Students, Students> implements Serializable {

    @Override
    public Class<Students> getEntityClass() {
        return Students.class;
    }

    @Override
    public Class<Students> getVOClass() {
        return Students.class;
    }
}
