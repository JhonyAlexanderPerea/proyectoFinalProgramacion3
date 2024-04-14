package model;

import java.util.Objects;
import java.util.TreeMap;

public class Rol {

    private String nombre;
    private long idRol;
    private TreeMap<String, Boolean> permisosAcceso;


    public Rol(long idRol, String nombre, TreeMap<String, Boolean> permisosAcceso) {
        super();
        this.idRol = idRol;
        this.nombre = nombre;
        this.permisosAcceso = permisosAcceso;
    }

    public Rol(long idRol, String nombre) {
        super();
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public TreeMap<String, Boolean> getPermisosAcceso() {
        return permisosAcceso;
    }

    public void setPermisosAcceso(TreeMap<String, Boolean> permisosAcceso) {
        this.permisosAcceso = permisosAcceso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rol other = (Rol) obj;
        return idRol == other.idRol;
    }
}
