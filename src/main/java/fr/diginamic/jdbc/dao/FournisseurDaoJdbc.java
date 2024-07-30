package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fr.diginamic.TestConnexionJdbc.*;

public class FournisseurDaoJdbc implements FournisseurDao {

    private static final String INSERT_FOURNISSEUR = "INSERT INTO fournisseur (id, nom) VALUES (?, ?)";
    private static final String SELECT_ALL_FOURNISSEURS = "SELECT * FROM fournisseur";
    private static final String UPDATE_FOURNISSEUR = "UPDATE fournisseur SET nom = ? WHERE nom = ?";
    private static final String DELETE_FOURNISSEUR = "DELETE FROM fournisseur WHERE id = ?";
    private static final String EXISTS_FOURNISSEUR = "SELECT COUNT(*) FROM fournisseur WHERE id = ?";

    @Override
    public void insert(Fournisseur fournisseur) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(INSERT_FOURNISSEUR)) {
            statement.setInt(1, fournisseur.getId());
            statement.setString(2, fournisseur.getNom());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Fournisseur> extraire() throws SQLException {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FOURNISSEURS)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                fournisseurs.add(new Fournisseur(id, nom));
            }
        }
        return fournisseurs;
    }

    @Override
    public int update(String oldName, String newName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_FOURNISSEUR)) {
            statement.setString(1, newName);
            statement.setString(2, oldName);
            statement.executeUpdate();
        }
        return 0;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(DELETE_FOURNISSEUR)) {
            statement.setInt(1, fournisseur.getId());
            statement.executeUpdate();
        }
        return false;
    }

    @Override
    public void insertFournisseur(Fournisseur fournisseur) {

    }

    @Override
    public void deleteFournisseur(int id) {

    }

    public boolean exists(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             PreparedStatement statement = connection.prepareStatement(EXISTS_FOURNISSEUR)) {
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
