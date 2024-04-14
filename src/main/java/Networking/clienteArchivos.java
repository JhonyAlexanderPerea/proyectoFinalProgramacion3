package Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
