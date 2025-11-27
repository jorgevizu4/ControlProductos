package main;

import com.google.protobuf.DescriptorProtos;
import controller.PeticionesController;
import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import model.Producto;

import java.util.Scanner;

public class TestingDAO {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        PeticionesController peticionesController = new PeticionesController();
        Scanner scanner = new Scanner(System.in);
        ProductoDAOImpl productoDAOImpl = new ProductoDAOImpl();
        int opcion;

        do {
            System.out.println("\n--------------------------------");
            System.out.println("SISTEMA DE GESTIÓN DE PRODUCTOS");
            System.out.println("1. Insertar producto.");
            System.out.println("2. Borrar producto.");
            System.out.println("3. Actualizar producto.");
            System.out.println("4. Listar producto.");
            System.out.println("5. Listar productos por categoria.");
            System.out.println("6. Importar productos desde XML.");
            System.out.println("7. Actualizar precio por categoria.");
            System.out.println("8. Salir.");
            System.out.println("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Indica nombre del producto:");
                    String nombre = scanner.next();
                    System.out.println("Indica precio del producto:");
                    double precio = scanner.nextDouble();
                    System.out.println("Indica categoria del producto:");
                    String categoria = scanner.nextLine();
                    peticionesController.insertarProducto(new Producto(nombre, precio, categoria));
                }
                case 2 -> {
                    System.out.println("Indica el id del producto");
                    int id = scanner.nextInt();
                    peticionesController.borrarProducto(id);
                }
                case 3 -> {
                    System.out.println("Introduce el nombre del producto a actualizar");
                    String nombreProd = scanner.next();
                    System.out.println("Indica nuevo nombre del producto:");
                    String nombre = scanner.next();
                    System.out.println("Indica nuevo precio del producto:");
                    double precio = scanner.nextDouble();
                    System.out.println("Indica nueva categoría del producto:");
                    String categoria = scanner.next();
                    peticionesController.actualizarPorducto(new Producto(nombre,precio,categoria), nombreProd);
                }
                case 4 -> {
                    peticionesController.listarProductos();
                }
                case 5 -> {
                    System.out.println("Indique una categoría.");
                    String cat = scanner.next();
                    peticionesController.listarPorCategoria(cat);
                }
                case 6 -> {
                    peticionesController.importacionXML();
                }
                case 7 -> {
                    System.out.println("Indica el porcentaje de subida");
                    double aumento = scanner.nextDouble();
                    System.out.println("Indica la categoria");
                    String categoria = scanner.next();
                    peticionesController.aumentarPrecioPorCategoria(aumento, categoria);
                }
                case 8 -> {
                    System.out.println("Fin del programa.");
                }
                default -> {
                    System.out.println("Opción no válida");
                }
            }
        } while (opcion!=8);
        scanner.close();
    }
}
