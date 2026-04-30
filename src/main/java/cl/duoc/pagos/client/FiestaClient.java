package cl.duoc.pagos.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.pagos.dto.response.DtoFiestaResponse;
import cl.duoc.pagos.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component //Le dice a Spring: "Esta clase es un bean, créala automáticamente y adminístrala
@RequiredArgsConstructor //Lombok genera automáticamente un constructor con los atributos final
public class FiestaClient {

    private final WebClient webcliente;

    public DtoFiestaResponse obtenerFiestaPorId(Integer id) {
        return webcliente.get() //Indica que la petición HTTP es tipo GET (puede ser post, put, etc...)
                .uri("/api/v1/fiesta/{id}", id) //Construye la URL
                .retrieve() //Ejecuta la petición HTTP
                .onStatus(
                    status -> status.value() == 404,
                    reponse -> Mono.error(new ResourceNotFoundException("Id de la fiesta no existe")))
                .bodyToMono(DtoFiestaResponse.class) //Convierte el JSON de respuesta en un objeto Java
                .block(); //Convierte el flujo reactivo en bloqueante osea Espera la respuesta
    }

}
