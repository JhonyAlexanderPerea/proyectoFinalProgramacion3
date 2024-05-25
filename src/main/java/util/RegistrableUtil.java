package util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RegistrableUtil {

    public static final String ESTADO_PROCESAMIENTO_EXITO = "exito";
    public static final String ESTADO_PROCESAMIENTO_FALLO = "fallo";
    public static final String ESTADO_PROCESAMIENTO_ERROR = "error";

    protected Map<String, LinkedList<String>> mensajes;
    protected boolean logActivo;
    protected String estadoProcesamiento;
    protected Logger registrarLog;

   public RegistrableUtil() {
        try {
            this.mensajes = new TreeMap<>();
            this.mensajes.put(RegistrableUtil.ESTADO_PROCESAMIENTO_EXITO, new LinkedList<String>());
            this.mensajes.put(RegistrableUtil.ESTADO_PROCESAMIENTO_FALLO, new LinkedList<String>());
            this.mensajes.put(RegistrableUtil.ESTADO_PROCESAMIENTO_ERROR, new LinkedList<String>());

            this.logActivo = true;

            if(this.logActivo)
            {
                SimpleFormatter formateadorMensaje = new SimpleFormatter();
                FileHandler controladorArchivoLog =
                       new FileHandler("src/main/java/Logs/proyecto.log");
                controladorArchivoLog.setFormatter(formateadorMensaje);
                this.registrarLog = Logger.getLogger("proyecto");
                this.registrarLog.addHandler(controladorArchivoLog);
            }

            // Se inicializa el estado de la aplicación como exitoso.
            this.estadoProcesamiento = RegistrableUtil.ESTADO_PROCESAMIENTO_EXITO;
        } catch(SecurityException | IOException excep) {
            excep.printStackTrace();
            System.out.println("Error: " + excep.getMessage());
        }
   }


    public void agregarMensaje(String tipoMensaje, String mensaje) {
        agregarMensaje(tipoMensaje, mensaje, false);
    }


    public void agregarMensaje(String tipoMensaje, String mensaje, boolean adjuntar) {
        if(RegistrableUtil.ESTADO_PROCESAMIENTO_ERROR != this.estadoProcesamiento)
            this.estadoProcesamiento = tipoMensaje;
        escribirLog(tipoMensaje, mensaje);

        if(true == adjuntar)
            this.mensajes.get(tipoMensaje).add(mensaje);
    }

   public void escribirLog(String tipoMensaje, String message) {
        if(this.logActivo)
        {
            if(RegistrableUtil.ESTADO_PROCESAMIENTO_ERROR == tipoMensaje) {
                this.registrarLog.severe(message);
            } else if(RegistrableUtil.ESTADO_PROCESAMIENTO_FALLO == tipoMensaje) {
                this.registrarLog.warning(message);
            } else {
                this.registrarLog.info(message);
            }
        }
    }

    public void logAdvertencia(String mensaje) {
        logAdvertencia(mensaje, false);
    }

    public void logAdvertencia(String mensaje, boolean adjuntar) {
        agregarMensaje(RegistrableUtil.ESTADO_PROCESAMIENTO_FALLO, mensaje);
    }

    public void logError(String mensaje) {
        logError(mensaje, false);
    }

    public void logError(String mensaje, boolean adjuntar) {
        agregarMensaje(RegistrableUtil.ESTADO_PROCESAMIENTO_ERROR, mensaje);
    }

    public void logInfo(String mensaje) {
        logInfo(mensaje, false);
    }

    public void logInfo(String mensaje, boolean adjuntar) {
        agregarMensaje(RegistrableUtil.ESTADO_PROCESAMIENTO_EXITO, mensaje);
    }


    public Map<String, LinkedList<String>> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Map<String, LinkedList<String>> mensajes) {
        this.mensajes = mensajes;
    }

    public boolean isLogActivo() {
        return logActivo;
    }

    public void setLogActivo(boolean logActivo) {
        this.logActivo = logActivo;
    }

    public String getEstadoProcesamiento() {
        return estadoProcesamiento;
    }

    public void setEstadoProcesamiento(String estadoProcesamiento) {
        this.estadoProcesamiento = estadoProcesamiento;
    }

    public Logger getRegistrarLog() {
        return registrarLog;
    }

    public void setRegistrarLog(Logger registrarLog) {
        this.registrarLog = registrarLog;
    }
}
