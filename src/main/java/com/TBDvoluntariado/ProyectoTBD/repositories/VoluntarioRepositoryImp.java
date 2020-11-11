package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Voluntario> getAllVoluntarios(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM voluntario").executeAndFetch(Voluntario.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntario getVoluntarioById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM voluntario WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Voluntario.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Voluntario createVoluntario(Voluntario voluntario){
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO voluntario (fnacimiento, nombre) values(:fnacimiento, :nombre)")
                        .addParameter("fnacimiento", voluntario.getFnacimiento())
                        .addParameter("nombre", voluntario.getNombre())
                        .executeUpdate().getKey();
            voluntario.setId(insertedId);
            return voluntario;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }
}
