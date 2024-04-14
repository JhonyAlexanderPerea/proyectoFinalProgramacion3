package model;

public class Foto extends Contenido{

    byte[] cuerpo;

    public Foto(long idContenido, String titulo, String fechaPublicacion) {
        super(idContenido, titulo, fechaPublicacion);
    }

    public Foto(long idContenido, String titulo, String fechaPublicacion, byte[] cuerpo) {
        super(idContenido, titulo, fechaPublicacion);
        this.cuerpo = cuerpo;
    }

    public byte[] getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(byte[] cuerpo) {
        this.cuerpo = cuerpo;
    }
}
