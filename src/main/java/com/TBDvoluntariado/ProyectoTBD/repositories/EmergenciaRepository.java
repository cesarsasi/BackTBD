package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Emergencia;
import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;

import java.util.List;

public interface EmergenciaRepository {
    public int countEmergencias();
    public List<Emergencia> getAllEmergencias();
    public Emergencia getEmergenciaById(Integer id);
    public Emergencia createEmergencia(Emergencia emergencia);
    public void updateEmergencia(int id, Emergencia emergencia);
    public void deleteEmergencia(int id, Emergencia emergencia);
}
