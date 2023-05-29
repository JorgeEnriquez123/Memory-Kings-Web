package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.VentaEntity;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Long> {
    @Query("select v from VentaEntity v where v.estado=1")
    List<VentaEntity> findAllCustom();
}
