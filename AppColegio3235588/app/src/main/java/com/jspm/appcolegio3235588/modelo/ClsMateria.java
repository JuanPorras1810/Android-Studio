package com.jspm.appcolegio3235588.modelo;

public class ClsMateria {
    private String IdMateria;
    private String DocumentoMateria;
    private String NombreMateria;
    private String GradoMateria;

    public ClsMateria() {
    }

    public ClsMateria(String IdMateria, String DocumentoMateria, String NombreMateria, String GradoMateria) {
        this.IdMateria = IdMateria;
        this.DocumentoMateria = DocumentoMateria;
        this.NombreMateria = NombreMateria;
        this.GradoMateria = GradoMateria;

    }

    // Getter and Setters
    public String getIdMateria() {
        return IdMateria;
    }

    public void setIdMateria(String idMateria) {
        IdMateria = idMateria;
    }

    public String getDocumentoMateria() {
        return DocumentoMateria;
    }

    public void setDocumentoMateria(String documentoMateria) {
        DocumentoMateria = documentoMateria;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        NombreMateria = nombreMateria;
    }

    public String getGradoMateria() {
        return GradoMateria;
    }

    public void setGradoMateria(String gradoMateria) {
        GradoMateria = gradoMateria;
    }
}
