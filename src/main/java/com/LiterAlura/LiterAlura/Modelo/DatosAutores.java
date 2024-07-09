package com.LiterAlura.LiterAlura.Modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutores(
        @JsonAlias("name")  String nombre,
        @JsonAlias("birth_year") int nacimiento,
        @JsonAlias("death_year") int muerte) {
}
