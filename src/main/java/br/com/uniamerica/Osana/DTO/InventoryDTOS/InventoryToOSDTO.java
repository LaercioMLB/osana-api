package br.com.uniamerica.Osana.DTO.InventoryDTOS;

import br.com.uniamerica.Osana.Model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryToOSDTO implements Serializable {
    private Long id;
    private Integer quantity;

    public InventoryToOSDTO(Inventory inventory){
        id = inventory.getId();
        quantity = inventory.getQuantity();
    }
}
