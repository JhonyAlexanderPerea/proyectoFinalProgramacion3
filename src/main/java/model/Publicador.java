package model;

import java.util.ArrayList;
import java.util.Objects;

public class Publicador {

    private String nombre;
    private long idPublicador;
    private String abreviatura;
    private ArrayList<Articulo> listaArticulos;
    private ArrayList<Foto> listaFotos;
    private String rutaCarpetaArticulos;
    private String rutaCarpetaFotos;
    private String urlDelSitioWeb;

    public Publicador(String nombre, long idPublicador,
                      String rutaCarpetaArticulos, String rutaCarpetaFotos) {
        this.nombre = nombre;
        this.idPublicador = idPublicador;
        this.rutaCarpetaArticulos = rutaCarpetaArticulos;
        this.rutaCarpetaFotos = rutaCarpetaFotos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(long idPublicador) {
        this.idPublicador = idPublicador;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(ArrayList<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public ArrayList<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(ArrayList<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public String getRutaCarpetaArticulos() {
        return rutaCarpetaArticulos;
    }

    public void setRutaCarpetaArticulos(String rutaCarpetaArticulos) {
        this.rutaCarpetaArticulos = rutaCarpetaArticulos;
    }

    public String getRutaCarpetaFotos() {
        return rutaCarpetaFotos;
    }

    public void setRutaCarpetaFotos(String rutaCarpetaFotos) {
        this.rutaCarpetaFotos = rutaCarpetaFotos;
    }

    public String getUrlDelSitioWeb() {
        return urlDelSitioWeb;
    }

    public void setUrlDelSitioWeb(String urlDelSitioWeb) {
        this.urlDelSitioWeb = urlDelSitioWeb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Publicador other = (Publicador) obj;
        return Objects.equals(nombre, other.nombre);
    }
}
