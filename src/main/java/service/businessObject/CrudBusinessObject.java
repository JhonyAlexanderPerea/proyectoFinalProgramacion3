package service.businessObject;

import model.Publicador;
import service.modelBehavior.GestorPublicadoresModelBehavior;

public class CrudBusinessObject extends BusinessObject {

    public CrudBusinessObject() {
        super();
    }

    public long guardarPublicador(Publicador publicador) {
        GestorPublicadoresModelBehavior gestorPublicaciones = new GestorPublicadoresModelBehavior(this);
        return gestorPublicaciones.guardarPublicador(publicador);
    }

}
