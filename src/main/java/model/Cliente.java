package model;

import java.util.Objects;

public class Cliente {

    public String nombre;
    public long idCliente;
    public String rutaArticulos;
    public String rutaFotos;

     public Cliente(long idCliente, String nombre, String rutaArticulos, String rutaFotos) {
        super();
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.rutaArticulos = rutaArticulos;
        this.rutaFotos = rutaFotos;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaArticulos() {
        return rutaArticulos;
    }

    public void setRutaArticulos(String rutaArticulos) {
        this.rutaArticulos = rutaArticulos;
    }

    public String getRutaFotos() {
        return rutaFotos;
    }

    public void setRutaFotos(String rutaFotos) {
        this.rutaFotos = rutaFotos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(nombre, other.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
