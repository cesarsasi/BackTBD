package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Emergencia;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;

import java.util.List;

public interface Eme_habilidadRepository {
    public int countEme_habs();
    public List<Eme_habilidad> getAllEme_habs();
    public Eme_habilidad getEme_habilidadById(Integer id);
    public Eme_habilidad createEme_hab(Eme_habilidad eme_habilidad);
    public void updateEme_habilidad(int id, Eme_habilidad eme_habilidad);
    public void deleteEme_habilidad(int id, Eme_habilidad eme_habilidad);
}
