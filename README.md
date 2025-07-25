# 🧠 ForoHub – API REST de Tópicos

Bienvenido a **ForoHub**, una API REST desarrollada en Java con Spring Boot que permite crear, consultar y gestionar tópicos dentro de un foro académico.  
Esta versión inicial incluye autenticación de usuarios con JWT y operaciones CRUD sobre tópicos.

---

## 🚀 Tecnologías Utilizadas

- Java 17 : versión 17 en adelante - Descarga la última versión LTS de Java gratuita 
- Spring Boot 3 - https://start.spring.io/
- Spring Data JPA  
- Spring Security (JWT)  
- MySQL : versión 8 en adelante *- https://dev.mysql.com/downloads/installer/ (Instalador para Windows) 
- Maven : versión 4 en adelante  
- IDE (Entorno de desarrollo integrado) IntelliJ IDEA - opcional - https://www.jetbrains.com/es-es/idea/download/?section=windows
---
## 🚀 Configuración al crear el proyecto con Spring Initializr - https://start.spring.io/

- Java (versión 17 en adelante)
- Maven (Initializr utiliza la versión 4)
- Spring Boot
- Proyecto en formato JAR
---
## 🚀 Dependencias para agregar al crear el proyecto con Spring Initializr - https://start.spring.io/:

- Spring Web 
- Spring Boot DevTools
- Spring Data JPA
- Flyway Migration
- MySQL Driver
- Validation
- Spring Security
---
## 📥 Descarga o Clonación del Proyecto
Puedes clonar este repositorio con:

- bash
  git clone [https://github.com/mapulgarin2/forohub](https://github.com/mapulgarin2/Challenger-Foro-Hub)
---
## 💡 Descripción del Proyecto

Esta aplicación permite a un usuario autenticado:

- Iniciar sesión mediante email y contraseña
- Crear nuevos tópicos de discusión
- Listar todos los tópicos
- Consultar un tópico por ID
- Actualizar la información de un tópico
- Eliminar un tópico

Todo esto desde endpoints REST con protección JWT.

---
## Funcionalidades

### 1. Registro de Tópicos

- **Endpoint:** `POST /topicos`
- **Descripción:** Permite registrar un nuevo tópico enviando los datos necesarios como título, mensaje, autor y curso.

### 2. Listado de Tópicos

- **Endpoint:** `GET /topicos`
- **Descripción:** Lista todos los tópicos registrados.

### 3. Detalle de un Tópico

- **Endpoint:** `GET /topicos/{id}`
- **Descripción:** Muestra los detalles de un tópico específico por su ID.
- **Campos retornados:** Título, mensaje, fecha de creación, estado, autor, curso.

### 4. Actualización de un Tópico

- **Endpoint:** `PUT /topicos/{id}`
- **Descripción:** Permite actualizar un tópico existente.
- **Validación:** Se verifica que el tópico exista antes de actualizarlo.

### 5. Eliminación  de un Tópico

- **Endpoint:** `DELETE /topicos/{id}`
- **Descripción:** Realiza la eleiminacion de un tópico.

### 6. Autenticación de Usuario

- **Endpoint:** `POST /login`
- **Descripción:** Permite autenticarse con email y contraseña.
- **Respuesta:** Retorna un token JWT si las credenciales son válidas.
---
## 📋 Requisitos Implementados

1. **Login de usuario con JWT**
   - Endpoint `/login`
   - Devuelve token JWT
   - Token usado en todas las rutas protegidas

2. **Gestión de tópicos**
   - Crear, consultar, actualizar y eliminar un tópico,lista de tópicos creados.
---
## Seguridad con JWT

La API está protegida con JWT. Se requiere un token válido para acceder a la mayoría de los endpoints (excepto el de login).

- **Generación del token:** se realiza tras el login.
- **Validación del token:** se maneja mediante un filtro de seguridad (`SecurityFilter`) y un servicio (`TokenService`).
- **Configuración:** la clave secreta y expiración se definen en `application.properties`.

## Variables de configuración

En `application.properties` debes incluir:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro
spring.datasource.username=root
spring.datasource.password=tu_password

jwt.secret=tu_clave_secreta
jwt.expiration=3600000
```
---

## 📂 Estructura del Proyecto

### Entidades

- **Usuario**  
  - Atributos: `id`, `nombre`, `email`, `contrasena`  
  - Implementa `UserDetails` para autenticación

- **Topico**  
  - Atributos: `id`, `titulo`, `mensaje`, `fechaCreacion`, `status`, `autor`, `curso`  
  - Relación: `@ManyToOne` con `Usuario` y `Curso`

### Seguridad

- `TokenService`  
  - Generación y validación del JWT

- `SecurityFilter`  
  - Filtra peticiones protegidas y extrae el token del header

- `SecurityConfiguration`  
  - Configura rutas públicas y protegidas

---

## 📚 Endpoints principales

| Método | Endpoint       | Descripción                   |
|--------|----------------|-------------------------------|
| POST   | /login         | Login y obtención del JWT     |
| POST   | /topicos       | Crear un nuevo tópico         |
| GET    | /topicos       | Listar todos los tópicos      |
| GET    | /topicos/{id}  | Consultar un tópico por ID    |
| PUT    | /topicos       | Actualizar un tópico          |
| DELETE | /topicos/{id}  | Eliminar (inactivar) un tópico|

> Todos los endpoints (excepto /login) requieren un header Authorization con un token JWT válido.

---

## 📁 Configuración del Proyecto

- Base de datos: MySQL  
- Migraciones: Flyway (`src/main/resources/db/migration`)  
- Archivo de configuración: `application.properties`

---

## 🧪 Ejemplo de autenticación

### Request

```http
POST /login
Content-Type: application/json

{
  "login": "usuario@email.com",
  "clave": "123456"
}
```

### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```



---

## ⚖️ Licencia

Este proyecto fue desarrollado con fines educativos como parte de un desafío de aprendizaje con Java y Spring Boot.

---

## 🧑‍💻 Autor

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/mapulgarin2">
        <img src="https://avatars.githubusercontent.com/u/111947748?v=4" width="100px;" alt="Mauricio Pulgarin"/><br />
        <sub><b>Mauricio Pulgarin</b></sub>
      </a>
    </td>
      <td align="center">
      <a href="https://www.aluracursos.com/">
        <img src="https://www.aluracursos.com/assets/img/home/alura-logo.1730889068.svg" width="100px;" alt="Alura Latam"/><br />
        <sub><b>Alura Latam</b></sub>
      </a>
    </td>
  </tr>
</table>

---

## 🙏 Agradecimientos

- A mi familia por apoyarme en cada reto que me propongo. 
- A mi compromiso con el aprendizaje continuo.  
- A los tutores del curso por su guía.  
- A la comunidad de Java por compartir conocimiento.

---

✨ ¡Gracias por visitar el proyecto ForoHub! ✨
