package service.businessObject;

import Service.modelbehavior.GestorServiciosDeArchivos;

public class ProcesosBusinessObject {
    public void enviarArchivo(String ruta) {
        GestorServiciosDeArchivos archivos = new GestorServiciosDeArchivos();
        archivos.enviarArchivo(ruta, "127.0.0.1", 5000);
    }

    public void iniciarReceptorArchivos() {
        GestorServiciosDeArchivos archivos = new GestorServiciosDeArchivos();
        archivos.iniciarServicioRecepcion(5000);
    }
}

