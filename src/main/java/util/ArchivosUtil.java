package util;

import java.io.File;

public class ArchivosUtil {
    public static boolean crearCarpetaDePublicador(String abreviatura) {

        String feedDeEntradaArticulos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/",
                abreviatura,
                "articulos"
        );

        String feedDeEntradaFotos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/",
                abreviatura,
                "fotos"
        );

        File creadorCarpetaArticulos = new File(feedDeEntradaArticulos);
        File creadorCarpetaFotos = new File(feedDeEntradaFotos);

        // Se crean las dos carpetas.
        if(creadorCarpetaArticulos.mkdirs() && creadorCarpetaFotos.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }


}
