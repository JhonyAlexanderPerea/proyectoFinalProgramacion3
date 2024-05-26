package util;

import java.io.File;

public class PruebaUtil {

    /**
     * Aqui ya funciona, esto fue lo que circa me dijo que habia que hacer, recuerden que los archivos se borran cuando se convierten a csv entonces los guarde en la carpeta respaldo para que los vuelvan a copiar, no se si deban hacer el proceso de pasarlos a cvs de forma recursiva entonces no olviden revisarlo.
     * @param args
     * 
     */
    public static void main(String[] args) {
        String rutaDirectorio = "src/main/resources/content/articles";
        File directory = new File(rutaDirectorio);

        for(File file : directory.listFiles()) ArchivosUtil.transformarXML(file);
    }
}
