package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.DetalleOrdenCompraEntity;
import pe.com.apiciberelectrik.repository.gestion.DetalleOrdenCompraRepository;
import pe.com.apiciberelectrik.service.gestion.DetalleOrdenCompraService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenCompraServiceImpl implements DetalleOrdenCompraService {
    @Autowired
    DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    @Override
    public List<DetalleOrdenCompraEntity> findAll() {
        return detalleOrdenCompraRepository.findAll();
    }

    @Override
    public List<DetalleOrdenCompraEntity> findAllCustom() {
        return null;
    }

    @Override
    public DetalleOrdenCompraEntity add(DetalleOrdenCompraEntity detalleOrdenCompraEntity) {
        return detalleOrdenCompraRepository.save(detalleOrdenCompraEntity);
    }

    @Override
    public Optional<DetalleOrdenCompraEntity> findById(Long id) {
        return detalleOrdenCompraRepository.findById(id);
    }

    @Override
    public DetalleOrdenCompraEntity update(DetalleOrdenCompraEntity detalleOrdenCompraEntity) {
        return null;
    }

    @Override
    public DetalleOrdenCompraEntity delete(DetalleOrdenCompraEntity detalleOrdenCompraEntity) {
        return null;
    }

    @Override
    public DetalleOrdenCompraEntity enabled(DetalleOrdenCompraEntity detalleOrdenCompraEntity) {
        return null;
    }
}
