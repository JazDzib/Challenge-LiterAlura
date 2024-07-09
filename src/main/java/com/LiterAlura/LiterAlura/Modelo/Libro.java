package com.LiterAlura.LiterAlura.Modelo;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private  Autor autor;
    private String idiomas;
    private int descargas;

    public Libro(DatosLibro libro){
        this.id = libro.id();
        this.titulo = libro.titulo();
        this.autor = new Autor(libro.autores().get(0));
        this.idiomas = String.valueOf(libro.idiomas().get(0));
        this.descargas = libro.numDescargas();


    }

    public Libro() {

    }


    public long getId() {
        return id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "+++++++++Libro++++++++\n" +
                "id: " + id + "\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor.getNombre() + "\n" +
                "Idiomas: " + idiomas + "\n"+
                "Descargas: " + descargas +"\n" +
                "++++++++++++++++++++++++++++";
    }
}
