package com.TBDvoluntariado.ProyectoTBD.services;

import com.TBDvoluntariado.ProyectoTBD.models.Vol_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;
import com.TBDvoluntariado.ProyectoTBD.repositories.Vol_habilidadRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "vol_hab")
public class Vol_habilidadService {

    private final Vol_habilidadRepository vhRepo;
    Vol_habilidadService(Vol_habilidadRepository vhRepo){
        this.vhRepo = vhRepo;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Vol_habilidad> getAllHabVol(){
        return this.vhRepo.getAllHabVol();
    }

    @PostMapping("createVolHab")
    @ResponseBody
    public Vol_habilidad createVol_hab(@RequestBody Voluntario v, Habilidad h, Vol_habilidad vol_hab){
        int hab_id = h.getId();
        int vol_id = v.getId();
        Vol_habilidad newVol_habilidad = vhRepo.createVol_hab(vol_hab, hab_id, vol_id);
        return newVol_habilidad;
    }
}
