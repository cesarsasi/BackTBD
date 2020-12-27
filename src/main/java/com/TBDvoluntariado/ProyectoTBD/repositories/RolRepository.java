package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Rol;

import java.util.List;

public interface RolRepository {
    public List<Rol> getAllRol();
    public Rol getRolById(Integer id);
    public Rol createRol(Rol rol);
    public void deleteRol(int id, Rol rol);
}
