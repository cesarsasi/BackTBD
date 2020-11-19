package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Institucion;
import com.TBDvoluntariado.ProyectoTBD.repositories.InstitucionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "institucion")
public class InstitucionService {
    private final InstitucionRepository institucionRepository;

    InstitucionService(InstitucionRepository institucionRepository) {
        this.institucionRepository = institucionRepository;
    }

    @GetMapping("/getAll")
    public List<Institucion> getAllInstituciones() {
        return institucionRepository.getAllInstituciones();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Institucion getInstitucionById(@PathVariable(value = "id") Integer id) {
        return this.institucionRepository.getInstitucionById(id);
    }

    @GetMapping("/count")
    public String countInstituciones() {
        int total = institucionRepository.countInstituciones();
        return String.format("Tienes %s instituciones!!", total);
    }

    @PostMapping("/createInstitucion")
    @ResponseBody
    public Institucion createInstitucion(@RequestBody Institucion institucion) {
        Institucion result = institucionRepository.createInstitucion(institucion);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateInstitucion(@PathVariable(value = "id") int id, Institucion institucion) {
        institucionRepository.updateInstitucion(id, institucion);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteInstitucion(@PathVariable(value = "id") int id, Institucion institucion){
        institucionRepository.deleteInstitucion(id, institucion);
    }
}
