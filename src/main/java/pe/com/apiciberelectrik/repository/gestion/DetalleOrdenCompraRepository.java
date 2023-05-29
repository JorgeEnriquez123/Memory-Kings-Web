package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.DetalleOrdenCompraEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompraEntity, Long> {
    List<DetalleOrdenCompraEntity> findByOrdencompraCodigo (Long id);
}
