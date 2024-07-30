package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static fr.diginamic.TestConnexionJdbc.*;

public class FournisseurDaoJdbc implements FournisseurDao {


    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String sql = "SELECT id, nom FROM fournisseur";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Fournisseur fournisseur = new Fournisseur(id, nom);
                fournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fournisseurs;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        String sql = "INSERT INTO fournisseur (id, nom) VALUES (" + fournisseur.getId() + ", '" + fournisseur.getNom() + "')";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        String sql = "UPDATE fournisseur SET nom = '" + nouveauNom + "' WHERE nom = '" + ancienNom + "'";
        int rowsUpdated = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement()) {

            rowsUpdated = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        String sql = "DELETE FROM fournisseur WHERE id = " + fournisseur.getId();
        boolean rowDeleted = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement()) {

            rowDeleted = statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public void deleteFournisseur(int id) {

    }

    @Override
    public void insertFournisseur(Fournisseur fournisseur) {

    }
}