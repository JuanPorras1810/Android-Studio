package com.jspm.appinventario3235588.modelo;

public class ClsAmbiente {
    private String IdAmbiente;
    private String NumeroIdAmbiente;
    private String PisoAmbiente;
    private String DescripcionAmbiente;

    public ClsAmbiente() {
    }

    public ClsAmbiente(String IdAmbiente, String NumeroIdAmbiente, String PisoAmbiente, String DescripcionAmbiente) {
        this.IdAmbiente = IdAmbiente;
        this.NumeroIdAmbiente = NumeroIdAmbiente;
        this.PisoAmbiente = PisoAmbiente;
        this.DescripcionAmbiente = DescripcionAmbiente;
    }

    // Getters and setter

    public String getIdAmbiente() {
        return IdAmbiente;
    }

    public void setIdAmbiente(String idAmbiente) {
        IdAmbiente = idAmbiente;
    }

    public String getNumeroIdAmbiente() {
        return NumeroIdAmbiente;
    }

    public void setNumeroIdAmbiente(String numeroIdAmbiente) {
        NumeroIdAmbiente = numeroIdAmbiente;
    }

    public String getPisoAmbiente() {
        return PisoAmbiente;
    }

    public void setPisoAmbiente(String pisoAmbiente) {
        PisoAmbiente = pisoAmbiente;
    }

    public String getDescripcionAmbiente() {
        return DescripcionAmbiente;
    }

    public void setDescripcionAmbiente(String descripcionAmbiente) {
        DescripcionAmbiente = descripcionAmbiente;
    }
}
