package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Institucion;

import java.util.List;

public interface InstitucionRepository {
    public int countInstituciones();
    public List<Institucion> getAllInstituciones();
    public Institucion getInstitucionById(Integer id);
    public Institucion createInstitucion(Institucion institucion);
    public void updateInstitucion(int id, Institucion institucion);
    public void deleteInstitucion(int id, Institucion institucion);
}
