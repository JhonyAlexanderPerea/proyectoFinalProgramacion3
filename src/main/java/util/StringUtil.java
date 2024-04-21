package util;

public class StringUtil {

    public static String obtenerAbreviatura(String texto) {
        return obtenerAbreviatura(texto, 4);
    }

    public static String obtenerAbreviatura(String texto, int cantidadCaracteres) {
        String[] palabras = texto.split(" ");
        String abreviatura = "";

        for(String palabra: palabras) {
            abreviatura += palabra.charAt(0);

            if(abreviatura.length() == cantidadCaracteres) {
                break;
            }
        }
        return abreviatura;
    }
}
