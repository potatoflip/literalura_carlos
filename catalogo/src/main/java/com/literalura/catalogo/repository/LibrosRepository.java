package com.literalura.catalogo.repository;


import com.literalura.catalogo.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByidLibro(Integer idLibro);

    List<Libros> findByIdiomas(String idioma);

}
