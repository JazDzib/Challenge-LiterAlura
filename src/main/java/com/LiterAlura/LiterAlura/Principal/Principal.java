package com.LiterAlura.LiterAlura.Principal;

import com.LiterAlura.LiterAlura.Modelo.Autor;
import com.LiterAlura.LiterAlura.Modelo.DatosBusqueda;
import com.LiterAlura.LiterAlura.Modelo.DatosLibro;
import com.LiterAlura.LiterAlura.Modelo.Libro;
import com.LiterAlura.LiterAlura.Servicio.Repository.AutorRepository;
import com.LiterAlura.LiterAlura.Servicio.ConsumoAPI;
import com.LiterAlura.LiterAlura.Servicio.ConvierteDatos;
import com.LiterAlura.LiterAlura.Servicio.Repository.LibroRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository repositorioLibro,AutorRepository repositorioAutor){
        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor =  repositorioAutor;
    }
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos convierteDaros = new ConvierteDatos();
    private String idiomas= """
            es = español
            en = ingles
            pt = portuges
            de = aleman
            """;
    private List<Libro> libroList;
    private List<Autor> autoreslist;
    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0){
            var menu= """
                 Menu
                1. Buscar Libro
                2.Lista de Libros
                3.Lista de autores
                4.Listar autores por año vivo
                5.Listar libros por idioma
                0. Salir               
                """;
            System.out.println(menu);
            opcion= sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1: buscaLibro();
                    break;
                case 2: listarLibros();
                    break;
                case 3: listarAutores();
                    break;
                case 4: listarAutroesAnioVivo();
                    break;
                case 5: listarLibroIdioma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Introduzca una opcion valida");
                    break;
            }
        }


    }
    private  void buscaLibro(){
        System.out.println("Introduzca un nombre de libro: ");
        var nameLibro = sc.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nameLibro.replace(" ","+"));

        DatosBusqueda datosBusqueda =convierteDaros.obtenerDatos(json,DatosBusqueda.class);
        List<DatosLibro> datosLibroList = datosBusqueda.resultados();

        if (!datosLibroList.isEmpty()){
            DatosLibro libroEncontrado = datosLibroList.get(0);
            Libro libro = new Libro(libroEncontrado);


            //comprueva que existe el autor
            String nombreAutor= libro.getAutor().getNombre();
            Autor autor = repositorioAutor.findByNombre(nombreAutor);
            if(autor == null){
                autor = new Autor(libroEncontrado.autores().get(0));
                repositorioAutor.save(autor);
            }
            libro.setAutor(autor);
            //COmprobar que el libro no se repite
            String nombreLibro = libro.getTitulo();
            Optional<Libro> librocomprueba = repositorioLibro.findByTitulo(nombreLibro);
            if (librocomprueba.isEmpty()){
                //guarda el autor en la BD
                repositorioLibro.save(libro);
            }else {
                System.out.println("El libro ya a sido agregado anteriormente");
            }


            System.out.println(libro);
        }else{
            System.out.println("Libro no encontado ");
        }


    }

    private void listarLibros(){
        libroList = repositorioLibro.findAll();
        libroList.forEach(System.out::println);

    }
    private void listarAutores(){
        autoreslist =repositorioAutor.findAll();
        autoreslist.forEach(System.out::println);
    }
    private void listarAutroesAnioVivo(){
        System.out.println("Introduce el año en el cual desea buscar");
        var anio = sc.nextInt();
        sc.nextLine();
        List<Autor> autoranio=repositorioAutor.listarAutroesAnioVivo(anio);
        if (!autoranio.isEmpty()){
            autoranio.forEach(System.out::println);
        }else {
            System.out.println("No se encontro ningun autor vivo en ese año");
        }

    }
    private  void listarLibroIdioma(){
        List<Libro> librosEncontrado;
        System.out.println(idiomas);
        System.out.println("Introduce el idioma que desea buscar: ");
        var idiomaBusca = sc.nextLine();
        if(idiomaBusca.equalsIgnoreCase("es")){
            librosEncontrado = repositorioLibro.listarLibroIdioma("es");
        } else if (idiomaBusca.equalsIgnoreCase("en")) {
            librosEncontrado = repositorioLibro.listarLibroIdioma("en");
        } else if (idiomaBusca.equalsIgnoreCase("pt")) {
            librosEncontrado = repositorioLibro.listarLibroIdioma("pt");
        }else if (idiomaBusca.equalsIgnoreCase("de")){
            librosEncontrado = repositorioLibro.listarLibroIdioma("de");
        }else {
            System.out.println(" Idioma no encontrado");
            return;
        }
        if (!librosEncontrado.isEmpty()){
            librosEncontrado.forEach(System.out::println);
        }else {
            System.out.println("No se encontraron libros en este idioma");
        }


    }
}
