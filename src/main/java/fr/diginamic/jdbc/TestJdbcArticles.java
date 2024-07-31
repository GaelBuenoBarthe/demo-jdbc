package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.SQLException;
import java.util.List;

public class TestJdbcArticles {

    public static void main(String[] args) {
        FournisseurDaoJdbc fournisseurDao = new FournisseurDaoJdbc();
        ArticleDaoJdbc articleDao = new ArticleDaoJdbc();

        try {
            // Insertion d'un fournisseur
            Fournisseur fournisseur = new Fournisseur(1, "La Maison de la Peinture");
            if (!fournisseurDao.exists(fournisseur.getId())) {
                fournisseurDao.insertFournisseur(fournisseur);
            }

            // Insertion d'articles
            Article article1 = new Article(1, "REF001", "Peinture blanche 1L", 12.5, fournisseur);
            if (!articleDao.exists(article1.getId())) {
                articleDao.insertArticle(article1);
            }

            Article article2 = new Article(2, "REF002", "Peinture rouge mate 1L", 15.5, fournisseur);
            if (!articleDao.exists(article2.getId())) {
                articleDao.insertArticle(article2);
            }

            Article article3 = new Article(3, "REF003", "Peinture noire laquée 1L", 17.8, fournisseur);
            if (!articleDao.exists(article3.getId())) {
                articleDao.insertArticle(article3);
            }

            Article article4 = new Article(4, "REF004", "Peinture bleue mate 1L", 15.5, fournisseur);
            if (!articleDao.exists(article4.getId())) {
                articleDao.insertArticle(article4);
            }

            // Modification des prix
            articleDao.setUpdatePrix();

            // Récupérer et afficher tous les articles
            List<Article> articles = articleDao.getAllArticles();
            articles.forEach(System.out::println);

            // Calculer et afficher le prix moyen
            double averagePrix = articleDao.getAveragePrix();
            System.out.println("Prix moyen: " + String.format("%.2f", averagePrix) + "€");

            // Supprimer des articles par nom
            articleDao.deleteArticlesByName();

            // Supprimer un fournisseur
            fournisseurDao.deleteFournisseur(fournisseur.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}