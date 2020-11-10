package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Estado_tareaRepositoryImp implements Estado_tareaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countEstado_tareas(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM estado_tarea").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Estado_tarea> getAllEstado_tareas() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from estado_tarea")
                    .executeAndFetch(Estado_tarea.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Estado_tarea createEstado_tarea(Estado_tarea estado_tarea) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO estado_tarea (descrip) values (:descrip)", true)
                    .addParameter("descrip", estado_tarea.getDescrip())
                    .executeUpdate().getKey();
            estado_tarea.setId(insertedId);
            return estado_tarea;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
