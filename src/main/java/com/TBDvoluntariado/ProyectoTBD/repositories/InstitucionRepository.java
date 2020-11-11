package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Institucion;

import java.util.List;

public interface InstitucionRepository {
    public int countInstituciones();
    public List<Institucion> getAllInstituciones();
    public Institucion createInstitucion(Institucion institucion);
}
