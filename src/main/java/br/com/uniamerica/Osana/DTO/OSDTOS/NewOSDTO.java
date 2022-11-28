package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.DTO.InventoryDTOS.InventoryDTO;
import br.com.uniamerica.Osana.DTO.InventoryDTOS.InventoryToOSDTO;
import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewOSDTO implements Serializable {
    @NotNull()
    private String motive;
    private String obs;
    private Date devolution;
    @NotNull()
    private Integer idPriority;
    private Integer idStatus;
    @NotNull()
    private Integer idTypeServices;
    @NotNull()
    private Integer idClient;
    @NotNull()
    private Integer idUsuario;

    private Set<Integer> equipaments;
    private HashSet<InventoryToOSDTO> inventories;

    public OS toModel(Set<Equipment> lista_obj_equipament){
        OS os = new OS();
        os.setMotive(this.motive);
        os.setObs(this.obs);
        os.setDevolution(this.devolution);
        os.setEquipment(lista_obj_equipament);

        return os;
    }
}
