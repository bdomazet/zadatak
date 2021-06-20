package zadatak.app.entity.facade;

import java.util.List;
import javax.ejb.Local;
import zadatak.app.entity.Users;

@Local
public interface UsersFacadeLocal {
    void create(Users user);
    
    void edit(Users user);
    
    void remove(Users user);
    
    Users find(Object id);
    
    List<Users> findAll();
    
}
