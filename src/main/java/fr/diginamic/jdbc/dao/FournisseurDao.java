package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import java.sql.SQLException;
import java.util.List;

public interface FournisseurDao {
    void insert(Fournisseur fournisseur) throws SQLException;

    List<Fournisseur> extraire() throws SQLException;

    int update(String oldName, String newName) throws SQLException;

    boolean delete(Fournisseur fournisseur) throws SQLException;

    void insertFournisseur(Fournisseur fournisseur);

    void deleteFournisseur(int id);
}