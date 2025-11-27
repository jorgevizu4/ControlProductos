package dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public interface InterfazDAO<T> {
    boolean insertarDato(T data) throws SQLException;
    ArrayList<T> obtenerListaDatos();
    int actualizarDato(T datoNuevo, String nombre) throws SQLException;
    int borrarDatos(int id);
}
