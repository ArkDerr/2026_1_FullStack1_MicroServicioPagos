package cl.duoc.pagos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.pagos.dto.request.DtoPagosRequest;
import cl.duoc.pagos.dto.response.DtoPagosResponse;
import cl.duoc.pagos.service.PagosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagosController {

    private final PagosService pagosservice;

    @PostMapping
    public ResponseEntity<DtoPagosResponse> guardarPago(@Valid @RequestBody DtoPagosRequest request) {
        DtoPagosResponse response = pagosservice.guardarPagos(request);
        return ResponseEntity.ok(response);
    }

}
