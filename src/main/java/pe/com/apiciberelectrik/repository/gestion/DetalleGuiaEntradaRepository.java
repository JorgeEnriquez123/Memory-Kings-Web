package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.DetalleGuiaEntradaEntity;
import pe.com.apiciberelectrik.entity.gestion.DetalleOrdenCompraEntity;

import java.util.List;

@Repository
public interface DetalleGuiaEntradaRepository extends JpaRepository<DetalleGuiaEntradaEntity, Long> {
    List<DetalleGuiaEntradaEntity> findByGuiaentradaCodigo (Long id);
}
