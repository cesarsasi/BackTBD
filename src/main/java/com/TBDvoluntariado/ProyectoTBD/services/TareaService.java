package com.TBDvoluntariado.ProyectoTBD.services;


import com.TBDvoluntariado.ProyectoTBD.models.Ranking;
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

    @PostMapping("/create")
    @ResponseBody
    public Tarea createTarea(@RequestBody Tarea tarea){ return tareaRepo.createTarea(tarea); }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Tarea getTareaById(@PathVariable(value = "id") Integer id){
        return this.tareaRepo.getTareaById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateTarea(@PathVariable(value = "id") int id, Tarea tarea){
        tareaRepo.updateTarea(id, tarea);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteTarea(@PathVariable(value = "id") int id, Tarea tarea){
        tareaRepo.deleteTarea(id, tarea);
    }

    @RequestMapping(value = "/deleteOldWithHour/{hours}")
    @ResponseBody
    public void deleteInactives(@PathVariable(value="hours") int hours) {
        tareaRepo.deleteInactivesTareas(hours);
    }

}

