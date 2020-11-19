package com.TBDvoluntariado.ProyectoTBD.repositories;



import com.TBDvoluntariado.ProyectoTBD.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
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
            System.out.println("try");
            return conn.createQuery("SELECT * FROM tarea").executeAndFetch(Tarea.class);
        }catch(Exception e){
            System.out.println("catch");
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

    public int biggestId() {
        try (Connection conn = sql2o.open()) {
            Tarea temp = conn.createQuery("SELECT * FROM tarea ORDER BY id DESC").executeAndFetchFirst(Tarea.class);
            return temp.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Tarea createTarea(Tarea tarea){
        int id = this.biggestId() + 1;

        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO tarea (id, id_emergencia, id_estado, nombre, finicio, ffin, descrip, cant_vol_inscritos, cant_vol_requeridos) values(:id, :id_emergencia, :id_estado, :nombre, :finicio, :ffin, :descrip, :cant_vol_inscritos, :cant_vol_requeridos)")
                    .addParameter("id", id)
                    .addParameter("id_emergencia", tarea.getId_emergencia())
                    .addParameter("id_estado", tarea.getId_estado())
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("finicio", tarea.getFinicio())
                    .addParameter("ffin", tarea.getFfin())
                    .addParameter("descrip", tarea.getDescripcion())
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



    @Override
    public Tarea getTareaById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea WHERE id = :tarea_id")
                    .addParameter("tarea_id", id)
                    .executeAndFetchFirst(Tarea.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateTarea(int id, Tarea tarea) {
        String updateSql = "update tarea set id_emergencia=:id_emer, nombre=:nombre, finicio=:finicio, ffin=:ffin, descrip=:descrip, cant_vol_inscritos=:cant_vol_inscritos, cant_vol_requeridos=:cant_vol_requeridos where id = :idParam";

        try (Connection con = sql2o.open()) {
            Tarea valorAntiguo = con.createQuery("SELECT * FROM tarea where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Tarea.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(tarea.getId_emergencia() != null){
                consulta.addParameter("id_emer", tarea.getId_emergencia());
            }else{
                consulta.addParameter("id_emer", valorAntiguo.getId_emergencia());
            }
            if(tarea.getNombre() != null){
                consulta.addParameter("nombre", tarea.getNombre());
            }else{
                consulta.addParameter("nombre", valorAntiguo.getNombre());
            }
            if(tarea.getFinicio() != null){
                consulta.addParameter("finicio", tarea.getFinicio());
            }else{
                consulta.addParameter("finicio", valorAntiguo.getFinicio());
            }
            if(tarea.getFfin() != null){
                consulta.addParameter("ffin", tarea.getFfin());
            }else{
                consulta.addParameter("ffin", valorAntiguo.getFfin());
            }
            if(tarea.getDescripcion() != null){
                consulta.addParameter("descrip", tarea.getDescripcion());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescripcion());
            }
            if(tarea.getCant_vol_inscritos() != null){
                consulta.addParameter("cant_vol_inscritos", tarea.getCant_vol_inscritos());
            }else{
                consulta.addParameter("cant_vol_inscritos", valorAntiguo.getCant_vol_inscritos());
            }
            if(tarea.getCant_vol_requeridos() != null){
                consulta.addParameter("cant_vol_requeridos", tarea.getCant_vol_requeridos());
            }else{
                consulta.addParameter("cant_vol_requeridos", valorAntiguo.getCant_vol_requeridos());
            }
            consulta.executeUpdate();
            System.out.println("La tarea se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTarea(int id, Tarea tarea){
        String deleteSql = "update tarea set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Tarea valorAntiguo = con.createQuery("SELECT * FROM tarea where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Tarea.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(tarea.getInvisible() != null){
                consulta.addParameter("invisible", tarea.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La tarea se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
