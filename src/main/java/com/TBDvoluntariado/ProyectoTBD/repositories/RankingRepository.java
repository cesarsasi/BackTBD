package com.TBDvoluntariado.ProyectoTBD.repositories;

import com.TBDvoluntariado.ProyectoTBD.models.Ranking;

import java.util.List;

public interface RankingRepository {
    public int countRankings();
    public List<Ranking> getAllRankings();
    public Ranking getRankingById(Integer id);
    public Ranking createRanking(Ranking ranking);
    public void updateRanking(int id, Ranking ranking);
    public void deleteRanking(int id, Ranking ranking);
}
