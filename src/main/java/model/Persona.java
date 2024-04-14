package model;

import java.util.ArrayList;
import java.util.Objects;

public class Persona {

    protected long id;
    protected String documento;
    protected String nombreCompleto;
    protected String tipoDocumento;
    protected ArrayList<String> listaEmails;
    protected String[] listaTelefonos;

    public Persona(long id, String documento, String nombreCompleto,
                   String tipoDocumento, ArrayList<String> listaEmails, String[] listaTelefonos) {
        this.id = id;
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.tipoDocumento = tipoDocumento;
        this.listaEmails = listaEmails;
        this.listaTelefonos = listaTelefonos;
    }

    public Persona(long id, String documento, String nombreCompleto) {
        this.id = id;
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
    }

    public Persona(String documento, String nombreCompleto) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public ArrayList<String> getListaEmails() {
        return listaEmails;
    }

    public void setListaEmails(ArrayList<String> listaEmails) {
        this.listaEmails = listaEmails;
    }

    public String[] getListaTelefonos() {
        return listaTelefonos;
    }

    public void setListaTelefonos(String[] listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        return Objects.equals(documento, other.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(documento);
    }
}
