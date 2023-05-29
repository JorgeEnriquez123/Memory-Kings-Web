package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.DetalleGuiaEntradaEntity;
import pe.com.apiciberelectrik.repository.gestion.DetalleGuiaEntradaRepository;
import pe.com.apiciberelectrik.service.gestion.DetalleGuiaEntradaService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleGuiaEntradaServiceImpl implements DetalleGuiaEntradaService {
    @Autowired
    DetalleGuiaEntradaRepository repositorio;

    @Override
    public List<DetalleGuiaEntradaEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<DetalleGuiaEntradaEntity> findAllCustom() {
        return null;
    }

    @Override
    public DetalleGuiaEntradaEntity add(DetalleGuiaEntradaEntity de) {
        return repositorio.save(de);
    }

    @Override
    public Optional<DetalleGuiaEntradaEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public DetalleGuiaEntradaEntity update(DetalleGuiaEntradaEntity de) {
        return null;
    }

    @Override
    public DetalleGuiaEntradaEntity delete(DetalleGuiaEntradaEntity de) {
        return null;
    }

    @Override
    public DetalleGuiaEntradaEntity enabled(DetalleGuiaEntradaEntity de) {
        return null;
    }
}
