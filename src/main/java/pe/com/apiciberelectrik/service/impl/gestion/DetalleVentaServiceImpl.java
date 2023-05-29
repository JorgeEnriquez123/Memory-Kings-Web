package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.DetalleVentaEntity;
import pe.com.apiciberelectrik.repository.gestion.DetalleVentaRepository;
import pe.com.apiciberelectrik.service.gestion.DetalleVentaService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVentaEntity> findAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public List<DetalleVentaEntity> findAllCustom() {
        return null;
    }

    @Override
    public DetalleVentaEntity add(DetalleVentaEntity detalleVentaEntity) {
        return detalleVentaRepository.save(detalleVentaEntity);
    }

    @Override
    public Optional<DetalleVentaEntity> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVentaEntity update(DetalleVentaEntity detalleVentaEntity) {
        return null;
    }

    @Override
    public DetalleVentaEntity delete(DetalleVentaEntity detalleVentaEntity) {
        return null;
    }

    @Override
    public DetalleVentaEntity enabled(DetalleVentaEntity detalleVentaEntity) {
        return null;
    }
}
