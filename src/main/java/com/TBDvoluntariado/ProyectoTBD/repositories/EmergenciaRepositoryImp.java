package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Emergencia;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
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

    @Override
    public void updateEmergencia(int id, Emergencia emergencia) {
        String updateSql = "update emergencia set nombre=:emergenciaNombre,id_institucion=:id_institucion,finicio=:finicio,ffin=:ffin,descrip=:descrip where id = :idParam";

        try (Connection con = sql2o.open()) {
            Emergencia valorAntiguo = con.createQuery("SELECT * FROM emergencia where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Emergencia.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(emergencia.getNombre() != null){
                consulta.addParameter("emergenciaNombre", emergencia.getNombre());
            }else{
                consulta.addParameter("emergenciaNombre", valorAntiguo.getNombre());
            }
            if(emergencia.getId_institucion() != null){
                consulta.addParameter("id_institucion", emergencia.getId_institucion());
            }else{
                consulta.addParameter("id_institucion", valorAntiguo.getId_institucion());
            }
            if(emergencia.getFinicio() != null){
                consulta.addParameter("finicio", emergencia.getFinicio());
            }else{
                consulta.addParameter("finicio", valorAntiguo.getFinicio());
            }
            if(emergencia.getFfin() != null){
                consulta.addParameter("ffin", emergencia.getFfin());
            }else{
                consulta.addParameter("ffin", valorAntiguo.getFfin());
            }
            if(emergencia.getDescrip() != null){
                consulta.addParameter("descrip", emergencia.getDescrip());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescrip());
            }
            consulta.executeUpdate();
            System.out.println("La emergencia se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteEmergencia(int id, Emergencia emergencia){
        String deleteSql = "update emergencia set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Emergencia valorAntiguo = con.createQuery("SELECT * FROM emergencia where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Emergencia.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(emergencia.getInvisible() != null){
                consulta.addParameter("invisible", emergencia.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La emergencia se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



}
