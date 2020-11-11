package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Tarea_habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Tarea_habilidadRepositoryImp implements Tarea_habilidadRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countTarea_habilidades(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM tarea_habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Tarea_habilidad> getAllTarea_habs() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from tarea_habilidad")
                    .executeAndFetch(Tarea_habilidad.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea_habilidad createTarea_hab(Tarea_habilidad tarea_habilidad) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO tarea_habilidad (id_emehab,id_tarea) values (:id_emehab,:id_tarea)", true)
                    .addParameter("id_emehab", tarea_habilidad.getId_emehab())
                    .addParameter("id_tarea", tarea_habilidad.getId_tarea())
                    .executeUpdate().getKey();
            tarea_habilidad.setId(insertedId);
            return tarea_habilidad;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
