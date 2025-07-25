# 🧠 ForoHub – API REST de Tópicos

Bienvenido a **ForoHub**, una API REST desarrollada en Java con Spring Boot que permite crear, consultar y gestionar tópicos dentro de un foro académico.  
Esta versión inicial incluye autenticación de usuarios con JWT y operaciones CRUD sobre tópicos.

---

## 🚀 Tecnologías Utilizadas

- Java 17  
- Spring Boot 3  
- Spring Data JPA  
- Spring Security (JWT)  
- MySQL  
- Maven  
- Flyway  
- Lombok

---

## 💡 Descripción del Proyecto

Esta aplicación permite a un usuario autenticado:

- Iniciar sesión mediante email y contraseña
- Crear nuevos tópicos de discusión
- Listar todos los tópicos
- Consultar un tópico por ID
- Actualizar la información de un tópico
- Eliminar (inactivar) un tópico

Todo esto desde endpoints REST con protección JWT.

---

## 📋 Requisitos Implementados

1. **Login de usuario con JWT**
   - Endpoint `/login`
   - Devuelve token JWT
   - Token usado en todas las rutas protegidas

2. **Gestión de tópicos**
   - Crear, consultar, actualizar y eliminar un tópico

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

Usa este token como:

```
Authorization: Bearer TU_TOKEN_AQUI
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
  </tr>
</table>

---

## 🙏 Agradecimientos

- A mi compromiso con el aprendizaje continuo.  
- A los tutores del curso por su guía.  
- A la comunidad de Java por compartir conocimiento.

---

✨ ¡Gracias por visitar el proyecto ForoHub! ✨
