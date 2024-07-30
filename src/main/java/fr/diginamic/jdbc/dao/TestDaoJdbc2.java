package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import java.sql.SQLException;
import java.util.List;

public class TestDaoJdbc2 {
    public static void main(String[] args) throws SQLException {
        FournisseurDao fournisseurDao = new FournisseurDaoJdbc2();

        // Insere le fournisseur "France de matériaux"
        Fournisseur fournisseur1 = new Fournisseur(1, "France de matériaux");
        fournisseurDao.insert(fournisseur1);

        // Affiche la liste des fournisseurs
        List<Fournisseur> fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après insertion:");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }

        // Maj du fournisseur "France de matériaux" en "France matériaux"
        fournisseurDao.update("France de matériaux", "France matériaux");

        // Affiche la liste des fournisseurs modifiée
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après modification:");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }

        // Supprime le fournisseur "France matériaux"
        Fournisseur fournisseurToDelete = new Fournisseur(1, "France matériaux");
        fournisseurDao.delete(fournisseurToDelete);

        // Affiche la liste des fournisseurs après suppression
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après suppression:");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }

        // Essaye d'insérer un fournisseur avec une apostrophe
        Fournisseur fournisseurWithQuote = new Fournisseur(2, "L’Espace Création");
        try {
            fournisseurDao.insert(fournisseurWithQuote);
        } catch (Exception e) {
            System.err.println("Erreur générale lors de l'insertion d'une apostrophe: " + e.getMessage());
            e.printStackTrace();
        }

        // Affiche la liste des fournisseurs après la tentative d'ajout avec une apostrophe
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après la tentative d'insertion d'une apostrophe:");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }
    }
}