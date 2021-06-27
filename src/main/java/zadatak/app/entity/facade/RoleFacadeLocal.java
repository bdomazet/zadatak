package zadatak.app.entity.facade;

import java.util.List;
import javax.ejb.Local;
import zadatak.app.entity.Role;


@Local
public interface RoleFacadeLocal {

    void create(Role role);

    void edit(Role role);

    void remove(Role role);

    Role find(Object id);

    List<Role> findAll();

    List<Role> findRange(int[] range);

    int count();
    
}
