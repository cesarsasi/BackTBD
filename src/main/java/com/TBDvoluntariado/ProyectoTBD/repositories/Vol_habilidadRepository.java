package com.TBDvoluntariado.ProyectoTBD.repositories;
import com.TBDvoluntariado.ProyectoTBD.models.Vol_habilidad;
import com.TBDvoluntariado.ProyectoTBD.models.Voluntario;
import com.TBDvoluntariado.ProyectoTBD.models.Habilidad;

import java.util.List;

public interface Vol_habilidadRepository {

    public List<Vol_habilidad> getAllHabVol();
    public Vol_habilidad getVol_habByIds(Integer id_vol, Integer id_hab);
    public Vol_habilidad createVol_hab(Vol_habilidad vh, int hab_id, int v_id);
    public void updateVh(int id, Vol_habilidad vh);
    public void deleteVh(int id, Vol_habilidad vh);
}
