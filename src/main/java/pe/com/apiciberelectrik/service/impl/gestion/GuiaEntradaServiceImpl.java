package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.GuiaEntradaEntity;
import pe.com.apiciberelectrik.entity.gestion.ProductoEntity;
import pe.com.apiciberelectrik.repository.gestion.GuiaEntradaRepository;
import pe.com.apiciberelectrik.repository.gestion.ProductoRepository;
import pe.com.apiciberelectrik.service.gestion.GuiaEntradaService;

import java.util.List;
import java.util.Optional;

@Service
public class GuiaEntradaServiceImpl implements GuiaEntradaService {
    @Autowired
    GuiaEntradaRepository repositorio;

    @Override
    public List<GuiaEntradaEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<GuiaEntradaEntity> findAllCustom() {
        return repositorio.findAllCustom();
    }

    @Override
    public GuiaEntradaEntity add(GuiaEntradaEntity g) {
        return repositorio.save(g);
    }

    @Override
    public Optional<GuiaEntradaEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public GuiaEntradaEntity update(GuiaEntradaEntity g) {
        GuiaEntradaEntity objGuiaEntrada = repositorio.getById(g.getCodigo());
        BeanUtils.copyProperties(g,objGuiaEntrada);
        return repositorio.save(objGuiaEntrada);
    }

    @Override
    public GuiaEntradaEntity delete(GuiaEntradaEntity g) {
        GuiaEntradaEntity objGuiaEntrada = repositorio.getById(g.getCodigo());
        objGuiaEntrada.setEstado(false);
        return repositorio.save(objGuiaEntrada);
    }

    @Override
    public GuiaEntradaEntity enabled(GuiaEntradaEntity g) {
        GuiaEntradaEntity objGuiaEntrada = repositorio.getById(g.getCodigo());
        objGuiaEntrada.setEstado(true);
        return repositorio.save(objGuiaEntrada);
    }

}
