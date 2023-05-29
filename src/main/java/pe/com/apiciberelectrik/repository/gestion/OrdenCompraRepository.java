package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.OrdenCompraEntity;
import pe.com.apiciberelectrik.entity.gestion.ProveedorEntity;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity, Long> {
    @Query("select oc from OrdenCompraEntity oc where oc.estado=1")
    List<OrdenCompraEntity> findAllCustom();
}
