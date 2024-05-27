package networking;

import java.io.*;
import java.net.Socket;

public class ClienteArchivos {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final String OUTPUT_DIRECTORY = "src/main/resources/carpetasClientes/articulos/";

    public static void conexionServidor() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conectado al servidor");

            recibirArchivo(socket);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recibirArchivo(Socket socket) throws IOException {
        byte[] byteArray = new byte[8192];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(OUTPUT_DIRECTORY + "/archivo_recibido.xml");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int bytesRead;
        while ((bytesRead = is.read(byteArray, 0, byteArray.length)) != -1) {
            bos.write(byteArray, 0, bytesRead);
        }
        bos.flush();
        bos.close();
        fos.close();
        is.close();
    }
}

