package com.literalura.catalogo.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
