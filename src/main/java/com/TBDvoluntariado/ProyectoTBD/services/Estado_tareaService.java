package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;
import com.TBDvoluntariado.ProyectoTBD.repositories.Estado_tareaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class Estado_tareaService {
    private final Estado_tareaRepository estado_tareaRepository;
    Estado_tareaService(Estado_tareaRepository estado_tareaRepository){
        this.estado_tareaRepository = estado_tareaRepository;
    }

    @GetMapping("/estado_tareas")
    public List<Estado_tarea> getAllEstado_tareas() {
        return estado_tareaRepository.getAllEstado_tareas();
    }

    @GetMapping("/estado_tareas/count")
    public String countEstado_tareas(){
        int total = estado_tareaRepository.countEstado_tareas();
        return String.format("Tienes %s Estado_Tareas!!", total);
    }

    @PostMapping("/estado_tarea")
    @ResponseBody
    public Estado_tarea createEstado_tarea(@RequestBody Estado_tarea estado_tarea){
        Estado_tarea result = estado_tareaRepository.createEstado_tarea(estado_tarea);
        return result;
    }
}
