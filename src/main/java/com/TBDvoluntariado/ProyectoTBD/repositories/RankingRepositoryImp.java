package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RankingRepositoryImp implements  RankingRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countRankings(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM ranking").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Ranking> getAllRankings(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from ranking")
                    .executeAndFetch(Ranking.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Ranking createRanking(Ranking ranking){
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO ranking (id_tarea, id_voluntario, puntaje, flg_invitado, flg_participa) values (:id_tarea, :id_voluntario, :puntaje, :flg_invitado, :flg_participa)", true)
                    .addParameter("id_tarea", ranking.getId_tarea())
                    .addParameter("id_voluntario", ranking.getId_voluntario())
                    .addParameter("puntaje", ranking.getPuntaje())
                    .addParameter("flg_invitado", ranking.getFlg_invitado())
                    .addParameter("flg_participa", ranking.getFlg_participa())
                    .executeUpdate().getKey();
            ranking.setId(insertedId);
            return ranking;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
