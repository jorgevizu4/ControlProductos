package operaciones;

import model.Producto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class Operaciones {

    public ArrayList<Producto> listaProductos() {
        ArrayList<Producto> lista = new ArrayList<>();

        lista.add(new Producto(1, "Televisión", 200, "Hogar"));
        lista.add(new Producto(2, "Placa base", 100, "Ordenador"));
        lista.add(new Producto(3, "Ratón", 10, "Ordenador"));
        lista.add(new Producto(4, "Procesador", 200, "Ordenador"));
        lista.add(new Producto(5, "Monitor", 50, "Ordenador"));

        return lista;
    }

    public void escritorObjetos(String path) {
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        ArrayList<Producto> lista = listaProductos();


        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {

            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            for (Producto producto : lista) {
                oos.writeObject(producto);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("No tienes permisos de escritura");
        } finally {
            try {
                oos.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado del fichero");
            }
        }
    }

    public void leerObjeto(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            Producto producto = null;
            while ((producto = (Producto) ois.readObject()) != null) {
                System.out.println(producto.getId() + ": " + producto.getNombre() + ", " + producto.getPrecio() + ", " + producto.getCategoria());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");
        } catch (ClassNotFoundException | ClassCastException e) {
            System.out.println("Error en la clase de lectura");
        } finally {
            try {
                ois.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    public void generarXML() {
        ArrayList<Producto> listaProductos = listaProductos();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element nodoRoot = document.createElement("productos");
            document.appendChild(nodoRoot);

            for (Producto producto : listaProductos) {
                Element nodoProducto = document.createElement("producto");
                nodoProducto.setAttribute("id", String.valueOf(producto.getId()));
                nodoProducto.setAttribute("categoria", producto.getCategoria());
                nodoRoot.appendChild(nodoProducto);

                Element nodoNombre = document.createElement("nombre");
                nodoNombre.setTextContent(producto.getNombre());
                nodoProducto.appendChild(nodoNombre);
                Element nodoPrecio = document.createElement("precio");
                nodoPrecio.setTextContent(String.valueOf(producto.getPrecio()));
                nodoProducto.appendChild(nodoPrecio);
                Element nodoCategoria = document.createElement("categoria");
                nodoCategoria.setTextContent(producto.getCategoria());
                nodoProducto.appendChild(nodoCategoria);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src/main/java/resources/productos.xml"));
            transformer.transform(domSource, result);


        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error en el parseo del documento");
        }
    }
}
