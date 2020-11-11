package com.TBDvoluntariado.ProyectoTBD.services;


import com.TBDvoluntariado.ProyectoTBD.models.Tarea_habilidad;
import com.TBDvoluntariado.ProyectoTBD.repositories.Tarea_habilidadRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "tarea_hab")
public class Tarea_habilidadService {
    private final Tarea_habilidadRepository tarea_habilidadRepository;
    Tarea_habilidadService(Tarea_habilidadRepository tarea_habilidadRepository){
        this.tarea_habilidadRepository = tarea_habilidadRepository;
    }

    @GetMapping("/getAll")
    public List<Tarea_habilidad> getAllTarea_habilidad() {
        return tarea_habilidadRepository.getAllTarea_habs();
    }

    @GetMapping("/count")
    public String countEmergencias(){
        int total = tarea_habilidadRepository.countTarea_habilidades();
        return String.format("Tienes %s tareas_habilidades!!", total);
    }

    @PostMapping("/create")
    @ResponseBody
    public Tarea_habilidad createTarea_hab(@RequestBody Tarea_habilidad tarea_habilidad){
        Tarea_habilidad result = tarea_habilidadRepository.createTarea_hab(tarea_habilidad);
        return result;
    }

}
