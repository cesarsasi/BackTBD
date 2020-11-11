package com.TBDvoluntariado.ProyectoTBD.services;


import com.TBDvoluntariado.ProyectoTBD.models.Tarea;
import com.TBDvoluntariado.ProyectoTBD.repositories.TareaRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "tarea")
public class TareaService {
    private final TareaRepository tareaRepo;
    TareaService(TareaRepository tareaRepo){
        this.tareaRepo = tareaRepo;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Tarea> getAllTarea(){
        return this.tareaRepo.getAllTareas();
    }

    @PostMapping("createVolHab")
    @ResponseBody
    public Tarea createTarea(@RequestBody Tarea tarea){
        int emer_id = tarea.getId_emergencia();
        int estTarea_id = tarea.getId_estado();
        String nombre = tarea.getNombre();
        Date finicio = tarea.getFinicio();
        Date ffin = tarea.getFfin();
        String descripcion = tarea.getDescripcion();
        Integer cant_vol_inscritos = tarea.getCant_vol_inscritos();
        Integer cant_vol_requeridos = tarea.getCant_vol_requeridos();
        Tarea newTarea = tareaRepo.createTarea(tarea);
        return newTarea;
    }
}

