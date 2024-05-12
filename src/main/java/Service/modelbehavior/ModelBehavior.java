package Service.modelbehavior;

import Service.businessObject.BusinessObject;
public class ModelBehavior {

    BusinessObject bo;

    public ModelBehavior() {
        super();
    }

    public ModelBehavior(BusinessObject bo) {
        if(null != this.bo)
            this.bo = bo;
    }

    public void logAdvertencia(String mensaje) {
        if(null != this.bo)
            this.bo.logAdvertencia(mensaje);
    }

    public void logAdvertencia(String mensaje, boolean adjuntar) {
        if(null != this.bo)
            this.bo.logAdvertencia(mensaje, adjuntar);
    }

    // Delega el registro de logs a la clase mediadora de mensajes BusinessObject.
    public void logError(String mensaje) {
        if(null != this.bo)
            this.bo.logError(mensaje);
    }

    // Delega el registro de logs a la clase mediadora de mensajes BusinessObject.
    public void logError(String mensaje, boolean adjuntar) {
        if(null != this.bo)
            this.bo.logError(mensaje, adjuntar);
    }

    // Delega el registro de logs a la clase mediadora de mensajes BusinessObject.
    public void logInfo(String mensaje) {
        if(null != this.bo)
            this.bo.logInfo(mensaje);
    }

    // Delega el registro de logs a la clase mediadora de mensajes BusinessObject.
    public void logInfo(String mensaje, boolean adjuntar) {
        if(null != this.bo)
            this.bo.logInfo(mensaje, adjuntar);
    }
}

