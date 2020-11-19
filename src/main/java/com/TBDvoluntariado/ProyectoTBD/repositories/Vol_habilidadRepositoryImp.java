package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;
import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;
import com.TBDvoluntariado.ProyectoTBD.models.Vol_habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
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

    public int biggestId(){
        try(Connection conn = sql2o.open()){
            Vol_habilidad temp = conn.createQuery("SELECT * FROM vol_habilidad ORDER BY id DESC").executeAndFetchFirst(Vol_habilidad.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Vol_habilidad createVol_hab(Vol_habilidad vh, int hab_id, int v_id){
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO vol_habilidad (id, id_habilidad, id_voluntario) values(:id, :hab_id, :v_id)")
                    .addParameter("id", id)
                    .addParameter("hab_id", hab_id)
                    .addParameter("v_id", v_id)
                    .executeUpdate().getKey();
            vh.setId(id);
            return vh;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateVh(int id, Vol_habilidad vh) {
        String updateSql = "UPDATE vol_habilidad SET id_habilidad=:id_h, id_voluntario=:id_v WHERE id = :idParam";

        try (Connection con = sql2o.open()) {
            Vol_habilidad valorAntiguo = con.createQuery("SELECT * FROM vol_habilidad WHERE id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Vol_habilidad.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(vh.getId_habilidad() != null){
                consulta.addParameter("id_h", vh.getId_habilidad());
            }else{
                consulta.addParameter("id_h", valorAntiguo.getId_habilidad());
            }
            if(vh.getId_voluntario() != null){
                consulta.addParameter("id_v", vh.getId_voluntario());
            }else{
                consulta.addParameter("id_v", valorAntiguo.getId_voluntario());
            }
            consulta.executeUpdate();
            System.out.println("La tabla intermedia Voluntario_Habilidad se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteVh(int id, Vol_habilidad vh) {
        String deleteSql = "UPDATE vol_habilidad SET invisible=:invisible WHERE id = :idParam";
        try (Connection con = sql2o.open()) {
            Vol_habilidad valorAntiguo = con.createQuery("SELECT * FROM vol_habilidad WHERE id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Vol_habilidad.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(vh.getInvisible() != null){
                consulta.addParameter("invisible", vh.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("El voluntario se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}









