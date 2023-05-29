package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.ProductoEntity;
import pe.com.apiciberelectrik.entity.gestion.ProveedorEntity;
import pe.com.apiciberelectrik.repository.gestion.ProveedorRepository;
import pe.com.apiciberelectrik.service.gestion.ProveedorService;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorEntity> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public List<ProveedorEntity> findAllCustom() {
        return proveedorRepository.findAllCustom();
    }

    @Override
    public ProveedorEntity add(ProveedorEntity proveedorEntity) {
        return proveedorRepository.save(proveedorEntity);
    }

    @Override
    public Optional<ProveedorEntity> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public ProveedorEntity update(ProveedorEntity proveedorEntity) {
        ProveedorEntity objProveedor = proveedorRepository.getById(proveedorEntity.getCodigo());
        BeanUtils.copyProperties(proveedorEntity,objProveedor);
        return proveedorRepository.save(objProveedor);
    }

    @Override
    public ProveedorEntity delete(ProveedorEntity proveedorEntity) {
        ProveedorEntity objProveedor = proveedorRepository.getById(proveedorEntity.getCodigo());
        objProveedor.setEstado(false);
        return proveedorRepository.save(objProveedor);
    }

    @Override
    public ProveedorEntity enabled(ProveedorEntity proveedorEntity) {
        ProveedorEntity objProveedor = proveedorRepository.getById(proveedorEntity.getCodigo());
        objProveedor.setEstado(true);
        return proveedorRepository.save(objProveedor);
    }
}
