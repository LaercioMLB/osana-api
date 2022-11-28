package br.com.uniamerica.Osana.Model;

import br.com.uniamerica.Osana.DTO.InventoryDTOS.InventoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OS implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOS;

    private String motive;
    private String obs;
    private Date devolution;
    private Date dateOS;

    {
        this.dateOS = new Date();
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Status status;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Priority priority;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TypeServices typeServices;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Equipment> equipment;

    @OneToMany(mappedBy = "os", cascade = CascadeType.ALL)
    private Set<OutputInventory> outputInventories = new HashSet<>();

    public Set<OutputInventory> getOutputInventories() {
        return outputInventories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idOS == null) ? 0 : idOS.hashCode());
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
        OS other = (OS) obj;
        if (idOS == null) {
            if (other.idOS != null)
                return false;
        } else if (!idOS.equals(other.idOS))
            return false;
        return true;
    }

}
