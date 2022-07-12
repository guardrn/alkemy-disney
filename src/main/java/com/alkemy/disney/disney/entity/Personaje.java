package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personajes")
@Getter
@Setter
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPersonaje;

    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personajes_peliculas",
            joinColumns = {@JoinColumn(name = "id_personaje")},
            inverseJoinColumns = {@JoinColumn(name = "id_pelicula")}
    )
    private List<Pelicula> peliculas;

}
