package fr.diginamic.jdbc.dao.article;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fr.diginamic.jdbc.test.TestConnexionJdbc.*;

public class ArticleDaoJdbc implements ArticleDao {

    private static final String INSERT_ARTICLE = "INSERT INTO article (id, ref, designation, prix, ID_FOU) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRIX = "UPDATE article SET prix = prix * 0.75 WHERE designation LIKE '%mate%'";
    private static final String SELECT_ALL_ARTICLES = "SELECT * FROM article";
    private static final String AVERAGE_PRIX = "SELECT AVG(prix) AS avg_prix FROM article";
    private static final String DELETE_ARTICLES_BY_NAME = "DELETE FROM article WHERE designation LIKE '%Peinture%'";
    private static final String EXISTS_ARTICLE = "SELECT COUNT(*) FROM article WHERE id = ?";

    @Override
    public void insertArticle(Article article) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(INSERT_ARTICLE)) {
            statement.setInt(1, article.getId());
            statement.setString(2, article.getRef());
            statement.setString(3, article.getDesignation());
            statement.setDouble(4, article.getPrix());
            statement.setInt(5, article.getFournisseur().getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void setUpdatePrix() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRIX)) {
            statement.executeUpdate();
        }
    }

    @Override
    public List<Article> getAllArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ARTICLES)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ref = resultSet.getString("ref");
                String designation = resultSet.getString("designation");
                double prix = resultSet.getDouble("prix");
                int fournisseurId = resultSet.getInt("ID_FOU");
                Fournisseur fournisseur = new Fournisseur(fournisseurId, "La Maison de la Peinture"); // Simplified for example
                articles.add(new Article(id, ref, designation, prix, fournisseur));
            }
        }
        return articles;
    }

    @Override
    public double getAveragePrix() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(AVERAGE_PRIX)) {
            if (resultSet.next()) {
                return resultSet.getDouble("avg_prix");
            }
        }
        return 0;
    }

    @Override
    public void deleteArticlesByName() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLES_BY_NAME)) {
            statement.executeUpdate();
        }
    }

    @Override
    public boolean exists(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(EXISTS_ARTICLE)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
