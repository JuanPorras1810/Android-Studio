package com.jspm.appcolegio3235588.modelo;

public class ClsAcudiente {
    private String IdAcudiente;
    private String DocumentoAcudiente;
    private String NombreAcudiente;
    private String ApellidoAcudiente;
    private String DireccionAcudiente;
    private String TelefonoAcudiente;
    private String EmailAcudiente;

    public ClsAcudiente() {
    }

    public ClsAcudiente(String IdAcudiente, String DocumentoAcudiente, String NombreAcudiente, String ApellidoAcudiente, String DireccionAcudiente, String TelefonoAcudiente, String EmailAcudiente) {
        this.IdAcudiente = IdAcudiente;
        this.DocumentoAcudiente = DocumentoAcudiente;
        this.NombreAcudiente = NombreAcudiente;
        this.ApellidoAcudiente = ApellidoAcudiente;
        this.DireccionAcudiente = DireccionAcudiente;
        this.TelefonoAcudiente = TelefonoAcudiente;
        this.EmailAcudiente = EmailAcudiente;
    }

    // Getter y Setter


    public String getIdAcudiente() {
        return IdAcudiente;
    }

    public void setIdAcudiente(String idAcudiente) {
        IdAcudiente = idAcudiente;
    }

    public String getDocumentoAcudiente() {
        return DocumentoAcudiente;
    }

    public void setDocumentoAcudiente(String documentoAcudiente) {
        DocumentoAcudiente = documentoAcudiente;
    }

    public String getNombreAcudiente() {
        return NombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        NombreAcudiente = nombreAcudiente;
    }

    public String getApellidoAcudiente() {
        return ApellidoAcudiente;
    }

    public void setApellidoAcudiente(String apellidoAcudiente) {
        ApellidoAcudiente = apellidoAcudiente;
    }

    public String getDireccionAcudiente() {
        return DireccionAcudiente;
    }

    public void setDireccionAcudiente(String direccionAcudiente) {
        DireccionAcudiente = direccionAcudiente;
    }

    public String getTelefonoAcudiente() {
        return TelefonoAcudiente;
    }

    public void setTelefonoAcudiente(String telefonoAcudiente) {
        TelefonoAcudiente = telefonoAcudiente;
    }

    public String getEmailAcudiente() {
        return EmailAcudiente;
    }

    public void setEmailAcudiente(String emailAcudiente) {
        EmailAcudiente = emailAcudiente;
    }
}


