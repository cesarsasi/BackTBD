package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Eme_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Emergencia;
import com.TBDvoluntariado.ProyectoTBD.repositories.Eme_habilidadRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "eme_habs")
public class Eme_habilidadService {

    private final Eme_habilidadRepository eme_habilidadRepository;
    Eme_habilidadService(Eme_habilidadRepository eme_habilidadRepository){
        this.eme_habilidadRepository = eme_habilidadRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Eme_habilidad> getAllEme_habs() {
        return eme_habilidadRepository.getAllEme_habs();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEmergencias(){
        int total = eme_habilidadRepository.countEme_habs();
        return String.format("Tienes %s emergencias_habilidades!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Eme_habilidad getEme_habilidadById(@PathVariable(value = "id") Integer id){
        return this.eme_habilidadRepository.getEme_habilidadById(id);
    }

    @PostMapping("/createEme_hab")
    @ResponseBody
    public Eme_habilidad createEmergencia(@RequestBody Eme_habilidad eme_habilidad){
        Eme_habilidad result = eme_habilidadRepository.createEme_hab(eme_habilidad);
        return result;
    }

}
