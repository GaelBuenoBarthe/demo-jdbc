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

        // Update the supplier "France de matériaux" to "France matériaux"
        fournisseurDao.update("France de matériaux", "France matériaux");

        // Display the list of suppliers after update
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après modification:");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }

        // Delete the supplier "France matériaux"
        Fournisseur fournisseurToDelete = new Fournisseur(1, "France matériaux");
        fournisseurDao.delete(fournisseurToDelete);

        // Display the list of suppliers after deletion
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après suppression:");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }

        // Try to insert a supplier with an apostrophe
        Fournisseur fournisseurWithQuote = new Fournisseur(2, "L’Espace Création");
        try {
            fournisseurDao.insert(fournisseurWithQuote);
        } catch (Exception e) {
            System.err.println("Erreur générale lors de l'insertion d'une apostrophe: " + e.getMessage());
            e.printStackTrace();
        }

        // Display the list of suppliers after the attempt to insert with an apostrophe
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après la tentative d'insertion d'une apostrophe:");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }
    }
}