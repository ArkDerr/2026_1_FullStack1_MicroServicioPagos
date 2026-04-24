package cl.duoc.pagos.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPagosResponse {
    private Long idPagos;
    private LocalDate fechaPago;
    private Integer montoPagado;
    private String estadoPago;
}
