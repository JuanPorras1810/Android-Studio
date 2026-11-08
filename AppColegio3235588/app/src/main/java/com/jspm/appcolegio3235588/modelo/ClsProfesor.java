package com.jspm.appcolegio3235588.modelo;

public class ClsProfesor {
    private String IdProfesor;
    private String DocumentoProfesor;
    private String NombreProfesor;
    private String ApellidoProfesor;
    private String DireccionProfesor;
    private String TelefonoProfesor;
    private String EmailProfesor;
    private String TituloProfesor;

    public ClsProfesor() {
    }

    public ClsProfesor(String IdProfesor, String DocumentoProfesor, String NombreProfesor, String ApellidoProfesor, String DireccionProfesor, String TelefonoProfesor, String EmailProfesor, String TituloProfesor) {
        this.IdProfesor = IdProfesor;
        this.DocumentoProfesor = DocumentoProfesor;
        this.NombreProfesor = NombreProfesor;
        this.ApellidoProfesor = ApellidoProfesor;
        this.DireccionProfesor = DireccionProfesor;
        this.TelefonoProfesor = TelefonoProfesor;
        this.EmailProfesor = EmailProfesor;
        this.TituloProfesor = TituloProfesor;
    }

    // Geter and Setters


    public String getDocumentoProfesor() {
        return DocumentoProfesor;
    }

    public void setDocumentoProfesor(String documentoProfesor) {
        DocumentoProfesor = documentoProfesor;
    }

    public String getNombreProfesor() {
        return NombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        NombreProfesor = nombreProfesor;
    }

    public String getApellidoProfesor() {
        return ApellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        ApellidoProfesor = apellidoProfesor;
    }

    public String getDireccionProfesor() {
        return DireccionProfesor;
    }

    public void setDireccionProfesor(String direccionProfesor) {
        DireccionProfesor = direccionProfesor;
    }

    public String getTelefonoProfesor() {
        return TelefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        TelefonoProfesor = telefonoProfesor;
    }

    public String getEmailProfesor() {
        return EmailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        EmailProfesor = emailProfesor;
    }

    public String getTituloProfesor() {
        return TituloProfesor;
    }

    public void setTituloProfesor(String tituloProfesor) {
        TituloProfesor = tituloProfesor;
    }

    public String getIdProfesor() {
        return IdProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        IdProfesor = idProfesor;
    }
}
