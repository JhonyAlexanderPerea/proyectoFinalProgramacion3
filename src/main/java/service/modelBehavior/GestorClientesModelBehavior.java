package service.modelBehavior;

import exception.Exceptions;
import model.Cliente;
import repository.csvdao.ClienteCsvDao;
import service.businessObject.BusinessObject;
import util.ArchivosUtil;
import util.StringUtil;
import java.io.IOException;

public class GestorClientesModelBehavior extends ModelBehavior{

    public GestorClientesModelBehavior() {
        super();
    }

    public GestorClientesModelBehavior(BusinessObject bo) {
        super(bo);
    }

    public long guardarCliente(Cliente cliente) {
        long idCliente = 0;

        try (ClienteCsvDao clienteCsvDao = new ClienteCsvDao()) {
            String abr = StringUtil.obtenerAbreviatura(cliente.getNombre());

            if (!ArchivosUtil.crearCarpetaDeCliente(abr)) {
                logAdvertencia("No se pudo crear correctamente las carpetas asociadas al cliente");
            }

            idCliente = clienteCsvDao.guardarCliente(cliente);
        } catch (Exceptions.CsvDeClienteMalFormado excep) {
            logError(excep.getMessage(), true);
        } catch (IOException | Exceptions.ClienteDuplicadoException excep) {
            logError(excep.getMessage());
            logError("Se presentó un problema en el sistema de archivos al intentar crear el Cliente", true);
        }

        return idCliente;
    }
}
