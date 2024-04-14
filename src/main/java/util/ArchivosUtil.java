package util;

import java.io.File;

public class ArchivosUtil {
    public static boolean crearCarpetaDePublicador(String abreviatura) {

        // Ruta de la carpeta para recibir los artículos del publicador.
        String feedDeEntradaArticulos = String.format(
                "%s/%s/%s",
                //"C:/Users/lenovo/Documents/grupo_dia/feed_de_entrada/",
                abreviatura,
                "articulos"
        );

        // Ruta de la carpeta para recibir las fotos del publicador.
        String feedDeEntradaFotos = String.format(
                "%s/%s/%s",
                //"C:/Users/lenovo/Documents/grupo_dia/feed_de_entrada/",
                abreviatura,
                "fotos"
        );

        // Gestores para crear las carpetas.
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
