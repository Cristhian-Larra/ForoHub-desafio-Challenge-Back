<h1 align="center"> ForoHub - Challenge Back </h1>

![image](https://github.com/Cristhian-Larra/ForoHub-desafio-Challenge-Back/assets/141253906/8f7e1059-dce7-49a2-80a8-de0090c79837)

![Badge en Desarollo](https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green)
![License](https://img.shields.io/badge/license-MIT-blue)


## Índice

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Estado del Proyecto](#estado-del-proyecto)
3. [Funcionalidades](#funcionalidades)
4. [Demostración de Funciones y Aplicaciones](#demostración-de-funciones-y-aplicaciones)
5. [Acceso al Proyecto](#acceso-al-proyecto)
6. [Tecnologías Utilizadas](#tecnologías-utilizadas)
7. [Cómo Usar el Proyecto](#cómo-usar-el-proyecto)
8. [Ayuda](#ayuda)
9. [Personas Contribuyentes](#personas-contribuyentes)
10. [Personas Desarrolladoras del Proyecto](#personas-desarrolladoras-del-proyecto)
11. [Licencia](#licencia)

## Descripción del Proyecto

ForoHub es una API RESTful desarrollada con Java y Spring Boot para gestionar cursos, tópicos, respuestas y usuarios. Proporciona un sistema completo de autenticación y autorización utilizando tokens JWT.

## Estado del Proyecto

El proyecto está en su fase inicial de desarrollo, pero ya cuenta con funcionalidades básicas implementadas.

## Funcionalidades

- Registro, actualización y eliminación de cursos
- Registro, actualización y eliminación de tópicos
- Registro, actualización y eliminación de respuestas
- Registro, actualización y eliminación de usuarios
- Autenticación de usuarios mediante JWT
- Paginación y búsqueda de cursos, tópicos, respuestas y usuarios

## Demostración de Funciones y Aplicaciones

### Endpoints

#### Cursos
- `POST /cursos`: Registra un curso en la base de datos
- `GET /cursos`: Obtiene el listado de cursos
- `GET /cursos/{id}`: Obtener un curso por ID
- `PUT /cursos`: Actualiza un curso en la base de datos
- `DELETE /cursos/{id}`: Elimina un curso de la base de datos

#### Respuestas
- `POST /respuestas`: Registra una respuesta en la base de datos
- `GET /respuestas`: Obtiene el listado de respuestas
- `GET /respuestas/{id}`: Obtener una respuesta por ID
- `PUT /respuestas`: Actualiza una respuesta en la base de datos
- `DELETE /respuestas/{id}`: Elimina una respuesta de la base de datos

#### Tópicos
- `POST /topicos`: Registra un tópico en la base de datos
- `GET /topicos`: Obtiene el listado de tópicos
- `GET /topicos/{id}`: Obtener un tópico por ID
- `PUT /topicos`: Actualiza un tópico en la base de datos
- `DELETE /topicos/{id}`: Elimina un tópico de la base de datos

#### Usuarios
- `POST /usuarios`: Registra un usuario en la base de datos
- `POST /usuarios/login`: Autentica un usuario
- `GET /usuarios`: Obtiene el listado de usuarios
- `GET /usuarios/{id}`: Obtiene un usuario por ID
- `PUT /usuarios`: Actualiza un usuario en la base de datos
- `DELETE /usuarios/{id}`: Elimina un usuario de la base de datos

## Demostración
A continuación, se presentan ejemplos de cómo interactuar con la API:

- **Registrar Topicos**:
    ```sh
    POST /Topicos
    {
    "id_usuario": "1",
    "titulo": "Titulo del Topico",
    "nombre_curso": "Nombre del Curso",
    "mensaje": "mensaje"
  }
    ```

- **Listar Topicos**:
    ```sh
    GET /Topicos
    ```

- **Actualizar Topicos**:
    ```sh
    PUT /Topicos
    {
      "id": 1,
      "titulo": "Nuevo título",
      "mensaje": "Nuevo mensaje",
      "respuestas": [
          {
              "idRespuesta": 1,
              "correcta": true
          }    
        ]
    }
    ```

- **Eliminar Topicos**:
    ```sh
    DELETE /Topicos/1
    ```

## Acceso al Proyecto

1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu_usuario/ForoHub-desafio-Challenge-Back.git
    cd ForoHub-desafio-Challenge-Back
    ```

2. Configura tu base de datos en el archivo `application.properties`.



## Tecnologías Utilizadas

- Java (versión 17 en adelante)
- Hibernate
- Maven
- Spring Boot


Dependencias que usa el proyecto
- Lombok
- Spring Web
- Spring Boot DevTools
- Spring Data JPA
- Flyway Migration
- MySQL Driver
- Validation


## Ayuda
Para obtener ayuda, puedes abrir un issue en el [repositorio del proyecto](https://github.com/Cristhian-Larra/ForoHub-desafio-Challenge-Back/issues).

## Autores
- **Cristhian Larrahondo** - [GitHub](https://github.com/Cristhian-Larra)
    
## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

