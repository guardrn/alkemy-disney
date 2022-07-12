package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPelicula;

    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private Integer calificacion;

    @ManyToMany(mappedBy = "peliculas", fetch = FetchType.LAZY)
    private List<Personaje> personajes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero")
    private Genero genero;

}
