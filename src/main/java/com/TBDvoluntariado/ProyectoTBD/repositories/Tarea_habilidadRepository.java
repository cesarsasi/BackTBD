package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Tarea_habilidad;

import java.util.List;

public interface Tarea_habilidadRepository {
    public int countTarea_habilidades();
    public List<Tarea_habilidad> getAllTarea_habs();
    public Tarea_habilidad createTarea_hab(Tarea_habilidad tarea_habilidad);
    public void updateTarea_habilidad(int id, Tarea_habilidad tarea_habilidad);
    public void deleteTarea_habilidad(int id, Tarea_habilidad tarea_habilidad);
    public Tarea_habilidad getTarea_habById(Integer id);
}
