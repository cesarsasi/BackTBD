package com.TBDvoluntariado.ProyectoTBD.models;

public class Habilidad {
    private Integer id;
    private String descrip;

    public String getDescrip() {
        return descrip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    
}
