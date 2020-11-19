package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;
import com.TBDvoluntariado.ProyectoTBD.repositories.Estado_tareaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "estado_tareas")
public class Estado_tareaService {
    private final Estado_tareaRepository estado_tareaRepository;
    Estado_tareaService(Estado_tareaRepository estado_tareaRepository){
        this.estado_tareaRepository = estado_tareaRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Estado_tarea> getAllEstado_tareas() {
        return estado_tareaRepository.getAllEstado_tareas();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEstado_tareas(){
        int total = estado_tareaRepository.countEstado_tareas();
        return String.format("Tienes %s Estado_Tareas!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Estado_tarea getEstado_tareaById(@PathVariable(value = "id") Integer id){
        return this.estado_tareaRepository.getEstado_tareaById(id);
    }

    @PostMapping("/createEstado_tarea")
    @ResponseBody
    public Estado_tarea createEstado_tarea(@RequestBody Estado_tarea estado_tarea){
        Estado_tarea result = estado_tareaRepository.createEstado_tarea(estado_tarea);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateEstado_tarea(@PathVariable(value = "id") int id, Estado_tarea estado_tarea) {
        estado_tareaRepository.updateEstado_tarea(id, estado_tarea);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteEstado_tarea(@PathVariable(value = "id") int id, Estado_tarea estado_tarea){
        estado_tareaRepository.deleteEstado_tarea(id, estado_tarea);
    }
}
