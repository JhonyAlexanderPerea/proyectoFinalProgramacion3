package model;

public class Articulo extends Contenido{

    private String cuerpoArticulo;

    public Articulo(long idContenido, String titulo, String fechaPublicacion) {
        super(idContenido, titulo, fechaPublicacion);
    }

    public Articulo(long idContenido, String titulo, String fechaPublicacion, String cuerpoArticulo) {
        super(idContenido, titulo, fechaPublicacion);
        this.cuerpoArticulo = cuerpoArticulo;
    }

    public String getCuerpoArticulo() {
        return cuerpoArticulo;
    }

    public void setCuerpoArticulo(String cuerpoArticulo) {
        this.cuerpoArticulo = cuerpoArticulo;
    }
}
