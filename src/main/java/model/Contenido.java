package model;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Contenido {

    protected long idContenido;
    protected String titulo;
    protected String fechaPublicacion;
    protected OffsetDateTime fechaPublicacionOdt;
    protected ZonedDateTime fechaPublicacionZdt;

    public Contenido(long idContenido, String titulo, String fechaPublicacion) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Contenido(long idContenido, String titulo, OffsetDateTime fechaPublicacionOdt) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.fechaPublicacionOdt = fechaPublicacionOdt;
    }

    public Contenido(long idContenido, String titulo, ZonedDateTime fechaPublicacionZdt) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.fechaPublicacionZdt = fechaPublicacionZdt;
    }

    public long getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(long idContenido) {
        this.idContenido = idContenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public OffsetDateTime getFechaPublicacionOdt() {
        return fechaPublicacionOdt;
    }

    public void setFechaPublicacionOdt(OffsetDateTime fechaPublicacionOdt) {
        this.fechaPublicacionOdt = fechaPublicacionOdt;
    }

    public ZonedDateTime getFechaPublicacionZdt() {
        return fechaPublicacionZdt;
    }

    public void setFechaPublicacionZdt(ZonedDateTime fechaPublicacionZdt) {
        this.fechaPublicacionZdt = fechaPublicacionZdt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contenido other = (Contenido) obj;
        return Objects.equals(fechaPublicacion, other.fechaPublicacion) && idContenido == other.idContenido
                && Objects.equals(titulo, other.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContenido, titulo, fechaPublicacion);
    }
}
