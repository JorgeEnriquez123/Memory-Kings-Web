package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.GuiaEntradaEntity;
import pe.com.apiciberelectrik.entity.gestion.ProveedorEntity;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
    @Query("select pv from ProveedorEntity pv where pv.estado=1")
    List<ProveedorEntity> findAllCustom();
}
