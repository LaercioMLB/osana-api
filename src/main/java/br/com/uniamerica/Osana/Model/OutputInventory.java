package br.com.uniamerica.Osana.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class OutputInventory implements Serializable{
    @EmbeddedId
    private OutputInventoryKey id = new OutputInventoryKey();
    @ManyToOne
    @MapsId("osId")
    @JoinColumn(name = "os_idOS")
    private OS os;
    @ManyToOne
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    private Integer quantity;

    public OutputInventoryKey getId() {
        return id;
    }

    public void setId(OutputInventoryKey id) {
        this.id = id;
    }

    @JsonIgnore
    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void diminuirQuantity(Integer quantity){
        if (this.quantity >= quantity){
            this.quantity = this.quantity - quantity;
        }
    }

    public void incrementarQuantity(Integer quantity){
        this.quantity = this.quantity + quantity;
    }
}
