package br.com.uniamerica.Osana.Model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OS {
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
    private Priority priority;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Equipment equipment;

}
