package com.jspm.appinventario3235588.modelo;

public class ClsNovedades {
    private String IdNovedad;
    private String FechaNovedad;
    private String TipoNovedad;
    private String DescripcionNovedad;
    private String AmbienteNovedad;
    private String ElementoNovedad;
    private String InstructorNovedad;

    public ClsNovedades() {
    }

    public ClsNovedades(String IdNovedad, String FechaNovedad, String TipoNovedad, String DescripcionNovedad, String AmbienteNovedad, String ElementoNovedad, String InstructorNovedad) {
        this.IdNovedad = IdNovedad;
        this.FechaNovedad = FechaNovedad;
        this.TipoNovedad = TipoNovedad;
        this.DescripcionNovedad = DescripcionNovedad;
        this.AmbienteNovedad = AmbienteNovedad;
        this.ElementoNovedad = ElementoNovedad;
        this.InstructorNovedad = InstructorNovedad;
    }

    // Getter and setter

    public String getIdNovedad() {
        return IdNovedad;
    }

    public void setIdNovedad(String idNovedad) {
        IdNovedad = idNovedad;
    }

    public String getFechaNovedad() {
        return FechaNovedad;
    }

    public void setFechaNovedad(String fechaNovedad) {
        FechaNovedad = fechaNovedad;
    }

    public String getTipoNovedad() {
        return TipoNovedad;
    }

    public void setTipoNovedad(String tipoNovedad) {
        TipoNovedad = tipoNovedad;
    }

    public String getDescripcionNovedad() {
        return DescripcionNovedad;
    }

    public void setDescripcionNovedad(String descripcionNovedad) {
        DescripcionNovedad = descripcionNovedad;
    }

    public String getAmbienteNovedad() {
        return AmbienteNovedad;
    }

    public void setAmbienteNovedad(String ambienteNovedad) {
        AmbienteNovedad = ambienteNovedad;
    }

    public String getElementoNovedad() {
        return ElementoNovedad;
    }

    public void setElementoNovedad(String elementoNovedad) {
        ElementoNovedad = elementoNovedad;
    }

    public String getInstructorNovedad() {
        return InstructorNovedad;
    }

    public void setInstructorNovedad(String instructorNovedad) {
        InstructorNovedad = instructorNovedad;
    }
}
