package edu.xalead.cms.dao;

import edu.xalead.cms.entity.Article;
import java.sql.Connection;
import java.util.List;

public interface ArticleDao {
    List<Article> findAllArticle(Connection coon);

    void addArticle(Article article, Connection conn);

    void deleteArticleByid(int aid, Connection conn);

    Article findArticleByid(int aid, Connection conn);

    void updateArticle(Article article, Connection conn);

    int findArticlecount(Connection conn);

    List<Article> findPageArticle(int offset, int pagesize, Connection conn);
}
