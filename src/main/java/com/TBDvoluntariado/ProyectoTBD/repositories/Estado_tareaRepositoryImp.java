package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
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
    public Estado_tarea getEstado_tareaById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM estado_tarea WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Estado_tarea.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
        Metodo que obtiene el mayor ID de la tabla Estado_tarea
     */
    public int biggestIdEstado_tarea(){
        try(Connection conn = sql2o.open()){
            Estado_tarea temp = conn.createQuery("SELECT * FROM estado_tarea ORDER BY id DESC").executeAndFetchFirst(Estado_tarea.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Estado_tarea createEstado_tarea(Estado_tarea estado_tarea){
        int id = this.biggestIdEstado_tarea() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO estado_tarea (id,descrip) values (:id,:descrip)", true)
                    .addParameter("id", id)
                    .addParameter("descrip", estado_tarea.getDescrip())
                    .executeUpdate().getKey();
            estado_tarea.setId(id);
            return estado_tarea;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public void updateEstado_tarea(int id, Estado_tarea estado_tarea) {
        String updateSql = "update estado_tarea set descrip=:descrip where id = :idParam";

        try (Connection con = sql2o.open()) {
            Estado_tarea valorAntiguo = con.createQuery("SELECT * FROM estado_tarea where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Estado_tarea.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(estado_tarea.getDescrip() != null){
                consulta.addParameter("descrip", estado_tarea.getDescrip());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescrip());
            }
            consulta.executeUpdate();
            System.out.println("La Estado_tarea se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteEstado_tarea(int id, Estado_tarea estado_tarea){
        String deleteSql = "update eme_habilidad set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Estado_tarea valorAntiguo = con.createQuery("SELECT * FROM eme_habilidad where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Estado_tarea.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(estado_tarea.getInvisible() != null){
                consulta.addParameter("invisible", estado_tarea.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La Estado_tarea se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
