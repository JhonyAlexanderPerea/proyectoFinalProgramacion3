package networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorArchivos {

    private int puerto;
    private String rutaDirectorio = "src/main/resources/content/";
    private volatile boolean ejecutando = true; // Flag para controlar la ejecución del servidor
    private ServerSocket serverSocket;

    public ServidorArchivos(int puerto) {
        this.puerto = puerto;
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (ejecutando) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                // Iniciar un nuevo hilo para manejar la conexión con el cliente
                new Thread(() -> manejarConexionCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            if (!ejecutando) {
                // Si se interrumpe el servidor de forma intencionada, no mostrar error
                System.out.println("Servidor cerrado");
            } else {
                e.printStackTrace();
            }
        } finally {
            cerrarServidor(); // Asegurarse de cerrar el servidor cuando se sale del bucle
        }
    }

    private void manejarConexionCliente(Socket clienteSocket) {
        try {
            // Obtener el nombre del archivo desde el cliente
            DataInputStream dataInputStream = new DataInputStream(clienteSocket.getInputStream());
            String nombreArchivo = dataInputStream.readUTF();

            // Crear el archivo en la ruta deseada
            File archivo = new File(rutaDirectorio + nombreArchivo);

            // Guardar el archivo recibido en la ruta deseada
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            byte[] buffer = new byte[8192];
            int count;
            while ((count = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.close();
            System.out.println("Archivo recibido y guardado en " + archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clienteSocket.close(); // Cerrar conexión con el cliente
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cerrarServidor() {
        ejecutando = false; // Establecer el flag en false para terminar el bucle del servidor
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Cerrar el ServerSocket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
