package dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public interface InterfazDAO<T> {
    boolean insertarDato(T data) throws SQLException;
    ArrayList<T> obtenerListaDatos();
    boolean actualizarDato(T datoNuevo);
    int borrarDatos(int id);
}
