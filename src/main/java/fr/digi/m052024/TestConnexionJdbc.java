package fr.digi.m052024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {
    private static final String DB_URL = "jdbc:mariadb://localhost:3308/";
    private static final String DB_USER = "root";
    private static final String DB_PWD = "714131";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        System.out.println("Connexion établie !");
        connection.close();
    }

}