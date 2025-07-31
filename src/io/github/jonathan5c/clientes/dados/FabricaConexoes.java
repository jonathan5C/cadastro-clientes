package io.github.jonathan5c.clientes.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexoes {

    private static String url = "jdbc:postgresql://localhost:5432/clientes";
    private static String username = "postgres";
    private static String password = "postgres";

    public static Connection criarConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            var conexao = DriverManager.getConnection(url, username, password);
            return conexao;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
