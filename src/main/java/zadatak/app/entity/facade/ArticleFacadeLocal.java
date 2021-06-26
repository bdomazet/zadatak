package zadatak.app.entity.facade;

import java.util.List;
import javax.ejb.Local;
import zadatak.app.entity.Article;


@Local
public interface ArticleFacadeLocal {

    void create(Article article);

    void edit(Article article);

    void remove(Article article);

    Article find(Object id);

    List<Article> findAll();

    List<Article> findRange(int[] range);

    int count();
    
}
