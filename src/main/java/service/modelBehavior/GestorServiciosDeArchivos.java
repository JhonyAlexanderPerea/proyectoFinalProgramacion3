package service.modelBehavior;
/*
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import networking.ClienteArchivos;
import networking.ServidorArchivos;

public class GestorServiciosDeArchivos {

    private ClienteArchivos emisorArchivos;

    public GestorServiciosDeArchivos() {
        super();
    }

    public void enviarArchivo(String ruta, String direccionIP, int puerto) {
        try {
            iniciarEmisorArchivos(direccionIP, puerto);

            int bytes = 0;
            File archivo = new File(ruta);
            FileInputStream flujoEntrada = new FileInputStream(archivo);
            this.emisorArchivos.getFlujoSalida().writeLong(archivo.length());
            byte[] buffer = new byte[4 * 1024];
            while( (bytes = flujoEntrada.read(buffer)) != -1 ) {
                this.emisorArchivos.getFlujoSalida().write(buffer, 0, bytes);
                this.emisorArchivos.getFlujoSalida().flush();
            }
            flujoEntrada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciarServicioRecepcion(int puerto) {
        ServidorArchivos servidorArchivos = new ServidorArchivos(puerto);
        servidorArchivos.iniciarServicio();
    }

    public void iniciarEmisorArchivos(String direccionIP, int puerto) {
        this.emisorArchivos = new ClienteArchivos(direccionIP, puerto);
        this.emisorArchivos.iniciarConexionConServidor();
    }
}
*/



