package cl.duoc.pagos.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.pagos.dto.response.DtoFiestaResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FiestaClient {

    private final WebClient webcliente;

    public DtoFiestaResponse obtenerFiestaPorId(Integer id) {
        return webcliente.get()
                .uri("/api/v1/fiestas/{id}", id)
                .retrieve()
                .bodyToMono(DtoFiestaResponse.class)
                .block();
    }

}
