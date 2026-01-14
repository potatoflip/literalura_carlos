package com.literalura.catalogo.principal;

import com.literalura.catalogo.model.*;
import com.literalura.catalogo.repository.AutoresRepository;
import com.literalura.catalogo.repository.LibrosRepository;
import com.literalura.catalogo.service.ConsumoAPI;
import com.literalura.catalogo.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
@Component
public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    List<Libros> librosDatabase;
    private String menu = """
            \n
            1 - Busqueda de libro por titulo
            2 - Listado de libros registrados
            3 - Listado de autores registrados
            4 - Buscar autores segun años de vida
            5 - Listado de libros por idioma
            0 - Salir
            """;
    private LibrosRepository repositorio;
    private AutoresRepository autoresRepository;

    public Principal (LibrosRepository repositorio, AutoresRepository autoresRepository)
    {this.repositorio = repositorio;
        this.autoresRepository = autoresRepository;}

    public void menuPrincipal(){
        var opcion = -1;
        while(opcion != 0){
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch(opcion){
                case 1:
                    getLibrosPorAutor();
                    break;
                case 2 :
                    buscarTodosLosLibros();
                    break;
                case 3:
                    listaDeAutores();
                    break;
                case 4:
                    autoresPorAño();
                    break;
                case 5:
                    listadoPorAño();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }

    }


    private  void getLibrosPorAutor() {
        System.out.println("Escriba el titulo del libro que desee buscar: ");
        var nombreAutor = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreAutor.toLowerCase().replaceAll(" ", "%20"));
        ResultadosLibros datos = conversor.obtenerDatos(json, ResultadosLibros.class);
            Optional<Libros> libros = datos.resultados().stream().
                    map(Libros::new).
                    filter(l->l.getAutores() != null && !l.getAutores().getNombre().isEmpty()
                    && l.getAutores().getNombre() != null).
                    max(Comparator.comparing(Libros::getNumeroDescargas));

            if(libros.isEmpty()){
                System.out.println("\nTitulo no se encuentra registrado.");
            }else {
            Libros libroEncotrado = libros.get();
            if(repositorio.findByidLibro(libroEncotrado.getidLibro()).isPresent()){
                System.out.println("Libro registrado en la base de datos.");
                return;

            }
                String nombreAutor2 = libroEncotrado.getAutores().getNombre();
                Autores autor = autoresRepository.nombreAutores(nombreAutor2).orElseGet(()->
                { Autores nuevoAutor =libroEncotrado.getAutores();
                    return autoresRepository.save(nuevoAutor);});
                libroEncotrado.setAutores(autor);
                repositorio.save(libroEncotrado);
                System.out.println("\n"+libros);

            }}

            private void buscarTodosLosLibros() {
        librosDatabase = repositorio.findAll();
        librosDatabase.stream()
                .sorted(Comparator.comparing(Libros::getNumeroDescargas))
                .forEach(System.out::println);

            }

            private void listaDeAutores(){
         List<Autores> autoresTodos = autoresRepository.findAll();
         if (autoresTodos.isEmpty()){
             System.out.println("No se han registrado autores en la base de datos. ");
         }else {
                System.out.println("Autores registrados en la base de datos: ");
         autoresTodos.stream().sorted(Comparator.comparing(Autores::getAñoDeNacimiento,
                         Comparator.nullsLast(Comparator.naturalOrder())))
                 .forEach(System.out::println);}

    }
    private void autoresPorAño(){
        System.out.println("Ingrese el año del autor que desea buscar: ");
        String input = teclado.nextLine();
        try{
            int año = Integer.parseInt(input);
            List<Autores> añoBuscado = autoresRepository.añoBuscado(año);

            if (añoBuscado.isEmpty()){
                System.out.println("No se encontraros autores para ese año");
            }else{
                añoBuscado.stream().sorted(Comparator.comparing(Autores::getAñoDeNacimiento)).forEach(System.out::println);
            }
        }catch (NumberFormatException e){
            System.out.println("ERROR: Ingrese un número entero");
        }
    }

    private void listadoPorAño(){
        var opcionIdiomas = -1;
        while(opcionIdiomas != 0){
            System.out.println("""
                \n
                Indique el idioma que quiere consultar:
                1- Español
                2- Ingles
                3- Frances
                0- Volver al menu
                """);
            opcionIdiomas = teclado.nextInt();
            teclado.nextLine();
            switch (opcionIdiomas){
                case 1:
                    resultadoIdioma("es");
                    break;
                case 2:
                    resultadoIdioma("en");
                    break;
                case 3:
                    resultadoIdioma("fr");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Eliga una opcion correcta.");
            }
        }



}
private void resultadoIdioma(String idioma){
    List<Libros> idiomaBuscado = repositorio.findByIdiomas(idioma);
    System.out.println("Cantidad de libros en " + idioma.toUpperCase() + " " +idiomaBuscado.size());
    System.out.println("titulos en " + idioma.toUpperCase()+ ":");
    idiomaBuscado.forEach(l -> System.out.println("-" + l.getTitulo()));

}


}




