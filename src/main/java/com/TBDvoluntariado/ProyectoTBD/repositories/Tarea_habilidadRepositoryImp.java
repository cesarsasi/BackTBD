package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;
import com.TBDvoluntariado.ProyectoTBD.models.Tarea;
import com.TBDvoluntariado.ProyectoTBD.models.Tarea_habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
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

    public int biggestId() {
        try (Connection conn = sql2o.open()) {
            Tarea_habilidad temp = conn.createQuery("SELECT * FROM tarea_habilidad ORDER BY id DESC").executeAndFetchFirst(Tarea_habilidad.class);
            return temp.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Tarea_habilidad createTarea_hab(Tarea_habilidad tarea_habilidad) {
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO tarea_habilidad (id, id_emehab,id_tarea) values (:id, :id_emehab,:id_tarea)", true)
                    .addParameter("id", id)
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

    @Override
    public void updateTarea_habilidad(int id, Tarea_habilidad tarea_habilidad) {
        String updateSql = "update tarea_habilidad set id_emehab=:id_emehab, id_tarea=:id_tarea where id = :idParam";

        try (Connection con = sql2o.open()) {
            Tarea_habilidad valorAntiguo = con.createQuery("SELECT * FROM tarea_habilidad where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Tarea_habilidad.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(tarea_habilidad.getId_emehab() != null){
                consulta.addParameter("id_emehab", tarea_habilidad.getId_emehab());
            }else{
                consulta.addParameter("id_emehab", valorAntiguo.getId_emehab());
            }
            if(tarea_habilidad.getId_tarea() != null){
                consulta.addParameter("id_tarea", tarea_habilidad.getId_tarea());
            }else{
                consulta.addParameter("id_tarea", valorAntiguo.getId_tarea());
            }
            consulta.executeUpdate();
            System.out.println("La Tarea_habilidad se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTarea_habilidad(int id, Tarea_habilidad tarea_habilidad){
        String deleteSql = "update tarea_habilidad set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Tarea_habilidad valorAntiguo = con.createQuery("SELECT * FROM tarea_habilidad where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Tarea_habilidad.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(tarea_habilidad.getInvisible() != null){
                consulta.addParameter("invisible", tarea_habilidad.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La tarea_habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
