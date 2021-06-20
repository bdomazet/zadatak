package zadatak.app.entity.facade;

import java.util.List;
import javax.ejb.Local;
import zadatak.app.entity.Articles;

@Local
public interface ArticlesFacadeLocal {

    void create(Articles article);

    void edit(Articles article);

    void remove(Articles article);

    Articles find(Object id);

    List<Articles> findAll();

}
