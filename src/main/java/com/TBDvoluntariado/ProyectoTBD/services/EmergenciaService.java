package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.repositories.EmergenciaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EmergenciaService {

    private final EmergenciaRepository emergenciaRepository;
    EmergenciaService(EmergenciaRepository emergenciaRepository){
        this.emergenciaRepository = emergenciaRepository;
    }

    @GetMapping("/emergencia/count")
    public String countEmergencia(){
        int total = emergenciaRepository.countEmergencia();
        return String.format("Tienes %s emergencias!!", total);
    }
    
}
