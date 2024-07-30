package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static fr.diginamic.TestConnexionJdbc.*;

public class FournisseurDaoJdbc2 implements FournisseurDao {


    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String sql = "SELECT id, nom FROM fournisseur";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

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
        String sql = "INSERT INTO fournisseur (id, nom) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, fournisseur.getId());
            statement.setString(2, fournisseur.getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        String sql = "UPDATE fournisseur SET nom = ? WHERE nom = ?";
        int rowsUpdated = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nouveauNom);
            statement.setString(2, ancienNom);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        String sql = "DELETE FROM fournisseur WHERE id = ?";
        boolean rowDeleted = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, fournisseur.getId());
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}