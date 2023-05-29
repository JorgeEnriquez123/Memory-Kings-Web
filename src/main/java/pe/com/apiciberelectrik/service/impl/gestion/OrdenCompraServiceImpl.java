package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.OrdenCompraEntity;
import pe.com.apiciberelectrik.entity.gestion.ProveedorEntity;
import pe.com.apiciberelectrik.repository.gestion.OrdenCompraRepository;
import pe.com.apiciberelectrik.service.gestion.OrdenCompraService;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Override
    public List<OrdenCompraEntity> findAll() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public List<OrdenCompraEntity> findAllCustom() {
        return ordenCompraRepository.findAllCustom();
    }

    @Override
    public OrdenCompraEntity add(OrdenCompraEntity ordenCompraEntity) {
        return ordenCompraRepository.save(ordenCompraEntity);
    }

    @Override
    public Optional<OrdenCompraEntity> findById(Long id) {
        return ordenCompraRepository.findById(id);
    }

    @Override
    public OrdenCompraEntity update(OrdenCompraEntity ordenCompraEntity) {
        OrdenCompraEntity objCompra = ordenCompraRepository.getById(ordenCompraEntity.getCodigo());
        BeanUtils.copyProperties(ordenCompraEntity,objCompra);
        return ordenCompraRepository.save(objCompra);
    }

    @Override
    public OrdenCompraEntity delete(OrdenCompraEntity ordenCompraEntity) {
        OrdenCompraEntity objCompra = ordenCompraRepository.getById(ordenCompraEntity.getCodigo());
        objCompra.setEstado(false);
        return ordenCompraRepository.save(objCompra);
    }

    @Override
    public OrdenCompraEntity enabled(OrdenCompraEntity ordenCompraEntity) {
        OrdenCompraEntity objCompra = ordenCompraRepository.getById(ordenCompraEntity.getCodigo());
        objCompra.setEstado(true);
        return ordenCompraRepository.save(objCompra);
    }
}
