package com.TBDvoluntariado.ProyectoTBD.models;

public class Vol_habilidad {
    private Long id;
    private Integer id_habilidad;
    private Integer id_voluntario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(Integer id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    public Integer getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(Integer id_voluntario) {
        this.id_voluntario = id_voluntario;
    }
}
