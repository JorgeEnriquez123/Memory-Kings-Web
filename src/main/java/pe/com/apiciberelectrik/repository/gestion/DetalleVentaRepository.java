package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.DetalleVentaEntity;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Long> {
    List<DetalleVentaEntity> findByVentaCodigo (Long id);
}
