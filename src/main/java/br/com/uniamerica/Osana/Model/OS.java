package br.com.uniamerica.Osana.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
    Set<Equipment> equipment;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Inventory> inventory;

}
