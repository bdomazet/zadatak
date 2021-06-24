package jsfbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;
import zadatak.app.entity.Articles;
import zadatak.app.entity.facade.ArticlesFacadeLocal;

@Named(value = "articlesManagedBean")
@SessionScoped
public class ArticlesManagedBean implements Serializable {

    private Integer articleId = null;
    private String articleName = null;
    private int articleAmount = 0;
    private Articles article;

    private List<Articles> _articlesList;

    @Inject
    ArticlesFacadeLocal articlesFacadeLocal;

    @PostConstruct
    private void init() {
        _articlesList = articlesFacadeLocal.findAll();
    }

    public ArticlesManagedBean() {
    }

    public List<Articles> getArticlesList() {
        return _articlesList;
    }

    public void setArticlesList(List<Articles> _articlesList) {
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
        Articles articleTemp = new Articles(null, articleName, articleAmount);
        articlesFacadeLocal.create(articleTemp);
        return "";
    }

    public String removeArticle(Integer articleId) {
        Articles articleTemp = articlesFacadeLocal.find(articleId);
        articlesFacadeLocal.remove(articleTemp);
        return "";
    }

    public String updateArticle(Integer articleId) {
        Articles articleTemp = articlesFacadeLocal.find(articleId);
        if (articleTemp != null) {
            if (articleTemp.getName().equals(articleName)) {
                if (!(articleTemp.getAmount() == articleAmount)) {
                    article = new Articles(articleTemp.getId(), articleName, articleAmount);
                    articlesFacadeLocal.edit(article);
                }
            }
        }
        return "";
    }

    public String loadArticle(int id) {
        Articles articleTemp = articlesFacadeLocal.find(id);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> requestMap = externalContext.getRequestMap();
        requestMap.put("article", articleTemp);
        return "article-update";
    }
}
