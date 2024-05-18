package model;

import java.util.ArrayList;

public class Usuario extends Persona{

    private long idCliente;
    private long idPublicador;
    private long idUsuario;
    private String loginEmail;


    private String usuario;
    private String contraseña;
    private Rol rol;
    private String tipoAliado;
    public final String TIPO_ALIADO_CLIENTE = "CLIENTE";
    public final String TIPO_ALIADO_PUBLICADOR = "PUBLICADOR";

    public Usuario(long id, String documento, String nombreCompleto, String tipoDocumento,ArrayList<String> listaEmails,
                   String[] listaTelefonos, long idCliente, long idPublicador, long idUsuario, String loginEmail, Rol rol) {
        super(id, documento, nombreCompleto, tipoDocumento, listaEmails, listaTelefonos);
        this.idCliente = idCliente;
        this.idPublicador = idPublicador;
        this.idUsuario = idUsuario;

        this.loginEmail = loginEmail;
        this.rol = rol;
    }

    public Usuario(long id, String documento, String nombreCompleto, String loginEmail, Rol rol) {
        super(id, documento, nombreCompleto);
        this.loginEmail = loginEmail;
        this.rol = rol;
    }

    public Usuario(String documento, String nombreCompleto, String loginEmail, Rol rol) {
        super(documento, nombreCompleto);
        this.loginEmail = loginEmail;
        this.rol = rol;
    }

    public Usuario(String usuario, String contraseña) {
        super();
        this.usuario = usuario;
        this.contraseña = contraseña;
    }


    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(long idPublicador) {
        this.idPublicador = idPublicador;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTipoAliado() {
        return tipoAliado;
    }

    public void setTipoAliado(String tipoAliado) {
        this.tipoAliado = tipoAliado;
    }

    public String getContraseña() {return contraseña;}

    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
}
