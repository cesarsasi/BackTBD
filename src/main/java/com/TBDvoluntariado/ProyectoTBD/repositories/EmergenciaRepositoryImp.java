package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Emergencia;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergenciaRepositoryImp implements EmergenciaRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countEmergencias() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM emergencia").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Emergencia> getAllEmergencias() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from emergencia")
                    .executeAndFetch(Emergencia.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia getEmergenciaById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM emergencia WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Emergencia.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*
        Metodo que obtiene el mayor ID de la tabla emergencia
     */
    public int biggestIdEme(){
        try(Connection conn = sql2o.open()){
            Emergencia temp = conn.createQuery("SELECT * FROM emergencia ORDER BY id DESC").executeAndFetchFirst(Emergencia.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Emergencia createEmergencia(Emergencia emergencia){
        int id = this.biggestIdEme() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO emergencia (id,nombre,id_institucion,finicio,ffin,descrip) values (:id,:emergenciaNombre,:id_institucion,:finicio,:ffin,:descrip)", true)
                    .addParameter("id", id)
                    .addParameter("emergenciaNombre", emergencia.getNombre())
                    .addParameter("id_institucion", emergencia.getId_institucion())
                    .addParameter("finicio", emergencia.getFinicio())
                    .addParameter("ffin", emergencia.getFfin())
                    .addParameter("descrip", emergencia.getDescrip())
                    .executeUpdate().getKey();
            emergencia.setId(id);
            return emergencia;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }



}
