package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.Model.Equipment;
import br.com.uniamerica.Osana.Model.OS;
import br.com.uniamerica.Osana.Model.Priority;
import br.com.uniamerica.Osana.Model.Status;
import br.com.uniamerica.Osana.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OSDTO implements Serializable {
    private Long idOS;
    private String motive;
    private String obs;
    private Date devolution;
    private Date dateOS;
    private Priority priority;
    private Usuario usuario;
    private Equipment equipment;

    private Status status;

    public OSDTO(OS os){
        idOS = os.getIdOS();
        motive = os.getMotive();
        obs = os.getObs();
        devolution = os.getDevolution();
        dateOS = os.getDateOS();
        priority = os.getPriority();
        usuario = os.getUsuario();
        equipment = os.getEquipment();
        status = os.getStatus();
    }
}
