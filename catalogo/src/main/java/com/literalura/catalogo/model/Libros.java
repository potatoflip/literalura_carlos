package com.literalura.catalogo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "libros", uniqueConstraints = {@UniqueConstraint(columnNames = {"autor_id", "titulo"})})
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer idLibro;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autores;
    private Integer numeroDescargas;
    private String idiomas;

    public Libros() {}

    public Libros(DatosLibros datosLibros) {
        this.idLibro = datosLibros.idLibro();
        this.titulo = datosLibros.titulo();
        if (datosLibros.autores() != null && !datosLibros.autores().isEmpty()) {
            this.autores = new Autores(datosLibros.autores().getFirst());
        }
        this.numeroDescargas = datosLibros.numeroDescargas();
        this.idiomas = datosLibros.idiomas().getFirst();
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getidLibro() {
        return idLibro;
    }

    public void setidLibro(Integer idLibro) {
        idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        titulo = titulo;
    }




    @Override
    public String toString() {
        return "\nLibro:" +
                "\nIdLibro: " + idLibro +
                "\nTitulo: " + titulo +
                "\nAutores: " + autores +
                "\nIdiomas: " + idiomas +
                "\nNumero de descargas: " + numeroDescargas;
    }
}
