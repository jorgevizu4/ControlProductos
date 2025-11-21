package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionSingleton {
    private static Connection connection;

    public static Connection getConnection() {
        if(connection==null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/database.properties");
            properties.load(fis);

            String url = "jdbc:mysql://localhost:3306/techmarketdb";
            String user = "root";
            String pass = "root";

            connection = DriverManager.getConnection(url, user, pass);

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");;
        } catch (IOException ex) {
            System.out.println("No tienes permisos de acceso");
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL");
        }
    }
}
