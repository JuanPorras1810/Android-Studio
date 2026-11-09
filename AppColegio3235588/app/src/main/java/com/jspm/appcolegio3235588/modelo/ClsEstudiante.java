package com.jspm.appcolegio3235588.modelo;

public class ClsEstudiante {
    private String IdEstudiante;
    private String DocumentoEstudiante;
    private String NombreEstudiante;
    private String ApellidoEstudiante;
    private String DireccionEstudiante;
    private String TelefonoEstudiante;

    public ClsEstudiante() {
    }

    public ClsEstudiante(String IdEstudiante, String DocumentoEstudiante, String NombreEstudiante, String ApellidoEstudiante, String DireccionEstudiante, String TelefonoEstudiante) {
        this.IdEstudiante = IdEstudiante;
        this.DocumentoEstudiante = DocumentoEstudiante;
        this.NombreEstudiante = NombreEstudiante;
        this.ApellidoEstudiante = ApellidoEstudiante;
        this.DireccionEstudiante = DireccionEstudiante;
        this.TelefonoEstudiante = TelefonoEstudiante;
    }

    // Getter and Setters

    public String getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        IdEstudiante = idEstudiante;
    }

    public String getDocumentoEstudiante() {
        return DocumentoEstudiante;
    }

    public void setDocumentoEstudiante(String documentoEstudiante) {
        DocumentoEstudiante = documentoEstudiante;
    }

    public String getNombreEstudiante() {
        return NombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        NombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return ApellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        ApellidoEstudiante = apellidoEstudiante;
    }

    public String getDireccionEstudiante() {
        return DireccionEstudiante;
    }

    public void setDireccionEstudiante(String direccionEstudiante) {
        DireccionEstudiante = direccionEstudiante;
    }

    public String getTelefonoEstudiante() {
        return TelefonoEstudiante;
    }

    public void setTelefonoEstudiante(String telefonoEstudiante) {
        TelefonoEstudiante = telefonoEstudiante;
    }
}