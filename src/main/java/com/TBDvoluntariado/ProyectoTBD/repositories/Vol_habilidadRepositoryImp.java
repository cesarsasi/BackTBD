package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;
import com.TBDvoluntariado.ProyectoTBD.models.Vol_habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.List;

@Repository
public class Vol_habilidadRepositoryImp implements Vol_habilidadRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Vol_habilidad> getAllHabVol(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM vol_habilidad").executeAndFetch(Vol_habilidad.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vol_habilidad getVol_habByIds(Integer id_vol, Integer id_hab){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM vol_habilidad WHERE id_habilidad = :id_hab AND id_voluntario = :id_vol")
                    .addParameter("id_hab", id_hab)
                    .addParameter("id_vol", id_vol)
                    .executeAndFetchFirst(Vol_habilidad.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vol_habilidad createVol_hab(Vol_habilidad vh, int hab_id, int v_id){
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO vol_habilidad (id_habilidad, id_voluntario) values(:hab_id, :v_id)")
                    .addParameter("hab_id", hab_id)
                    .addParameter("v_id", v_id)
                    .executeUpdate().getKey();
            vh.setId(insertedId);
            return vh;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}









