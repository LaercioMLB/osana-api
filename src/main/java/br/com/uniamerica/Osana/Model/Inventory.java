package br.com.uniamerica.Osana.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OutputInventory> outputInventories = new HashSet<>();

    public void diminuirQuantity(Integer quantity){
        if (this.quantity >= quantity){
            this.quantity = this.quantity - quantity;
        }
    }

    public void incrementarQuantity(Integer quantity){
        this.quantity = this.quantity + quantity;
    }

    @JsonIgnore
    public Set<OS> getOs() {
        Set<OS> set = new HashSet<>();
        for(OutputInventory x : outputInventories) {
            set.add(x.getOs());
        }

        return set;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Inventory other = (Inventory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
