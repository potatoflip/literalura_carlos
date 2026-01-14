package com.literalura.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros (
        @JsonAlias("id") Integer idLibro,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("download_count") Integer numeroDescargas,
        @JsonAlias("languages") List<String> idiomas
) {

}
