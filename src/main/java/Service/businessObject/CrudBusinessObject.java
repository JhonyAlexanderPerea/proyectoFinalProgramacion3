package Service.businessObject;

import model.Publicador;
import Service.modelbehavior.GestorPublicadoresModelBehavior;

public class CrudBusinessObject extends BusinessObject {

    public CrudBusinessObject() {
        super();
    }

    public long guardarPublicador(Publicador publicador) {
        GestorPublicadoresModelBehavior gestorPublicaciones = new GestorPublicadoresModelBehavior(this);
        return gestorPublicaciones.guardarPublicador(publicador);
    }

}
