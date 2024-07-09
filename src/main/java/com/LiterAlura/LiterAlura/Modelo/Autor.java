package com.LiterAlura.LiterAlura.Modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private  String nombre;
    private int fechaNacimiento;
    private int fechaFallecimiento;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro > libros;


    public Autor(DatosAutores datosAutores){
        this.nombre = datosAutores.nombre();
        this.fechaNacimiento = datosAutores.nacimiento();
        this.fechaFallecimiento = datosAutores.muerte();
    }


    public Autor() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(int fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


    @Override
    public String toString() {
        String nombres = libros.stream()
                .map(l -> l.getTitulo()).collect(Collectors.joining("; "));
        return "+++++++++Libro++++++++\n" +
                "Nombre: " + nombre + "\n" +
                "Nacimiento: " + fechaNacimiento + "\n" +
                "Fallecimiento: " + fechaFallecimiento + "\n"+
                "Libros: " + nombres + "\n"+
                "++++++++++++++++++++++++++";
    }
}
