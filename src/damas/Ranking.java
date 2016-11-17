/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author tsuzukayama
 */
public class Ranking {

    public Ranking() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE RANKING"
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                    + "nome VARCHAR(40) NOT NULL,"
                    + "jogadas INTEGER NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("criou");
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void addJogador(String nome, int jogadas) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO RANKING(NOME,JOGADAS) VALUES ('" + nome + "'," + jogadas + ")";
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public static Object[][] getRanking() {
        Connection c = null;
        Statement stmt = null;
        ArrayList[] teste = new ArrayList[2];
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Integer> tempo = new ArrayList<>();
        Object[][] result = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "SELECT COUNT(*) AS rowcount FROM RANKING";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int i = rs.getInt("rowcount");
            System.out.println(i);
            sql = "SELECT * FROM RANKING";
            rs = stmt.executeQuery(sql);
            int j = rs.getMetaData().getColumnCount();
            System.out.println(j);
            result = new Object[i][j];
            rs.next();
            for (int k = 0; k < i; k++) {
                result[k][0] = rs.getString("nome");
                for (int l = 1; l < 2; l++) {
                    result[k][l] = rs.getInt("jogadas");
                }
                rs.next();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return result;
    }
}
