package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Emergencia;

import java.util.List;

public interface EmergenciaRepository {
    public int countEmergencias();
    public List<Emergencia> getAllEmergencias();
    public Emergencia createEmergencia(Emergencia emergencia);
}
