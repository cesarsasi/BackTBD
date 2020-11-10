package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;

import java.util.List;

public interface HabilidadRepository {
    public int countHabilidades();
    public List<Habilidad> getAllHabilidades();
    public Habilidad createHabilidad(Habilidad habilidad);
}
