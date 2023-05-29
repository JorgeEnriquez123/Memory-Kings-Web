package pe.com.apiciberelectrik.service.impl.gestion;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.ProductoEntity;
import pe.com.apiciberelectrik.repository.gestion.ProductoRepository;
import pe.com.apiciberelectrik.service.gestion.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoRepository repositorio;

    @Override
    public List<ProductoEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<ProductoEntity> findAllCustom() {
        return repositorio.findAllCustom();
    }

    @Override
    public ProductoEntity add(ProductoEntity t) {
        return repositorio.save(t);
    }

    @Override
    public Optional<ProductoEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public ProductoEntity update(ProductoEntity t) {
        ProductoEntity objProducto = repositorio.getById(t.getCodigo());
        BeanUtils.copyProperties(t,objProducto);
        return repositorio.save(objProducto);
    }

    @Override
    public ProductoEntity delete(ProductoEntity t) {
        ProductoEntity objProducto = repositorio.getById(t.getCodigo());
        objProducto.setEstado(false);
        return repositorio.save(objProducto);
    }

    @Override
    public ProductoEntity enabled(ProductoEntity t) {
        ProductoEntity objProducto = repositorio.getById(t.getCodigo());
        objProducto.setEstado(true);
        return repositorio.save(objProducto);
    }

}
