package util;

import java.io.File;

public class ArchivosUtil {
    public static boolean crearCarpetaDePublicador(String abreviatura) {

        String feedDeEntradaArticulos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/article/",
                abreviatura,
                "articulos"
        );

        String feedDeEntradaFotos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/photos/",
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


    public static boolean crearCarpetaDeCliente(String abre) {

        String feedDeRecibirArticulos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/carpetasClientes/articulos/",
                abre,
                "articulos"
        );

        String feedDeRecibirFotos = String.format(
                "%s/%s/%s",
                "src/main/resources/content/carpetasClientes/fotos/",
                abre,
                "fotos"
        );

        File creadorCarpetaArticulosCliente = new File(feedDeRecibirArticulos);
        File creadorCarpetaFotosCliente = new File(feedDeRecibirFotos);

        // Se crean las dos carpetas.
        if(creadorCarpetaArticulosCliente.mkdirs() && creadorCarpetaFotosCliente.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }



}
