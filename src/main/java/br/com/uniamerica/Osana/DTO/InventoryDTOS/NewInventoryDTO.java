package br.com.uniamerica.Osana.DTO.InventoryDTOS;

import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewInventoryDTO {
    @NotBlank(message = "Name is Required")
    private String name;
    private String model;

    public Inventory toModel(){
        Inventory inventory = new Inventory();
        inventory.setName(getName());

        return inventory;
    }
    public Inventory updatedInventory(Inventory updateInventory, InventoryRepository inventoryRepository){
        updateInventory.setName(this.name);

        return updateInventory;
    }
}
