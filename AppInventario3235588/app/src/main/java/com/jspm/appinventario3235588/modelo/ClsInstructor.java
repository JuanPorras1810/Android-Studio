package com.jspm.appinventario3235588.modelo;

public class ClsInstructor {
    private String IdInstructor;
    private String DocumentoInstructor;
    private String NombreInstructor;
    private String ApellidosInstructor;
    private String EmailInstructor;
    private String CelularInstructor;
    private String RedInstructor;

    public ClsInstructor() {
    }

    public ClsInstructor(String IdInstructor, String DocumentoInstructor, String NombreInstructor, String ApellidosInstructor, String EmailInstructor, String CelularInstructor, String RedInstructor) {
        this.IdInstructor = IdInstructor;
        this.DocumentoInstructor = DocumentoInstructor;
        this.NombreInstructor = NombreInstructor;
        this.ApellidosInstructor = ApellidosInstructor;
        this.EmailInstructor = EmailInstructor;
        this.CelularInstructor = CelularInstructor;
        this.RedInstructor = RedInstructor;
    }

    // Getter and setter

    public String getIdInstructor() {
        return IdInstructor;
    }

    public void setIdInstructor(String idInstructor) {
        IdInstructor = idInstructor;
    }

    public String getDocumentoInstructor() {
        return DocumentoInstructor;
    }

    public void setDocumentoInstructor(String documentoInstructor) {
        DocumentoInstructor = documentoInstructor;
    }

    public String getNombreInstructor() {
        return NombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        NombreInstructor = nombreInstructor;
    }

    public String getApellidosInstructor() {
        return ApellidosInstructor;
    }

    public void setApellidosInstructor(String apellidosInstructor) {
        ApellidosInstructor = apellidosInstructor;
    }

    public String getEmailInstructor() {
        return EmailInstructor;
    }

    public void setEmailInstructor(String emailInstructor) {
        EmailInstructor = emailInstructor;
    }

    public String getCelularInstructor() {
        return CelularInstructor;
    }

    public void setCelularInstructor(String celularInstructor) {
        CelularInstructor = celularInstructor;
    }

    public String getRedInstructor() {
        return RedInstructor;
    }

    public void setRedInstructor(String redInstructor) {
        RedInstructor = redInstructor;
    }
}
