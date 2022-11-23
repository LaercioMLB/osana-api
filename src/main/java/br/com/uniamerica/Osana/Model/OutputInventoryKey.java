package br.com.uniamerica.Osana.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OutputInventoryKey implements Serializable {
    private Long osId;
    private Long inventoryId;

    public Long getOsId() {
        return osId;
    }

    public void setOsId(Long osId) {
        this.osId = osId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputInventoryKey)) return false;
        OutputInventoryKey outputInventoryKey = (OutputInventoryKey) o;
        return Objects.equals(getOsId(), outputInventoryKey.getOsId()) &&
                Objects.equals(getInventoryId(), outputInventoryKey.getInventoryId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOsId(), getInventoryId());
    }
}
