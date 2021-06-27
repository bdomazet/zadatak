package zadatak.app.entity.facade;

import java.util.List;
import javax.ejb.Local;
import zadatak.app.entity.User;


@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    public User findByUsername(String username);

    List<User> findAll();

}
