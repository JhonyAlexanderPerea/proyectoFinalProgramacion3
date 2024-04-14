package Networking;

import javafx.concurrent.Task;

public class ServidorTask extends Task<Void> {
    private final int puerto;

    public ServidorTask(int puerto) {
        this.puerto = puerto;
    }

    @Override
    protected Void call() {
        servidorArchivos servidor = new servidorArchivos(puerto);
        if (servidor.iniciarServicio()) {
            System.out.println("Servidor iniciado en el puerto " + puerto);
        } else {
            System.err.println("Error al iniciar el servidor.");
        }
        return null;
    }
}
