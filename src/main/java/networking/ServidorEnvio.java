package networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorEnvio {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        String rutaCSV = "src/main/resources/content/articles/articles_csv/datos_noticias.csv"; // Ruta del archivo CSV con los datos de los artículos
        procesarEnvioContenido(rutaCSV);
    }

    public static void procesarEnvioContenido(String rutaCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            br.readLine(); // Saltar la línea de encabezado
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) { // Suponiendo que las columnas son: Título, Fecha, Cuerpo
                    String titulo = campos[0];
                    String fecha = campos[2];
                    String cuerpo = campos[3];
                    String contenidoXML = construirXML(titulo, fecha, cuerpo);
                    enviarArchivoXML(titulo, contenidoXML);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String construirXML(String titulo, String fecha, String cuerpo) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<nitf xmlns=\"\" xmlns:xsi=\"\" xsi:schemaLocation=\"\">\n" +
                "  <head>\n" +
                "    <title>" + titulo + "</title>\n" +
                "    <docdata>\n" +
                "      <date.issue>" + fecha + "</date.issue>\n" +
                "      <doc.copyright>El Tiempo Colombia</doc.copyright>\n" +
                "    </docdata>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <body.content>" + cuerpo + "</body.content>\n" +
                "  </body>\n" +
                "</nitf>";
    }

    private static void enviarArchivoXML(String titulo, String contenidoXML) {
        try {
            String nombreArchivo = titulo.replaceAll("[^a-zA-Z0-9]", "_") + ".xml";
            Path archivoXML = Paths.get("temporal", nombreArchivo);
            Files.createDirectories(archivoXML.getParent());
            Files.write(archivoXML, contenidoXML.getBytes());

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Servidor escuchando en el puerto " + PORT);

                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());
                enviarArchivo(clientSocket, archivoXML.toString());
                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarArchivo(Socket clientSocket, String filePath) throws IOException {
        File archivo = new File(filePath);
        byte[] byteArray = new byte[(int) archivo.length()];

        try (FileInputStream fis = new FileInputStream(archivo);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = clientSocket.getOutputStream()) {

            bis.read(byteArray, 0, byteArray.length);
            os.write(byteArray, 0, byteArray.length);
            os.flush();
        }
    }
}
