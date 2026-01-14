package com.literalura.catalogo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  nombre;
    private Integer añoDeNacimiento;
    private Integer añoDeFallecimiento;
    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libro;

    public Autores() {}

    public Autores(DatosAutor autores) {
        this.nombre = autores.nombre();
        this.añoDeNacimiento = autores.añoDeNacimiento();
        this.añoDeFallecimiento = autores.añoDeFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(Integer añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public Integer getAñoDeFallecimiento() {
        return añoDeFallecimiento;
    }

    public void setAñoDeFallecimiento(Integer añoDeFallecimiento) {
        this.añoDeFallecimiento = añoDeFallecimiento;
    }

    public List<Libros> getLibro() {
        return libro;
    }

    public void setLibro(List<Libros> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return
                "Autor: " +
                "\nNombre: " + nombre +
                "\nañoDeNacimiento: " + añoDeNacimiento +
                "\nañoDeFallecimiento: " + añoDeFallecimiento
                ;
    }
}
