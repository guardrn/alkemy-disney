package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "generos")
@Getter
@Setter
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGenero;

    private String imagen;

    @OneToMany(mappedBy = "generos", fetch = FetchType.LAZY)
    private List<Pelicula> peliculas;

}
