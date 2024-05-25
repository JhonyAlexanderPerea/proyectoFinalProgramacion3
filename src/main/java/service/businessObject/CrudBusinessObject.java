package service.businessObject;

import model.Cliente;
import model.Publicador;
import service.modelBehavior.GestorClientesModelBehavior;
import service.modelBehavior.GestorPublicadoresModelBehavior;

public class CrudBusinessObject extends BusinessObject {

    public CrudBusinessObject() {
        super();
    }

    public long guardarPublicador(Publicador publicador) {
        GestorPublicadoresModelBehavior gestorPublicaciones = new GestorPublicadoresModelBehavior(this);
        return gestorPublicaciones.guardarPublicador(publicador);
    }

    public long guardarCliente(Cliente cliente) {
        GestorClientesModelBehavior gestorClientes = new GestorClientesModelBehavior(this);
        return gestorClientes.guardarCliente(cliente);
    }

}
