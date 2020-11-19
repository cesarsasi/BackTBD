package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;

import java.util.List;

public interface Estado_tareaRepository {
    public int countEstado_tareas();
    public List<Estado_tarea> getAllEstado_tareas();
    public Estado_tarea getEstado_tareaById(Integer id);
    public Estado_tarea createEstado_tarea(Estado_tarea estado_Tarea);
    public void updateEstado_tarea(int id, Estado_tarea estado_tarea);
    public void deleteEstado_tarea(int id, Estado_tarea estado_tarea);
}
