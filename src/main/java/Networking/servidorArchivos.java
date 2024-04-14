package Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorArchivos {

    private Socket conector;
    private ServerSocket escuchadorDePeticiones;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private int puerto;

    public servidorArchivos(int puerto) {
        this.puerto = puerto;
    }

    public void cerrarRecursos() {
        try {
            this.flujoEntrada.close();
            this.flujoSalida.close();
            this.conector.close();
            this.escuchadorDePeticiones.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean iniciarServicio() {
        try {
            System.out.println("Iniciando servidor de gestión de Sockets.");

            this.escuchadorDePeticiones 	= new ServerSocket(this.puerto);
            this.conector 					= this.escuchadorDePeticiones.accept();
            flujoEntrada 					= new DataInputStream(this.conector.getInputStream());
            flujoSalida						= new DataOutputStream(this.conector.getOutputStream());

            System.out.println("Se inició el servidor y se inicializaron los flujos de entrada y salida.");

            recibirArchivo();
            System.out.println("Se recibió el archivo.");
            return true;
        }
        catch(Exception e) {
            return false;
        }
        finally {
            cerrarRecursos();
        }
    }

    public void recibirArchivo() {
        try {
            int bytes = 0;
            FileOutputStream flujoSalidaArchivo = new FileOutputStream(
                    "C:\\Users\\lenovo\\Documents\\grupo_dia\\feed_de_entrada\\ETC\\cargado1.txt");
            long tamaño = this.flujoEntrada.readLong();
            byte[] buffer = new byte[4 * 1024];
            while(
                    tamaño > 0 &&
                            ( bytes = this.flujoEntrada.read(buffer, 0, (int) Math.min(buffer.length, tamaño)) ) != -1
            )
            {
                this.flujoSalida.write(buffer, 0, bytes);
                tamaño -= bytes;
            }

            flujoSalidaArchivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
