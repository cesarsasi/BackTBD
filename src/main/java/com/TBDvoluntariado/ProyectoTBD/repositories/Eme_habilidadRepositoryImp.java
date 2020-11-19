package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Eme_habilidadRepositoryImp implements Eme_habilidadRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countEme_habs(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM eme_habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Eme_habilidad> getAllEme_habs() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from eme_habilidad")
                    .executeAndFetch(Eme_habilidad.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Eme_habilidad getEme_habilidadById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM eme_habilidad WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Eme_habilidad.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*
        Metodo que obtiene el mayor ID de la tabla eme_habilidad
     */
    public int biggestIdeme_hab(){
        try(Connection conn = sql2o.open()){
            Eme_habilidad temp = conn.createQuery("SELECT * FROM eme_habilidad ORDER BY id DESC").executeAndFetchFirst(Eme_habilidad.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Eme_habilidad createEme_hab(Eme_habilidad eme_habilidad){
        int id = this.biggestIdeme_hab() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO eme_habilidad (id,id_emergencia,id_habilidad) values (:id,:id_emer,:id_hab)", true)
                    .addParameter("id", id)
                    .addParameter("id_emer", eme_habilidad.getId_emergencia())
                    .addParameter("id_hab", eme_habilidad.getId_habilidad())
                    .executeUpdate().getKey();
            eme_habilidad.setId(id);
            return eme_habilidad;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

}
