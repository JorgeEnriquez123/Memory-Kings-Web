/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.apiciberelectrik.service.impl.gestion;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.ReclamoEntity;
import pe.com.apiciberelectrik.entity.gestion.RolEntity;
import pe.com.apiciberelectrik.repository.gestion.ReclamoRepository;
import pe.com.apiciberelectrik.service.gestion.ReclamoService;

/**
 *
 * @author user
 */
@Service
public class ReclamoServiceImp implements ReclamoService {

    @Autowired
    private ReclamoRepository repositorio;

    @Override
    public List<ReclamoEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<ReclamoEntity> findAllCustom() {
        return repositorio.findAllCustom();
    }

    @Override
    public ReclamoEntity add(ReclamoEntity t) {
        return repositorio.save(t);
    }

    @Override
    public Optional<ReclamoEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public ReclamoEntity update(ReclamoEntity t) {
        ReclamoEntity objReclamo = repositorio.getById(t.getCodigo());
        BeanUtils.copyProperties(t, objReclamo);
        return repositorio.save(objReclamo);
    }

    @Override
    public ReclamoEntity delete(ReclamoEntity t) {
        ReclamoEntity objReclamo = repositorio.getById(t.getCodigo());
        objReclamo.setEstado(false);
        return repositorio.save(objReclamo);
    }

    @Override
    public ReclamoEntity enabled(ReclamoEntity t) {
        ReclamoEntity objReclamo = repositorio.getById(t.getCodigo());
        objReclamo.setEstado(true);
        return repositorio.save(objReclamo);
    }

}
