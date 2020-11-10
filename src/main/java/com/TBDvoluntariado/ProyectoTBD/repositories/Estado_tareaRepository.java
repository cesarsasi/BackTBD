package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;

import java.util.List;

public interface Estado_tareaRepository {
    public int countEstado_tareas();
    public List<Estado_tarea> getAllEstado_tareas();
    public Estado_tarea createEstado_tarea(Estado_tarea estado_Tarea);
}
