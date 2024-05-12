package Service.modelbehavior;
import java.io.IOException;

import model.Publicador;
import exception.Exceptions;
import exception.Exceptions.CsvDePublicadorMalFormado;
import exception.Exceptions.PublicadorDuplicadoException;
import repository.csvdao.PublicadorCsvDao;
import Service.businessObject.BusinessObject;
import util.ArchivosUtil;
import util.StringUtil;


public class GestorPublicadoresModelBehavior extends ModelBehavior {

    public GestorPublicadoresModelBehavior() {
        super();
    }

    public GestorPublicadoresModelBehavior(BusinessObject bo) {
        super(bo);
    }

    public long guardarPublicador(Publicador publicador) {
        long idPublicador = 0;

        try (PublicadorCsvDao publiCDao = new PublicadorCsvDao()) {
            String abr = StringUtil.obtenerAbreviatura(publicador.getNombre());
            publicador.setAbreviatura(abr);

            if (ArchivosUtil.crearCarpetaDePublicador(abr) == false) {
                logAdvertencia("No se pudo crear correctamente las carpetas asociadas al publicador");
            }

            idPublicador = publiCDao.guardarPublicador(publicador);
        } catch (PublicadorDuplicadoException excep) {
            logError(excep.getMessage(), true);
        } catch (CsvDePublicadorMalFormado excep) {
            logError(excep.getMessage(), true);
        } catch (IOException excep) {
            logError(excep.getMessage());
            logError("Se presentó un problema en el sistema de archivos al intentar crear el Publicador", true);
        }

        return idPublicador;
    }
}