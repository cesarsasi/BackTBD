package com.TBDvoluntariado.ProyectoTBD.repositories;


import com.TBDvoluntariado.ProyectoTBD.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Date;
import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Tarea> getAllTareas(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea").executeAndFetch(Tarea.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea getTareasByIds(Integer id_tarea, Integer id_emer, Integer id_est){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea WHERE id_emergencia = :id_emer AND id_estado = :id_est")
                    .addParameter("id_emer", id_emer)
                    .addParameter("id_est", id_est)
                    .executeAndFetchFirst(Tarea.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea createTarea(Tarea tarea){
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO tarea (id_emer, id_est, nombre, finicio, ffin, descripcion, cant_vol_inscritos, cant_vol_requeridos) values(:id_emer, :id_est, :nombre, :finicio, :ffin, :descripcion, :cant_vol_inscritos, :cant_vol_requeridos)")
                    .addParameter("id_emer", tarea.getId_emergencia())
                    .addParameter("id_est", tarea.getId_estado())
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("finicio", tarea.getFinicio())
                    .addParameter("ffinal", tarea.getFfin())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("cant_vol_inscritos", tarea.getCant_vol_inscritos())
                    .addParameter("cant_vol_requeridos", tarea.getCant_vol_requeridos())
                    .executeUpdate().getKey();
            tarea.setId(insertedId);
            return tarea;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
