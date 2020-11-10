package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;

import java.util.List;

public interface Eme_habilidadRepository {
    public int countEme_habs();
    public List<Eme_habilidad> getAllEme_habs();
    public Eme_habilidad createEme_hab(Eme_habilidad eme_habilidad);
}
