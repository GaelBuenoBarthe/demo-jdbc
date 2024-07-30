package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fr.diginamic.TestConnexionJdbc.*;

public class ArticleDaoJdbc {

    private static final String INSERT_ARTICLE = "INSERT INTO article (designation, prix, ID_FOU, REF) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PRIX = "UPDATE article SET prix = prix * 0.75 WHERE designation LIKE '%mate%'";
    private static final String SELECT_ALL_ARTICLES = "SELECT * FROM article";
    private static final String AVERAGE_PRIX = "SELECT AVG(prix) AS avg_prix FROM article";
    private static final String DELETE_ARTICLES_BY_NAME = "DELETE FROM article WHERE designation LIKE '%Peinture%'";

    public void insertArticle(Article article) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(INSERT_ARTICLE)) {
            statement.setString(1, article.getDesignation());
            statement.setDouble(2, article.getPrix());
            statement.setInt(3, article.getFournisseur().getId());
            statement.setString(4, article.getRef()); // Assuming Article class has a getRef() method
            statement.executeUpdate();
        }
    }

    public void setUpdatePrix() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRIX)) {
            statement.executeUpdate();
        }
    }

    public List<Article> getAllArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ARTICLES)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String designation = resultSet.getString("designation");
                double prix = resultSet.getDouble("prix");
                int fournisseurId = resultSet.getInt("ID_FOU");
                String ref = resultSet.getString("REF");
                Fournisseur fournisseur = new Fournisseur(fournisseurId, "La Maison de la Peinture"); // Simplified for example
                articles.add(new Article(id, designation, prix, fournisseur)); // Assuming Article class has a constructor with ref
            }
        }
        return articles;
    }

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

    public void deleteArticlesByName() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLES_BY_NAME)) {
            statement.executeUpdate();
        }
    }
}