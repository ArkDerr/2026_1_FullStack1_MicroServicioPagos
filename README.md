# MicroServicio Pagos - Spring Boot + WebClient + MySQL

Microservicio desarrollado con Spring Boot para la gestión de pagos asociados a fiestas, dentro de una arquitectura de microservicios.

Este servicio permite registrar, consultar y persistir pagos, validando previamente la existencia de la fiesta mediante consumo de un microservicio externo utilizando WebClient.

---

# Objetivo del Proyecto

Este microservicio permite:

- Registrar pagos asociados a fiestas
- Consultar pagos registrados
- Validar existencia de fiestas mediante WebClient
- Persistir información utilizando MySQL
- Aplicar validaciones con Jakarta Validation
- Implementar arquitectura en capas
- Manejar excepciones globales
- Retornar respuestas REST estructuradas

---

# Tecnologías Utilizadas

| Tecnología           | Descripción                       |
| -------------------- | --------------------------------- |
| Java 21              | Lenguaje principal                |
| Spring Boot          | Framework principal               |
| Spring Web           | Desarrollo de APIs REST           |
| Spring Data JPA      | Persistencia ORM                  |
| Hibernate            | ORM                               |
| MySQL                | Base de datos                     |
| WebClient            | Consumo de APIs externas          |
| Jakarta Validation   | Validaciones de DTOs              |
| Lombok               | Reducción de boilerplate          |
| Maven Wrapper        | Gestión de dependencias           |
| Spring Boot DevTools | Reinicio automático en desarrollo |

---

# Dependencias Agregadas desde start.spring.io

Al crear el proyecto en:

```text
https://start.spring.io
```

se agregaron las siguientes dependencias desde la interfaz gráfica:

- Spring Web
- Spring Data JPA
- Validation
- Lombok
- MySQL Driver
- Spring Boot DevTools

---

## Spring Web

Permite:

- Crear APIs REST
- Utilizar `@RestController`
- Utilizar `@GetMapping`, `@PostMapping`
- Serialización automática JSON
- Tomcat embebido

---

## Spring Data JPA

Permite:

- Persistencia ORM
- Uso de `JpaRepository`
- Mapear entidades con `@Entity`
- Consultas automáticas
- Integración con Hibernate

---

## Validation

Permite:

- Validar DTOs
- Utilizar `@Valid`
- Utilizar validaciones como:
  - `@NotBlank`
  - `@NotNull`
  - `@Positive`
  - `@FutureOrPresent`

---

## MySQL Driver

Permite:

- Conectarse a MySQL
- Utilizar JDBC
- Integrarse con Hibernate y Spring Data JPA

---

## Lombok

Permite:

- Reducir código repetitivo
- Generar getters y setters automáticamente
- Generar constructores automáticamente
- Simplificar entidades y DTOs

---

## Spring Boot DevTools

Permite:

- Reinicio automático
- Hot reload
- Mejor experiencia de desarrollo

---

# Dependencias Agregadas Manualmente

Las siguientes configuraciones y componentes fueron agregados manualmente al proyecto.

---

## WebClient

Se implementó WebClient para consumir el microservicio de fiestas.

Clase utilizada:

```text
WebClientConfigFiesta
```

### ¿Qué permite?

- Consumir APIs REST externas
- Validar existencia de fiestas
- Integración entre microservicios
- Comunicación HTTP desacoplada

---

# Arquitectura del Proyecto

El proyecto utiliza arquitectura en capas.

```text
src/main/java/cl/duoc/pagos
├── client            # Consumo de microservicio externo
├── config            # Configuración WebClient
├── controller        # Endpoints REST
├── dto               # DTO Request y Response
│   ├── request
│   └── response
├── exception         # Manejo global de excepciones
├── model             # Entidades JPA
├── repository        # Acceso a datos
├── service           # Lógica de negocio
└── PagosApplication.java
```

---

# Flujo de Funcionamiento

## Registrar un Pago

1. El cliente realiza una solicitud HTTP:

```http
POST /api/v1/pagos
```

2. El Controller recibe el request

3. Se validan los datos utilizando Jakarta Validation

4. El Service:

- Consulta el microservicio de fiestas
- Valida que la fiesta exista
- Construye la entidad
- Guarda la información utilizando JPA

5. Hibernate genera el SQL automáticamente

6. MySQL almacena la información

7. Se retorna un DTO Response

---

# Endpoints Principales

---

## Obtener todos los pagos

```http
GET /api/v1/pagos
```

---

## Obtener pago por ID

```http
GET /api/v1/pagos/{id}
```

---

## Registrar un pago

```http
POST /api/v1/pagos
```

### Ejemplo Request

```json
{
  "fechaPago": "20-04-2026",
  "montoPagado": 1500000,
  "estadoPago": "PAGADO",
  "idFiesta": 1
}
```

---

### Ejemplo Response

```json
{
  "idPagos": 1,
  "montoPagado": 1500000,
  "estadoPago": "PAGADO",
  "nombreFiesta": "Fiesta Electrónica",
  "fechaPago": "20-04-2026"
}
```

---

# Integración entre Microservicios

El proyecto consume un microservicio externo de fiestas utilizando:

```text
FiestaClient
```

y

```text
WebClientConfigFiesta
```

---

## ¿Qué hace esta integración?

Permite:

- Validar que la fiesta exista
- Obtener información de la fiesta
- Evitar registrar pagos inválidos
- Implementar arquitectura distribuida

---

# DTOs

## Request

### DtoPagosRequest

Utilizado para registrar pagos.

Validaciones utilizadas:

- `@NotNull`
- `@Positive`
- `@FutureOrPresent`
- `@NotBlank`

---

## Response

### DtoPagosResponse

DTO utilizado para responder información del pago.

---

### DtoFiestaResponse

DTO utilizado para recibir información desde el microservicio de fiestas.

---

# Manejo de Excepciones

El proyecto implementa manejo global de errores mediante:

```text
GlobalExceptionHandler
```

---

## Excepciones Implementadas

### ResourceNotFoundException

Utilizada cuando:

- No existe un pago
- No existe una fiesta
- No se encuentra información solicitada

---

## DtoApiError

DTO utilizado para retornar errores estructurados al cliente.

---

# Modelo de Datos

## Entidad Principal

### PagosModel

Representa un pago registrado en el sistema.

---

# Base de Datos

Motor utilizado:

```text
MySQL
```

Base de datos sugerida:

```sql
CREATE DATABASE bd_pagos;
```

---

# Configuración del Proyecto

Archivo principal:

```properties
spring.profiles.active=dev
```

---

## application-dev.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bd_pagos

spring.datasource.username=usuario
spring.datasource.password=pass123

spring.jpa.hibernate.ddl-auto=create

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=8082
```

---

# Ejecución del Proyecto

## macOS / Linux

```bash
./mvnw spring-boot:run
```

---

## Windows

```bash
mvnw.cmd spring-boot:run
```

---

# Build del Proyecto

```bash
./mvnw clean package
```

---

# Ejecutar JAR

```bash
java -jar target/*.jar
```

---

# Conceptos Implementados

- REST API
- DTO
- JPA
- Hibernate
- MySQL
- Arquitectura en capas
- Repository Pattern
- Service Layer
- Dependency Injection
- Bean Validation
- CRUD REST
- ResponseEntity
- WebClient
- Integración entre microservicios
- Manejo global de excepciones

---

# Buenas Prácticas Implementadas

- Arquitectura en capas
- Separación de responsabilidades
- Uso de DTOs
- Validaciones con Jakarta
- Manejo global de excepciones
- Configuración por perfiles
- Uso de Lombok
- Uso de ResponseEntity
- Persistencia desacoplada mediante Hibernate
- Consumo desacoplado de APIs mediante WebClient

---

# Mejoras Futuras

- Integración con Spring Security
- JWT Authentication
- Dockerización
- Integración continua con GitHub Actions
- Swagger/OpenAPI
- Retry y timeout en WebClient
- Circuit Breaker con Resilience4j
- Logs estructurados
- Pruebas unitarias con JUnit y Mockito
- Implementación de Flyway
- Paginación y ordenamiento
- Monitoreo con Spring Boot Actuator

---

# Autor

Proyecto académico desarrollado por **Daniel Riquelme**
como parte del aprendizaje de:

- Spring Boot
- Microservicios
- WebClient
- JPA
- Hibernate
- MySQL
- Arquitectura REST
- DTOs
- Validaciones con Jakarta
- Arquitectura en capas
