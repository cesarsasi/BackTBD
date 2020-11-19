package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;
import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;

import java.util.List;

public interface HabilidadRepository {
    public int countHabilidades();
    public List<Habilidad> getAllHabilidades();
    public Habilidad getHabilidadById(Integer id);
    public Habilidad createHabilidad(Habilidad habilidad);
    public void updateHabilidad(int id, Habilidad habilidad);
    public void deleteHabilidad(int id, Habilidad habilidad);
}
