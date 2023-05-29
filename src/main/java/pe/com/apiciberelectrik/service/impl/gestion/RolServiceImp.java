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
import pe.com.apiciberelectrik.entity.gestion.RolEntity;
import pe.com.apiciberelectrik.repository.gestion.RolRepository;
import pe.com.apiciberelectrik.service.gestion.RolService;

/**
 *
 * @author user
 */
@Service
public class RolServiceImp implements RolService {

    @Autowired
    private RolRepository repositorio;

    @Override
    public List<RolEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<RolEntity> findAllCustom() {
        return repositorio.findAllCustom();
    }

    @Override
    public RolEntity add(RolEntity t) {
        return repositorio.save(t);
    }

    @Override
    public Optional<RolEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public RolEntity update(RolEntity t) {
        RolEntity objRol = repositorio.getById(t.getCodigo());
        BeanUtils.copyProperties(t, objRol);
        return repositorio.save(objRol);
    }

    @Override
    public RolEntity delete(RolEntity t) {
        RolEntity objRol = repositorio.getById(t.getCodigo());
        objRol.setEstado(false);
        return repositorio.save(objRol);
    }

    @Override
    public RolEntity enabled(RolEntity t) {
        RolEntity objRol = repositorio.getById(t.getCodigo());
        objRol.setEstado(true);
        return repositorio.save(objRol);
    }

}
