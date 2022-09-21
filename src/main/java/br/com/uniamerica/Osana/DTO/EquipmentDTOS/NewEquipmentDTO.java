package br.com.uniamerica.Osana.DTO.EquipmentDTOS;

import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.EquipmentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewEquipmentDTO {
    @NotBlank(message = "Name is Required")
    private String name;
    private String model;

    public Equipment toModel(){
        Equipment equipment = new Equipment();
        equipment.setName(getName());
        equipment.setModel(getModel());

        return equipment;
    }
    private String getModel() {
		// TODO Auto-generated method stub
		return null;
	}
    
	public Equipment updatedEquipment(Equipment updateEquiment, EquipmentRepository EquipmentRepository){
        updateEquiment.setName(this.name);
        updateEquiment.setModel(this.model);

        return updateEquiment;
    }
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
