package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.ProductoEntity;
import pe.com.apiciberelectrik.entity.gestion.ProductoProveedorEntity;
import pe.com.apiciberelectrik.entity.gestion.ProductoProveedorPK;

import java.util.List;

@Repository
public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedorEntity, ProductoProveedorPK> {
    @Query("select prodprov from ProductoProveedorEntity prodprov where prodprov.estado=1")
    List<ProductoProveedorEntity> findAllCustom();

    List<ProductoProveedorEntity> findByProveedorCodigo(long id);
}
