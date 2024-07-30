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
            fournisseurDao.insertFournisseur(fournisseur);

            // Insertion d'un article
            articleDao.insertArticle(new Article(1, "Peinture blanche 1L", 12.5, fournisseur));
            articleDao.insertArticle(new Article(2, "Peinture rouge mate 1L", 15.5, fournisseur));
            articleDao.insertArticle(new Article(3, "Peinture noire laquée 1L", 17.8, fournisseur));
            articleDao.insertArticle(new Article(4, "Peinture bleue mate 1L", 15.5, fournisseur));

            // Modification des prix
            articleDao.setUpdatePrix();

            // Recuperer et afficher tous les articles
            List<Article> articles = articleDao.getAllArticles();
            articles.forEach(System.out::println);

            // Calcule et affiche le prix moyen
            double averagePrix = articleDao.getAveragePrix();
            System.out.println("Prix moyen: " + averagePrix);

            // Supprime un article par nom
            articleDao.deleteArticlesByName();

            // Supprimer un fournisseur
            fournisseurDao.deleteFournisseur(fournisseur.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}