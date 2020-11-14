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
    /*
        Metodo que obtiene el mayor ID de la tabla voluntario
     */
    public int biggestId(){
        try(Connection conn = sql2o.open()){
            Voluntario temp = conn.createQuery("SELECT * FROM voluntario ORDER BY id DESC").executeAndFetchFirst(Voluntario.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Voluntario createVoluntario(Voluntario voluntario){
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO voluntario (id, nombre, fnacimiento) VALUES(:id, :nombre, :fnacimiento)", true)
                        .addParameter("id", id)
                        .addParameter("nombre", voluntario.getNombre())
                        .addParameter("fnacimiento", voluntario.getFnacimiento())
                        .executeUpdate().getKey();
            voluntario.setId(id);
            return voluntario;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }
}
