package cl.duoc.pagos.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pagos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagosModel {

    @Id
    private Long idPagos;

    @Column(nullable = false)
    private Integer montoPagado;

    @Column(nullable = false)
    private String estadoPago;

    @Column(nullable = false)
    private Integer idFiesta;

    @Column(nullable = false)
    private String nombreFiesta;

    @Column(nullable = false)
    private LocalDate fechaPago;
}
