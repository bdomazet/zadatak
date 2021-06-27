package jsfbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import zadatak.app.entity.Article;
import zadatak.app.entity.facade.ArticleFacadeLocal;

@Named(value = "articlesManagedBean")
@RequestScoped
public class ArticlesManagedBean implements Serializable {

    private Integer articleId = null;
    private String articleName = null;
    private int articleAmount = 0;

    private List<Article> _articlesList;

    @Inject
    ArticleFacadeLocal articlesFacadeLocal;

    @PostConstruct
    private void init() {
        _articlesList = articlesFacadeLocal.findAll();
    }

    public ArticlesManagedBean() {
    }

    public List<Article> getArticlesList() {
        return _articlesList;
    }

    public void setArticlesList(List<Article> _articlesList) {
        this._articlesList = _articlesList;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public int getArticleAmount() {
        return articleAmount;
    }

    public void setArticleAmount(int articleAmount) {
        this.articleAmount = articleAmount;
    }

    public String addArticle() {
        Article articleTemp = new Article(null, articleName, articleAmount);
        articlesFacadeLocal.create(articleTemp);
        init();
        return "write";
    }

    public String removeArticle(Integer articleId) {
        Article articleTemp = articlesFacadeLocal.find(articleId);
        articlesFacadeLocal.remove(articleTemp);
        init();
        return "write";
    }

    public String updateArticle() {
        Article articleTemp = articlesFacadeLocal.find(articleId);
        if (articleTemp != null) {
            articleTemp.setName(articleName);
            articleTemp.setAmount(articleAmount);
            articlesFacadeLocal.edit(articleTemp);
            init();
        }
        return "write";
    }
}
