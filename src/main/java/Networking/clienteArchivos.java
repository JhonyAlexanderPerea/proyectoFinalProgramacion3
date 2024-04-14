package Networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class clienteArchivos {

    private Socket conector;
    private String direccionIP;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private int puerto;
    private ServerSocket escuchadorDePeticiones;

    public clienteArchivos(String direccionIP, int puerto) {
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }

    public void enviarArchivo(File archivo) {
        String host = "localhost";
        int puerto = 10;

        try (Socket socket = new Socket(host, puerto)) {
            byte[] buffer = new byte[4096];

            // Enviar el nombre del archivo al servidor
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(archivo.getName());

            // Enviar el contenido del archivo al servidor
            try (FileInputStream fileInputStream = new FileInputStream(archivo)) {
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    socket.getOutputStream().write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Archivo enviado correctamente al servidor.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
