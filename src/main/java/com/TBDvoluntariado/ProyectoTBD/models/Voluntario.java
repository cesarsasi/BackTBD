package com.TBDvoluntariado.ProyectoTBD.models;

import java.sql.Date;

public class Voluntario {
    private Integer id;
    private String nombre;
    private Date fnacimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }
    
}
