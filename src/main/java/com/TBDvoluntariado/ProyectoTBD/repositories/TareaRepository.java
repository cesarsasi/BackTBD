package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Tarea;

import java.util.List;

public interface TareaRepository {
    public List<Tarea> getAllTareas();
    public Tarea getTareasByIds(Integer id_tarea, Integer id_emer, Integer id_est);
    public Tarea createTarea(Tarea tarea);
    public int biggestId();
    public Tarea getTareaById(Integer id);
    public void updateTarea(int id, Tarea tarea);
    public void deleteTarea(int id, Tarea tarea);
}
