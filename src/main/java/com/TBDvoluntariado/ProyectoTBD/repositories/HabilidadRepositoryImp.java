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
public class HabilidadRepositoryImp implements HabilidadRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countHabilidades() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Habilidad> getAllHabilidades() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from habilidad")
                    .executeAndFetch(Habilidad.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Habilidad getHabilidadById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM habilidad WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Habilidad.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
        Metodo que obtiene el mayor ID de la tabla habilidad
     */
    public int biggestIdHab(){
        try(Connection conn = sql2o.open()){
            Habilidad temp = conn.createQuery("SELECT * FROM habilidad ORDER BY id DESC").executeAndFetchFirst(Habilidad.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Habilidad createHabilidad(Habilidad habilidad){
        int id = this.biggestIdHab() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO habilidad (id,descrip) values (:id,:descrip)", true)
                    .addParameter("id", id)
                    .addParameter("descrip", habilidad.getDescrip())
                    .executeUpdate().getKey();
            habilidad.setId(id);
            return habilidad;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

}
