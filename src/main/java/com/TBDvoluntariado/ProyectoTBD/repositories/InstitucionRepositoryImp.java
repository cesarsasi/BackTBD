package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitucionRepositoryImp implements InstitucionRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countInstituciones(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM institucion").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Institucion> getAllInstituciones(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from institucion")
                    .executeAndFetch(Institucion.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institucion createInstitucion(Institucion institucion){
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO institucion (nombre, descrip) values (:nombreInstitucion, :descrip)", true)
                    .addParameter("nombreInstitucion", institucion.getNombre())
                    .addParameter("descrip", institucion.getDescrip())
                    .executeUpdate().getKey();
            institucion.setId(insertedId);
            return institucion;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
