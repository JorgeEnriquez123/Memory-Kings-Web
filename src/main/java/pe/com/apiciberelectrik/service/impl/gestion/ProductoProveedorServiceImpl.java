package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.OrdenCompraEntity;
import pe.com.apiciberelectrik.entity.gestion.ProductoEntity;
import pe.com.apiciberelectrik.entity.gestion.ProductoProveedorEntity;
import pe.com.apiciberelectrik.entity.gestion.ProductoProveedorPK;
import pe.com.apiciberelectrik.repository.gestion.ProductoProveedorRepository;
import pe.com.apiciberelectrik.service.gestion.ProductoProveedorService;
import pe.com.apiciberelectrik.service.gestion.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoProveedorServiceImpl implements ProductoProveedorService {
    @Autowired
    ProductoProveedorRepository productoProveedorRepository;

    @Override
    public List<ProductoProveedorEntity> findAll() {
        return productoProveedorRepository.findAll();
    }

    @Override
    public List<ProductoProveedorEntity> findAllCustom() {
        return productoProveedorRepository.findAllCustom();
    }

    @Override
    public ProductoProveedorEntity add(ProductoProveedorEntity productoProveedorEntity) {
        return productoProveedorRepository.save(productoProveedorEntity);
    }

    @Override
    public Optional<ProductoProveedorEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductoProveedorEntity update(ProductoProveedorEntity productoProveedorEntity) {
        return null;
    }

    public Optional<ProductoProveedorEntity> findById2(Long idproducto, Long idproveedor) {
        return productoProveedorRepository.findById(new ProductoProveedorPK(idproducto, idproveedor));
    }

    public ProductoProveedorEntity update2(Long idproducto, Long idproveedor, ProductoProveedorEntity productoProveedorEntity) {
        Optional<ProductoProveedorEntity> objpvbuscar = productoProveedorRepository.findById(new ProductoProveedorPK(idproducto, idproveedor));
        ProductoProveedorEntity objpvencontrado = objpvbuscar.get();
        BeanUtils.copyProperties(productoProveedorEntity,objpvencontrado);
        return productoProveedorRepository.save(objpvencontrado);
    }

    @Override
    public ProductoProveedorEntity delete(ProductoProveedorEntity productoProveedorEntity) {
        ProductoProveedorEntity objpv = productoProveedorEntity;
        objpv.setEstado(false);
        return productoProveedorRepository.save(objpv);
    }

    @Override
    public ProductoProveedorEntity enabled(ProductoProveedorEntity productoProveedorEntity) {
        ProductoProveedorEntity objpv = productoProveedorEntity;
        objpv.setEstado(true);
        return productoProveedorRepository.save(objpv);
    }
}
