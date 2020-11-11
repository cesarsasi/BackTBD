package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;
import com.TBDvoluntariado.ProyectoTBD.repositories.VoluntarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "voluntario")
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    VoluntarioService(VoluntarioRepository voluntarioRepository){
        this.voluntarioRepository = voluntarioRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Voluntario> getAllVoluntarios(){
        return this.voluntarioRepository.getAllVoluntarios();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Voluntario getVoluntarioById(@PathVariable(value = "id") Integer id){
        return this.voluntarioRepository.getVoluntarioById(id);
    }


    @PostMapping("createVoluntario")
    @ResponseBody
    public Voluntario createVoluntario(@RequestBody Voluntario v){
        Voluntario newVoluntario = voluntarioRepository.createVoluntario(v);
        return newVoluntario;
    }

}
