package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDTO;
import br.com.uniamerica.Osana.Model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private UsuarioDTO usuario;
    private Status idStatus;
    private Priority priority;
    private TypeServices typeServices;
    private Client client;
    private Set<Equipment> equipment;
    private Set<OutputInventory> outputInventories;

    private static List<OSDTO> osDTOS;


    public OSDTO(OS os){
        idOS = os.getIdOS();
        motive = os.getMotive();
        obs = os.getObs();
        devolution = os.getDevolution();
        dateOS = os.getDateOS();
        usuario = new UsuarioDTO(os.getUsuario());
        idStatus = os.getStatus();
        priority = os.getPriority();
        typeServices = os.getTypeServices();
        client = os.getClient();
        equipment = os.getEquipment();
        outputInventories = os.getOutputInventories();
    }

    public static List<OSDTO> convert(List<OS> osList) {
        osDTOS = new ArrayList<>();
        osDTOS.addAll(osList.stream().map(OSDTO::new).collect(Collectors.toList()));
        return osDTOS;
    }

}
