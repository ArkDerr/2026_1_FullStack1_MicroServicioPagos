package cl.duoc.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.pagos.model.PagosModel;

public interface PagosRepository extends JpaRepository<PagosModel, Long> {

}
