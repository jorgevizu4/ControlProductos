package controller;

import dao.ProductoDAOImpl;
import model.Producto;
import model.ProductoXML;

import javax.swing.undo.UndoableEditSupport;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

public class PeticionesController {

    private ProductoDAOImpl productoDAOImpl;

    public PeticionesController() {
        productoDAOImpl = new ProductoDAOImpl();
    }

    public void insertarProducto(Producto producto) {
        boolean fallo = false;
        do {
            try {
                productoDAOImpl.insertarDato(producto);
                fallo = false;
                System.out.println("Producto añadido correctamente");
            } catch (SQLException e) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("El nombre ya existe.");
                System.out.println("Introduce un nuevo nombre.");
                String nombre = scanner.next();
                producto.setNombre(nombre);
                scanner.close();
                fallo = true;
            }
        } while (fallo);
    }

    public void borrarProducto(int id) {
        int rows = productoDAOImpl.borrarDatos(id);
        if (rows > 1) {
            System.out.println("Productos borrado correctamente");
        } else if (rows == 1) {
            System.out.println("Producto borrado correctamente");
        } else if (rows == 0) {
            System.out.println("No se ha encontrado producto con ese id");
        } else {
            System.out.println("Fallo en el proceso");
        }
    }

    public void listarProductos() {
        for (Producto producto : productoDAOImpl.obtenerListaDatos()) {
            producto.mostrarDatos();
        }
    }

    public void importacionXML() {
        JAXBContext context = null;
        Unmarshaller unmarshaller;
        try {
            context = JAXBContext.newInstance(ProductoXML.class);
            unmarshaller = context.createUnmarshaller();
            ProductoXML productoXML = (ProductoXML) unmarshaller.unmarshal(new File("src/main/java/resources/productos.xml"));
            for (Producto producto : productoXML.getLista()) {
                productoDAOImpl.insertarDato(producto);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void aumentarPrecioPorCategoria(double precio, String categoria) {
        int rows = productoDAOImpl.actualizarPrecioPorCategoria(precio, categoria);
        boolean fallo = false;
        do {
            if (rows > 0) {
                System.out.println("Precio actualizado correctamente para los productos de " + categoria);
                fallo = false;
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("La categoría especificada no existe.");
                System.out.println("Introduzca otra categoria:");
                categoria = scanner.next();
                scanner.close();
                fallo = true;
            }
        } while (fallo);
    }
}
