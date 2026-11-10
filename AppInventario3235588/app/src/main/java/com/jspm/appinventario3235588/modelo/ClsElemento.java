package com.jspm.appinventario3235588.modelo;

public class ClsElemento {
    private String IdElemento;
    private String CodigoElemento;
    private String IdAmbienteElemento;
    private String TipoElemento;
    private String DescripcionElemento;
    private String AtributosElemento;
    private String ValorElemento;

    public ClsElemento() {
    }

    public ClsElemento(String IdElemento, String CodigoElemento, String IdAmbienteElemento, String TipoElemento, String DescripcionElemento, String AtributosElemento, String ValorElemento) {
        this.IdElemento = IdElemento;
        this.CodigoElemento = CodigoElemento;
        this.IdAmbienteElemento = IdAmbienteElemento;
        this.TipoElemento = TipoElemento;
        this.DescripcionElemento = DescripcionElemento;
        this.AtributosElemento = AtributosElemento;
        this.ValorElemento = ValorElemento;
    }

    // Getter and Setter
    public String getIdElemento() {
        return IdElemento;
    }

    public void setIdElemento(String idElemento) {
        IdElemento = idElemento;
    }

    public String getCodigoElemento() {
        return CodigoElemento;
    }

    public void setCodigoElemento(String codigoElemento) {
        CodigoElemento = codigoElemento;
    }

    public String getIdAmbienteElemento() {
        return IdAmbienteElemento;
    }

    public void setIdAmbienteElemento(String idAmbienteElemento) {
        IdAmbienteElemento = idAmbienteElemento;
    }

    public String getTipoElemento() {
        return TipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
        TipoElemento = tipoElemento;
    }

    public String getDescripcionElemento() {
        return DescripcionElemento;
    }

    public void setDescripcionElemento(String descripcionElemento) {
        DescripcionElemento = descripcionElemento;
    }

    public String getAtributosElemento() {
        return AtributosElemento;
    }

    public void setAtributosElemento(String atributosElemento) {
        AtributosElemento = atributosElemento;
    }

    public String getValorElemento() {
        return ValorElemento;
    }

    public void setValorElemento(String valorElemento) {
        ValorElemento = valorElemento;
    }
}
