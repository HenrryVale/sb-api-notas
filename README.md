# Spring Boot API Notas

![](https://img.shields.io/badge/build-success-brightgreen.svg)
# Desplegado actualmente
http://143.244.187.44:8080/swagger-ui.html

# Stack

![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/h2-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)



# Estructura de archivos

```
sb-api-notas/
 │
 ├── src/main/java/
 │   └── appnotas
 │       ├── configuration
 │       │   └── SwaggerConfig.java
 │       │
 │       ├── controller
 │       │   └── UserController.java
 │       │
 │       ├── dto
 │       │   ├── NotaDTO.java
 │       │   └── NotaResponseDTO.java
 │       │   └── UsuarioDataDTO.java
 │       │   └── UsuarioResponseDTO.java
 │       │   └── ResponseDTO.java
 │       │
 │       ├── exception
 │       │   ├── CustomException.java
 │       │   └── GlobalExceptionController.java
 │       │
 │       ├── model
 │       │   ├── UsuarioRol.java
 │       │   └── Usuario.java
 │       │   └── Nota.java
 │       │
 │       ├── repository
 │       │   └── UsuarioRepository.java
 │       │   └── NotaRepository.java
 │       │
 │       ├── security
 │       │   ├── JwtTokenFilter.java
 │       │   ├── JwtTokenFilterConfigurer.java
 │       │   ├── JwtTokenProvider.java
 │       │   ├── MyUserDetails.java
 │       │   └── WebSecurityConfig.java
 │       │
 │       ├── service
 │       │   └── UsuarioService.java
 │       │   └── NotaService.java
 │       │
 │       └── JwtAuthServiceApp.java
 │
 ├── src/main/resources/
 │   └── application.yml
 │
 ├── .gitignore
 ├── mvnw/mvnw.cmd
 ├── README.md
 └── pom.xml
```
# Modelo mental  (gráfico de modelo de base de datos y justificación de relaciones)
La relación es de uno a muchos, ya que un usuario puede tener muchas notas.
![Modelo de bd](modelobd.png)

# Patrones de diseño 
* Builder: Se realizo en la clase "ResponseDTO.java". Se aplico a esta clase ya que hay campos que no eran necesarios llenar.
* Dependency Injection: Se realizo en la clase "NotaService.java". Para generar automáticamente un constructor que inyecta la dependencia "NotaRepository".

# Casos de uso adicionales
*   Casos de uso para la entidad Usuario:

        Crear usuario: Un usuario puede registrarse en el sistema proporcionando sus datos personales, como nombre, dirección de correo electrónico y contraseña.
        Iniciar sesión: Un usuario puede iniciar sesión en el sistema ingresando su nombre de usuario  y contraseña.
  
* Casos de uso para la entidad Nota:

      Crear nota: Un usuario autenticado puede crear una nueva nota proporcionando un título y el contenido de la nota.
      Ver lista de notas: Un usuario autenticado puede ver la lista de todas sus notas.
#   Request y response serializadas utilizando JSON
* POST: `http://localhost:8080/users/signup`
  request:
  ``` json 
  {
  "username": "USUARIO1",
  "email": "USUARIO1@GMAIL.COM",
  "password": "USUARIO1",
  "usuarioRols": [
    "ROLE_USUARIO"]
  }
  ```
  response:
  ``` json 
  {
    "message": "Ingreso correcto!",
    "data": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVU1VBUklPMSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNVQVJJTyJ9XSwiaWF0IjoxNjg3MjkyMTQ5LCJleHAiOjE2ODcyOTI0NDl9.BH0cSKXFCHM0Q8AMCxfRF8O21rpP2LJs3ym0Cgl-dfE"
  }
  ```
  ![](signup.png)

* POST: `http://localhost:8080/users/nota`
  request:
  ``` json 
  {
  "titulo": "MI TITULO",
  "contenido": "PRIMER CONTENIDO"
  }
  ```
  response:
  ``` json 
  {
    "message": "Registrado correctamente!",
    "data": {
        "id": 2,
        "titulo": "MI TITULO",
        "contenido": "PRIMER CONTENIDO",
        "usuarioId": 2
    }
  }
  ```
  ![](registraNota.png)
* GET: `http://localhost:8080/users/notas`
  
  response:
  ``` json 
  [
    {
        "id": 1,
        "titulo": "string",
        "contenido": "string",
        "usuarioId": 2
    },
    {
        "id": 2,
        "titulo": "MI TITULO",
        "contenido": "PRIMER CONTENIDO",
        "usuarioId": 2
    }
  ]
  ```
  ![](listarMisNotas.png)



# AUTORIZACION 
Se asocia una nota con un usuario, de tal manera que cada usuario solo pueda ver sus propias notas

# SWAGGER
Se documento todos los endpoints utilizando swagger.

# DESPLIEGUE UTILIZANDO DOCKER
Navegue a `https://hub.docker.com/r/henrryvalen/api-notas` para descargar la imagen.

1. Asegúrate de tener Docker instalado y en funcionamiento en tu sistema.
2. Abre una terminal o línea de comandos y ejecuta el siguiente comando:

```cmd
docker pull henrryvalen/api-notas:1.0.0
```
3. Una vez que la imagen se haya descargado, puedes ejecutar un contenedor basado en esa imagen utilizando el comando docker run:

```cmd
docker run -p 8080:8080 henrryvalen/api-notas:1.0.0
```
4.Ahora, tu contenedor basado en la imagen de Docker Hub se está ejecutando localmente. `http://localhost:8080/`
# ¿Cómo usar el código?

1. Asegúrese de tener [Java 8](https://www.java.com/download/) y [Maven](https://maven.apache.org) instalados

2. Bifurca este repositorio y clonalo
  
```
$ git clone https://github.com/henrryvalen/sb-api-notas
```

3. Navegar a la carpeta

```
$ cd sb-api-notas
```

4. Instalar dependencias

```
$ mvn install
```

5. Ejecutar el proyecto

```
$ mvn spring-boot:run
```

6. Navegue a `http://localhost:8080/swagger-ui.html` en su navegador para comprobar que todo funciona correctamente. Puede cambiar el puerto predeterminado en el archivo `application.yml`
```yml
server:
  port: 8080
```

7. Realice una solicitud GET a `http://localhost:8080/users/notas` para comprobar que no está autenticado. Debería recibir una respuesta con un `403` con un mensaje de `Acceso denegado` ya que aún no ha configurado su token JWT válido
```
$ curl -X GET http://localhost:8080/users/notas
```

8. Realice una solicitud POST a `http://localhost:8080/users/signup` para crear un usuario y obtener un token JWT válido

```json
   {
   "username": "usuario1",
   "email": "usuario1@gmail.com",
   "password": "usuario1",
   "usuarioRols": ["ROLE_USUARIO"]
   }
 ```
o
```
$ curl -X POST "http://localhost:8080/users/signup" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"username\": \"usuario1\", \"email\": \"usuario1@gmail.com\", \"password\": \"usuario1\", \"usuarioRols\": [\"ROLE_USUARIO\"] }"
```

9. Agregue el token JWT como parámetro de encabezado y realice la solicitud GET inicial a `http://localhost:8080/users/notas` nuevamente
```
$ curl -X GET http://localhost:8080/users/notas -H 'Authorization: Bearer <JWT_TOKEN>'
```


10. Ahora para el caso de registrar notas realice la solicitud POST inicial a `http://localhost:8080/users/nota`
``` json
{
  "titulo": "TITULO 1",
  "contenido": "MI CONTENIDO"
}
```
o
```
curl -X POST "http://localhost:8080/users/nota" -H "accept: */*" -H "Authorization: Bearer <JWT_TOKEN>" -H "Content-Type: application/json" -d "{ \"titulo\": \"TITULO 1\", \"contenido\": \"MI CONTENIDO\"}"```
```

