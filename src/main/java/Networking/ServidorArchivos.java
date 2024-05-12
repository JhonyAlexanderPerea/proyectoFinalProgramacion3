package Networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorArchivos {

    private int puerto;
    private String rutaDirectorio = "archivos/";

    public ServidorArchivos(int puerto) {
        this.puerto = puerto;
    }

    public void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                new Thread(() -> {
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
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}