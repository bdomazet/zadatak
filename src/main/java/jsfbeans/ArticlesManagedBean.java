package jsfbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import zadatak.app.entity.Article;
import zadatak.app.entity.facade.ArticleFacadeLocal;


@Named(value = "articlesManagedBean")
@SessionScoped
public class ArticlesManagedBean implements Serializable {

    private Integer articleId = null;
    private String articleName = null;
    private int articleAmount = 0;
    private Article article;

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
        return "write";
    }

    public String removeArticle(Integer articleId) {
        Article articleTemp = articlesFacadeLocal.find(articleId);
        articlesFacadeLocal.remove(articleTemp);
        return "write";
    }

    public String updateArticle() {
        Article articleTemp = articlesFacadeLocal.find(articleId);
        for (Article artcleTemp : _articlesList) {
            if (articleTemp != null) {
                if (artcleTemp.getId().equals(articleId)) {
                    article = new Article(articleId, articleName, articleAmount);
                    articlesFacadeLocal.edit(article);
                }
            }
        }
        return "write";
    }
}
