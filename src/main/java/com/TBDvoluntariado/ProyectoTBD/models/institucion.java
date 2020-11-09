package com.TBDvoluntariado.ProyectoTBD.models;

public class institucion {
    private Integer id;
    private String nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
