package Networking;

import java.io.*;
import java.net.Socket;

public class ClienteArchivos {

    private String direccionIP;
    private int puerto;

    public ClienteArchivos(String direccionIP, int puerto) {
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }

    public void enviarArchivo(File archivo) throws IOException {
        try (Socket socket = new Socket(direccionIP, puerto);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             FileInputStream fileInputStream = new FileInputStream(archivo)) {

            // Enviar el nombre del archivo al servidor
            dataOutputStream.writeUTF(archivo.getName());

            // Enviar los datos del archivo al servidor
            byte[] buffer = new byte[8192];
            int count;
            while ((count = fileInputStream.read(buffer)) > 0) {
                dataOutputStream.write(buffer, 0, count);
            }
            dataOutputStream.flush();
        }
    }
}
