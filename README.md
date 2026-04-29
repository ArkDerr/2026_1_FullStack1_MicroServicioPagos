# MicroServicio Pagos

Microservicio desarrollado con Spring Boot para la gestión de pagos asociados a fiestas, dentro de una arquitectura de microservicios.

Este servicio valida, registra y persiste pagos, además de consumir un microservicio externo de fiestas para validar información.

---

## Tecnologías

- Java 21
- Spring Boot
- Spring Web (REST)
- Spring Data JPA
- Jakarta Validation
- MySQL
- Lombok
- WebClient (consumo de APIs externas)
- Maven Wrapper

---

## Arquitectura

El proyecto sigue una arquitectura en capas:

```bash
src/main/java/cl/duoc/pagos
├── client        # Consumo de microservicio de fiestas
├── config        # Configuración de WebClient
├── controller    # API REST
├── dto           # Request / Response
├── model         # Entidades JPA
├── repository    # Acceso a datos
├── service       # Lógica de negocio
└── PagosApplication.java
```

---

## Flujo de funcionamiento

1. El cliente realiza una solicitud HTTP (`POST /api/v1/pagos`)
2. El controller recibe el request
3. Se valida con Jakarta Validation
4. El service:
   - Consulta el microservicio de fiestas (WebClient)
   - Valida que la fiesta exista
   - Construye el modelo
   - Guarda en base de datos
5. Se retorna un DTO de respuesta

---

## Endpoints

### Crear pago

```http
POST /api/v1/pagos
```

### Ejemplo request

```json
{
  "fechaPago": "20-04-2026",
  "montoPagado": 1500000,
  "estadoPago": "PAGADO",
  "idFiesta": 1
}
```

### Ejemplo response

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

## Integración externa

El servicio consume un microservicio de fiestas mediante:

- `FiestaClient`
- `WebClientConfigFiesta`

Se utiliza WebClient para:

- Obtener datos de la fiesta
- Validar existencia antes de registrar el pago

---

## DTOs

### Request

- `DtoPagosRequest`

Validaciones utilizadas:

- `@NotNull`
- `@FutureOrPresent`
- `@Positive`
- `@NotBlank`

### Response

- `DtoPagosResponse`
- `DtoFiestaResponse`

---

## Base de datos

Motor: **MySQL**

```sql
CREATE DATABASE bd_pagos;
```

---

## Configuración

Archivo base:

```properties
spring.profiles.active=dev
```

Archivo `application-dev.properties`:

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

## Ejecución

### macOS / Linux

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

---

## Build

```bash
./mvnw clean package
```

### Ejecutar JAR

```bash
java -jar target/*.jar
```

---

## Buenas prácticas implementadas

- Arquitectura en capas
- Uso de DTOs
- Validaciones con Jakarta
- Separación de responsabilidades
- Consumo desacoplado de APIs (WebClient)
- Configuración por perfiles
- Uso de Lombok

---

## Mejoras futuras

- Manejo global de excepciones (`@RestControllerAdvice`)
- Documentación con Swagger/OpenAPI
- Pruebas unitarias (JUnit + Mockito)
- Dockerización
- Logs estructurados
- Retry / timeout en WebClient

---

## Autor

Desarrollado por **Daniel Riquelme**
