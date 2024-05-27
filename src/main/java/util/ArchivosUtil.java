package util;

import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import model.Articulo;
import com.opencsv.CSVWriter;

public class ArchivosUtil {
    public static boolean crearCarpetaDePublicador(String abreviatura) {

        String feedDeEntradaArticulos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/article/",
                abreviatura,
                "articulos"
        );

        String feedDeEntradaFotos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/photos/",
                abreviatura,
                "fotos"
        );

        File creadorCarpetaArticulos = new File(feedDeEntradaArticulos);
        File creadorCarpetaFotos = new File(feedDeEntradaFotos);

        // Se crean las dos carpetas.
        if(creadorCarpetaArticulos.mkdirs() && creadorCarpetaFotos.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }
    public static void transformarXML(File archivo){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);

            Element root = doc.getDocumentElement();
            String titulo = obtenerValElemento(root, "title");
            String fechaStr = obtenerValElemento(root, "date.issue");
            String publicador = obtenerValElemento(root, "doc.copyright");
            String contenido = obtenerValElemento(root, "body.content");

            System.out.println("Titulo: " + titulo);
            System.out.println("Fecha: " + fechaStr);
            System.out.println("Publicador: " + publicador);
            System.out.println("Contenido: " + contenido);

            Articulo articulo = new Articulo(1, titulo, fechaStr, contenido);

            guardarDatosCSV("src/main/resources/content/articles/articles_csv/", articulo);

            archivo.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String obtenerValElemento(Element elementoPadre, String nombreElemento) {
        NodeList nodeList = elementoPadre.getElementsByTagName(nombreElemento);
        if (nodeList.getLength() > 0) {
            Node nodo = nodeList.item(0);
            return nodo.getTextContent();
        }
        return "";
    }



    public static boolean crearCarpetaDeCliente(String abre) {

        String feedDeRecibirArticulos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/carpetasClientes/articulos/",
                abre,
                "articulos"
        );

        String feedDeRecibirFotos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/carpetasClientes/fotos/",
                abre,
                "fotos"
        );

        File creadorCarpetaArticulosCliente = new File(feedDeRecibirArticulos);
        File creadorCarpetaFotosCliente = new File(feedDeRecibirFotos);

        // Se crean las dos carpetas.
        if(creadorCarpetaArticulosCliente.mkdirs() && creadorCarpetaFotosCliente.mkdirs()) {
            return true;
        } else {
            return false;
        }

    }

    // Método para guardar los datos de la noticia en un archivo CSV
    private static void guardarDatosCSV(String rutaCarpetaSalida, Articulo articulo) {
        File carpetaSalida = new File(rutaCarpetaSalida);
        if (!carpetaSalida.exists()) {
            carpetaSalida.mkdirs(); // Crear la carpeta de salida si no existe
        }

        String nombreArchivoCSV = "datos_noticias.csv";
        File archivoCSV = new File(carpetaSalida, nombreArchivoCSV);

        try (CSVWriter writer = new CSVWriter(new FileWriter(archivoCSV, true))) {
            // Escribir una nueva línea en el archivo CSV con los datos de la noticia
            String[] datosNoticia = { articulo.getTitulo(), articulo.getFechaPublicacion(), "El Tiempo Colombia", articulo.getCuerpoArticulo() };
            writer.writeNext(datosNoticia);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
