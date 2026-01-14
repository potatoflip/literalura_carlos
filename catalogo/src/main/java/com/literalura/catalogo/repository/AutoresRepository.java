package com.literalura.catalogo.repository;

import com.literalura.catalogo.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Long> {

@Query("SELECT a FROM Autores a WHERE a.nombre ILIKE %:nombre%")
Optional<Autores> nombreAutores(String nombre);
@Query("SELECT a FROM Autores a WHERE a.añoDeNacimiento <= :año AND (a.añoDeFallecimiento IS NULL OR a.añoDeFallecimiento  >= :año)")
List<Autores> añoBuscado(Integer año);
}
