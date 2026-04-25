package cl.duoc.pagos.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import cl.duoc.pagos.client.FiestaClient;
import cl.duoc.pagos.dto.request.DtoPagosRequest;
import cl.duoc.pagos.dto.response.DtoFiestaResponse;
import cl.duoc.pagos.dto.response.DtoPagosResponse;
import cl.duoc.pagos.model.PagosModel;
import cl.duoc.pagos.repository.PagosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagosService {

    private final PagosRepository pagosrepository;
    private final FiestaClient FiestaClient;

    private DtoPagosResponse mapToDtoPagosResponse(PagosModel pagosmodel) {
        DtoPagosResponse response = new DtoPagosResponse();
        response.setIdPagos(pagosmodel.getIdPagos());
        response.setFechaPago(pagosmodel.getFechaPago());
        response.setMontoPagado(pagosmodel.getMontoPagado());
        response.setEstadoPago(pagosmodel.getEstadoPago());
        return response;
    }

    public DtoPagosResponse guardarPagos(DtoPagosRequest request){

        DtoFiestaResponse fiestaresponse = FiestaClient.obtenerFiestaPorId(request.getIdFiesta());
        
        PagosModel pagosmodel = new PagosModel();

        pagosmodel.setEstadoPago(request.getEstadoPago());
        pagosmodel.setFechaPago(LocalDate.now());
        pagosmodel.setIdFiesta(request.getIdFiesta());
        pagosmodel.setMontoPagado(request.getMontoPagado());
        //pagosmodel.setNombreFiesta("Pendiente hasta crear el webclient");
        pagosmodel.setNombreFiesta(fiestaresponse.getNombre());

        PagosModel pagoguardado = pagosrepository.save(pagosmodel);
        return mapToDtoPagosResponse(pagoguardado);
    }
}
