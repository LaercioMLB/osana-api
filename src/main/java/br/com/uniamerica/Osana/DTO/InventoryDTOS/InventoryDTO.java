package br.com.uniamerica.Osana.DTO.InventoryDTOS;

import br.com.uniamerica.Osana.Model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class InventoryDTO implements Serializable {
	private static final long serialVersionUID = -142325761316440847L;
	
	private Map<String, ?> id;
    private String name;



    public InventoryDTO(Inventory inventory){
        id = inventory.getId();
        name = inventory.getName();

    }
}
