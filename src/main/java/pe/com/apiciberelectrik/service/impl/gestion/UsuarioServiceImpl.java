/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.apiciberelectrik.service.impl.gestion;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.apiciberelectrik.entity.gestion.ProductoEntity;
import pe.com.apiciberelectrik.entity.gestion.UsuarioEntity;
import pe.com.apiciberelectrik.repository.gestion.ProductoRepository;
import pe.com.apiciberelectrik.repository.gestion.RolRepository;
import pe.com.apiciberelectrik.repository.gestion.UsuarioRepository;
import pe.com.apiciberelectrik.service.gestion.RolService;
import pe.com.apiciberelectrik.service.gestion.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{

    @Autowired
    private UsuarioRepository repositorio;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Override
    public List<UsuarioEntity> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<UsuarioEntity> findAllCustom() {
        return repositorio.findAllCustom();
    }

    @Override
    public UsuarioEntity add(UsuarioEntity t) {
        return repositorio.save(t);
    }

    @Override
    public Optional<UsuarioEntity> findById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public UsuarioEntity update(UsuarioEntity t) {
        UsuarioEntity objUsuario = repositorio.getById(t.getCodigo());
        BeanUtils.copyProperties(t, objUsuario);
        return repositorio.save(objUsuario);
    }

    @Override
    public UsuarioEntity delete(UsuarioEntity t) {
        UsuarioEntity objUsuario = repositorio.getById(t.getCodigo());
        objUsuario.setEstado(false);
        return repositorio.save(objUsuario);
    }

    @Override
    public UsuarioEntity enabled(UsuarioEntity t) {
        UsuarioEntity objUsuario = repositorio.getById(t.getCodigo());
        objUsuario.setEstado(true);
        return repositorio.save(objUsuario);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity us = repositorio.findByUsuario(username);
        List<GrantedAuthority> rol = new ArrayList<>();
        rol.add(new SimpleGrantedAuthority(us.getRol().getNombre()));

        UserDetails userdetails = new User(us.getUsuario(), us.getPassword(), rol);
        return userdetails;
    }
}
