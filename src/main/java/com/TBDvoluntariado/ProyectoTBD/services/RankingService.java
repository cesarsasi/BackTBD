package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Ranking;
import com.TBDvoluntariado.ProyectoTBD.repositories.RankingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RankingService {
    public final RankingRepository rankingRepository;
    RankingService(RankingRepository rankingRepository){
        this.rankingRepository = rankingRepository;
    }

    @GetMapping("/rankings")
    public List<Ranking> getAllRankings(){
        return rankingRepository.getAllRankings();
    }

    @GetMapping("/rankings/count")
    public String countRankings(){
        int total = rankingRepository.countRankings();
        return String.format("Tienes %s Rankings!!", total);
    }

    @PostMapping("/ranking")
    @ResponseBody
    public Ranking createRanking(@RequestBody Ranking ranking){
        Ranking result = rankingRepository.createRanking(ranking);
        return result;
    }
}
