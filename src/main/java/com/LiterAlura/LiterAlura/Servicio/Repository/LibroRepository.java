package com.LiterAlura.LiterAlura.Servicio.Repository;

import com.LiterAlura.LiterAlura.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro>  findByTitulo(String titulo);
    @Query("select l from Libro l where l.idiomas = :idioma")
    List<Libro> listarLibroIdioma(String idioma);
}
