package cl.duoc.pagos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration //Esta clase contiene configuración del contexto (beans)
public class WebClientConfigFiesta {

    @Bean //Ejecuta este método y guarda su resultado como un objeto administrado. La deja disponible para inyección (@Autowired o constructor)
    public WebClient webClientFiesta() {
        return WebClient.builder() //Crea un builder para configurar el cliente HTTP
                .baseUrl("http://localhost:8081") //Define la URL base del microservicio de fiestas
                .defaultHeader("Content-Type", "application/json") //Agrega un header HTTP por defecto a TODAS las solicitudes
                .build(); //Construye el objeto final WebClient
    }
}
