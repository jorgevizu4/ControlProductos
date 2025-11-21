package dao;

import model.Producto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductoDAO extends InterfazDAO<Producto> {
    ArrayList<Producto> obtenerPorCategoria(String categoria);
    int actualizarPrecioPorCategoria(double aumento, String categoria);
}
