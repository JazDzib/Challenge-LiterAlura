package com.LiterAlura.LiterAlura.Servicio.Repository;

import com.LiterAlura.LiterAlura.Modelo.Autor;
import com.LiterAlura.LiterAlura.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);
    @Query("select  a FROM Autor a where  a.fechaNacimiento <= :aniobusqueda and a.fechaFallecimiento >= :aniobusqueda")
    List<Autor> listarAutroesAnioVivo(int aniobusqueda);
}
