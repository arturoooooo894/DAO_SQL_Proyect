package conexion;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class conexion {
    private static Connection connection;

    static {
        conectar();
    }

    private static void conectar() {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("db.properties")) {
            Properties p = new Properties();

            if (input == null) {
                System.out.println("No se encontró el archivo db.properties en el classpath.");
                return;
            }

            p.load(input);
            String url = p.getProperty("db.url");
            String user = p.getProperty("db.user");
            String password = p.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Error al establecer la conexión con la base de datos", e);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                conectar();  // Reintenta conectar si está cerrada
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar la conexión", e);
        }
        return connection;
    }
}

