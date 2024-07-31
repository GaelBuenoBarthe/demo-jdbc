package fr.diginamic.jdbc.dao.article;

import fr.diginamic.jdbc.entites.Article;
import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {
    void insertArticle(Article article) throws SQLException;
    void setUpdatePrix() throws SQLException;
    List<Article> getAllArticles() throws SQLException;
    double getAveragePrix() throws SQLException;
    void deleteArticlesByName() throws SQLException;
    boolean exists(int id) throws SQLException;
}