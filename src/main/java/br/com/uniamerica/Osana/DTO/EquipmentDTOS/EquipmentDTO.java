package br.com.uniamerica.Osana.DTO.OSDTOS;

import br.com.uniamerica.Osana.Model.*;
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
public class EquipmentDTO implements Serializable {
    private Long id;
    private String name;
    private String model;



    public EquipmentDTO(Equipment equipment){
        id = equipment.getId();
        name = equipment.getName();
        model = equipment.getModel();

    }
}
