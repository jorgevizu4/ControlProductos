package dao;

import database.ConexionSingleton;
import database.InterfazDB;
import model.Producto;

import java.sql.*;
import java.util.ArrayList;

public class ProductoDAOImpl implements ProductoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductoDAOImpl() {
        connection = ConexionSingleton.getConnection();
    }

    @Override
    public ArrayList<Producto> obtenerPorCategoria(String categoria) {
        return null;
    }

    @Override
    public int actualizarPrecioPorCategoria(double aumento, String categoria) {
        String query = String.format("UPDATE %s SET %s = %s + %s * (?/100) WHERE %s = ?",
                InterfazDB.TAB_NAME,
                InterfazDB.COL_PRECIO, InterfazDB.COL_PRECIO, InterfazDB.COL_PRECIO, InterfazDB.COL_CATEGORIA);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, aumento);
            preparedStatement.setString(2, categoria);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL");
        }
        return 0;
    }

    @Override
    public boolean insertarDato(Producto data) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                InterfazDB.TAB_NAME,
                InterfazDB.COL_NAME, InterfazDB.COL_PRECIO, InterfazDB.COL_CATEGORIA);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, data.getNombre());
        preparedStatement.setDouble(2, data.getPrecio());
        preparedStatement.setString(3, data.getCategoria());

        return preparedStatement.execute();
    }

    @Override
    public ArrayList<Producto> obtenerListaDatos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FORM " + InterfazDB.TAB_NAME);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(InterfazDB.COL_ID);
                String name = resultSet.getString(InterfazDB.COL_NAME);
                double precio = resultSet.getDouble(InterfazDB.COL_PRECIO);
                String categoria = resultSet.getString(InterfazDB.COL_CATEGORIA);
                Producto producto = new Producto(id, name, precio, categoria);
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaProductos;
    }

    @Override
    public boolean actualizarDato(Producto datoNuevo) {
        return false;
    }

    @Override
    public int borrarDatos(int id) {
        String query = String.format("DELETE FROM %s WHERE %s=?",
                InterfazDB.TAB_NAME, InterfazDB.COL_ID);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL");
        }
        return -1;
    }
}
