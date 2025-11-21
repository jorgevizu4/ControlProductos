package main;

import database.ConexionSingleton;
import operaciones.Operaciones;

import java.sql.Connection;
import java.sql.SQLException;

public class Testing {
    public static void main(String[] args) {
        Operaciones op = new Operaciones();

        //op.escritorObjetos("src/main/java/resources/productos.dat");
        //op.leerObjeto("src/main/java/resources/productos.dat");
        //op.generarXML();
        Connection connection = ConexionSingleton.getConnection();
        try {
            connection.getCatalog();
        } catch (SQLException e) {
            System.out.println("Error en la sentencia");
        }
    }
}
