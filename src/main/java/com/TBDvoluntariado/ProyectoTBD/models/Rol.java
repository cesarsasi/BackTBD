package com.TBDvoluntariado.ProyectoTBD.models;

public class Rol {
    private int id;
    private String name;
    private String description;
    private Integer invisible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRole(Rol newRole){
        this.name = newRole.getName();
        this.description = newRole.getDescription();
    }

    public Integer getInvisible() {
        return invisible;
    }

    public void setInvisible(Integer invisible) {
        this.invisible = invisible;
    }
}
