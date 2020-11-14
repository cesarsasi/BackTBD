package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;

import java.util.List;

public interface VoluntarioRepository {

    public List<Voluntario> getAllVoluntarios();
    public Voluntario getVoluntarioById(Integer id);
    public Voluntario createVoluntario(Voluntario voluntario);
    public int biggestId();
}
