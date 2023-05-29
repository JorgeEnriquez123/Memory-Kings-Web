package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.OrdenCompraEntity;
import pe.com.apiciberelectrik.entity.gestion.VentaEntity;
import pe.com.apiciberelectrik.repository.gestion.VentaRepository;
import pe.com.apiciberelectrik.service.gestion.VentaService;

import java.util.List;
import java.util.Optional;
@Service
public class VentaServiceImpl implements VentaService{
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<VentaEntity> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public List<VentaEntity> findAllCustom() {
        return ventaRepository.findAllCustom();
    }

    @Override
    public VentaEntity add(VentaEntity ventaEntity) {
        return ventaRepository.save(ventaEntity);
    }

    @Override
    public Optional<VentaEntity> findById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public VentaEntity update(VentaEntity ventaEntity) {
        VentaEntity objVenta = ventaRepository.getById(ventaEntity.getCodigo());
        BeanUtils.copyProperties(ventaEntity,objVenta);
        return ventaRepository.save(objVenta);
    }

    @Override
    public VentaEntity delete(VentaEntity ventaEntity) {
        VentaEntity objVenta = ventaRepository.getById(ventaEntity.getCodigo());
        objVenta.setEstado(false);
        return ventaRepository.save(objVenta);
    }

    @Override
    public VentaEntity enabled(VentaEntity ventaEntity) {
        VentaEntity objVenta = ventaRepository.getById(ventaEntity.getCodigo());
        objVenta.setEstado(true);
        return ventaRepository.save(objVenta);
    }
}
