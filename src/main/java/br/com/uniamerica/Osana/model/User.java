package br.com.uniamerica.Osana.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String contact;
    private String username;
    private String password;

    @OneToMany
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private List<OS> os = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }
}