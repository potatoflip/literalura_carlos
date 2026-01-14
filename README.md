# Gestión de Libros y Autores — Spring Boot + JPA
Proyecto desarrollado en **Java con Spring Boot y Spring Data JPA**, orientado a la práctica de persistencia de datos, relaciones entre entidades y consultas derivadas/JPA Query, utilizando una aplicación de **consola**.
---
##  Descripción general
La aplicación permite gestionar un catálogo de **libros y autores**, consultando información desde una base de datos relacional.
---
## Funcionalidades disponibles
La aplicación cuenta con un menú de consola que permite:

1.  Buscar libros por autor
2.  Listar todos los libros registrados
3.  Listar todos los autores ordenados por año de nacimiento
4.  Buscar autores vivos en un año determinado
5.  Listar libros según idioma
---
## Consideraciones
Para este proyecto, cada libro tiene un solo idioma, modelado como String

No fue necesario reconstruir la base de datos al simplificar el idioma

El diseño permite futuras mejoras (enum de idiomas, relaciones adicionales, etc.)
