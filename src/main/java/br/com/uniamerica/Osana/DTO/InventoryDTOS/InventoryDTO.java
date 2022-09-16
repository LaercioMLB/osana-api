package br.com.uniamerica.Osana.DTO.InventoryDTOS;

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
public class InventoryDTO implements Serializable {
    private Long id;
    private String name;



    public InventoryDTO(Inventory inventory){
        id = inventory.getId();
        name = inventory.getName();

    }
}
