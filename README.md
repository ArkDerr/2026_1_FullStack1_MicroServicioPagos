# MicroServicio Pagos

Microservicio desarrollado con Spring Boot para la gestión de pagos asociados a fiestas, dentro de una arquitectura de microservicios.

Este servicio valida, registra y persiste pagos, además de consumir un microservicio externo de fiestas para validar información.

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

## Arquitectura

El proyecto sigue una arquitectura en capas:

src/main/java/cl/duoc/pagos
├── client        # Consumo de microservicio de fiestas
├── config        # Configuración de WebClient
├── controller    # API REST
├── dto           # Request / Response
├── model         # Entidades JPA
├── repository    # Acceso a datos
├── service       # Lógica de negocio
└── PagosApplication.java

## Flujo de funcionamiento

El cliente realiza una solicitud HTTP (POST /pagos)
El controller recibe el request
Se valida con Jakarta Validation
El service:
Consulta el microservicio de fiestas (WebClient)
Valida que la fiesta exista
Construye el modelo
Guarda en base de datos
Se retorna un DTO de respuesta

## Endpoints

Crear pago:
POST /api/v1/pagos
Ejemplo request
{
  "fechaPago": "20-04-2026",
  "montoPagado": 1500000,
  "estadoPago": "PAGADO",
  "idFiesta": 1
}
Ejemplo response
{
  "idPagos": 1,
  "montoPagado": 1500000,
  "estadoPago": "PAGADO",
  "nombreFiesta": "Fiesta Electrónica",
  "fechaPago": "20-04-2026"
}

## Integración externa

El servicio consume un microservicio de fiestas mediante:

FiestaClient
WebClientConfigFiesta

Se utiliza WebClient para:

Obtener datos de la fiesta
Validar existencia antes de registrar el pago

## DTOs

Request
DtoPagosRequest

Incluye validaciones:

@NotNull
@FutureOrPresent
@Positive
@NotBlank
Response
DtoPagosResponse
DtoFiestaResponse

## Base de datos

Motor: MySQL

Crear base de datos:

CREATE DATABASE bd_pagos;

## Configuración

Archivo base:

spring.profiles.active=dev
application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/bd_pagos
spring.datasource.username=usuario
spring.datasource.password=pass123

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=8082

## Ejecución

macOS / Linux
./mvnw spring-boot:run

Windows
mvnw.cmd spring-boot:run

## Build
./mvnw clean package

## Ejecutar:

java -jar target/*.jar

## Buenas prácticas implementadas

Arquitectura en capas
Uso de DTOs
Validaciones con Jakarta
Separación de responsabilidades
Consumo desacoplado de APIs (WebClient)
Configuración por perfiles
Uso de Lombok

## Mejoras Futuras

Agregar manejo global de excepciones (@RestControllerAdvice)
Documentar API con Swagger/OpenAPI
Implementar pruebas unitarias (JUnit + Mockito)
Dockerizar el servicio
Manejo de logs estructurados
Retry / timeout en WebClient