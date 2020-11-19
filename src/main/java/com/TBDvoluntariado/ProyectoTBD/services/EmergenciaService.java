package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Emergencia;
import com.TBDvoluntariado.ProyectoTBD.models.Estado_tarea;
import com.TBDvoluntariado.ProyectoTBD.models.Institucion;
import com.TBDvoluntariado.ProyectoTBD.repositories.EmergenciaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "emergencias")
public class EmergenciaService {

    private final EmergenciaRepository emergenciaRepository;
    EmergenciaService(EmergenciaRepository emergenciaRepository){
        this.emergenciaRepository = emergenciaRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Emergencia> getAllEmergencias() {
        return emergenciaRepository.getAllEmergencias();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEmergencias(){
        int total = emergenciaRepository.countEmergencias();
        return String.format("Tienes %s emergencias!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Emergencia getEmergenciaById(@PathVariable(value = "id") Integer id){
        return this.emergenciaRepository.getEmergenciaById(id);
    }

    @PostMapping("/createEmergencia")
    @ResponseBody
    public Emergencia createEmergencia(@RequestBody Emergencia emergencia){
        Emergencia result = emergenciaRepository.createEmergencia(emergencia);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateEmergencia(@PathVariable(value = "id") int id, Emergencia emergencia) {
        emergenciaRepository.updateEmergencia(id, emergencia);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteEmergencia(@PathVariable(value = "id") int id, Emergencia emergencia){
        emergenciaRepository.deleteEmergencia(id, emergencia);
    }
    
}
