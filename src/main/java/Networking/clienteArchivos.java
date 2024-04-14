package Networking;

import java.io.*;
import java.net.Socket;

public class clienteArchivos {

    private Socket conector;
    private String direccionIP;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private int puerto;

    public clienteArchivos(String direccionIP, int puerto) {
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }

    public boolean iniciarConexionConServidor() {
        try {
            this.conector 		= new Socket(this.direccionIP, this.puerto);
            this.flujoEntrada 	= new DataInputStream(this.conector.getInputStream());
            this.flujoSalida 	= new DataOutputStream(conector.getOutputStream());

            return true;
        } catch(Exception e) {
            return false;
        } finally {
            // TODO
        }
    }
    public void enviarArchivo(String rutaArchivo) {
        try {
            // Abrir el archivo
            FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            // Obtener el tamaño del archivo
            long tamanoArchivo = fileInputStream.available();

            // Enviar el nombre del archivo y su tamaño al servidor
            flujoSalida.writeUTF(new File(rutaArchivo).getName());
            flujoSalida.writeLong(tamanoArchivo);

            // Crear un buffer para leer el archivo en bloques
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                // Escribir los bytes en el flujo de salida
                flujoSalida.write(buffer, 0, bytesRead);
            }

            // Cerrar recursos
            bufferedInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarRecursos() {
        try {
            this.flujoEntrada.close();
            this.flujoSalida.close();
            this.conector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public DataInputStream getFlujoEntrada() {
        return flujoEntrada;
    }

    public void setFlujoEntrada(DataInputStream flujoEntrada) {
        this.flujoEntrada = flujoEntrada;
    }

    public DataOutputStream getFlujoSalida() {
        return flujoSalida;
    }

    public void setFlujoSalida(DataOutputStream flujoSalida) {
        this.flujoSalida = flujoSalida;
    }
}
