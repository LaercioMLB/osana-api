package br.com.uniamerica.Osana.DTO.InventoryDTOS;

import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewInventoryDTO {
    @NotNull(message = "Name is Required")
    private String name;
    private Integer quantity;

    public Inventory toModel(){
        Inventory inventory = new Inventory();
        inventory.setName(this.name);
        inventory.setQuantity(this.quantity);

        return inventory;
    }
    public Inventory updatedInventory(Inventory updateInventory, InventoryRepository inventoryRepository){
        updateInventory.setName(this.name);
        updateInventory.setQuantity(this.quantity);

        return updateInventory;
    }
}
