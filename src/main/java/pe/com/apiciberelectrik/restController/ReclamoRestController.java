/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.apiciberelectrik.restController;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.apiciberelectrik.entity.gestion.ReclamoEntity;
import pe.com.apiciberelectrik.entity.gestion.RolEntity;
import pe.com.apiciberelectrik.service.gestion.ReclamoService;
import pe.com.apiciberelectrik.service.gestion.RolService;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/reclamo")
public class ReclamoRestController {
     @Autowired
    private ReclamoService servicio;
    
    @GetMapping
    public List<ReclamoEntity> findAll(){
        return servicio.findAll();
    }
    @GetMapping("/custom")
    public List<ReclamoEntity>findAllCustom(){
        return servicio.findAllCustom();
    }
    @PostMapping
    public ReclamoEntity add(@RequestBody ReclamoEntity r){
        return servicio.add(r);
    }
    @GetMapping("/{id}")
    public Optional<ReclamoEntity>findById(@PathVariable long id){
        return servicio.findById(id);
    }
    @PutMapping("/{id}")
    public ReclamoEntity update(@PathVariable long id,@RequestBody ReclamoEntity r){
        r.getCodigo();
        return servicio.update(r);
    }
    @DeleteMapping("/{id}")
    public ReclamoEntity delete(@PathVariable long id){
        ReclamoEntity objReclamo = new ReclamoEntity();
        objReclamo.setCodigo(id);
        return servicio.delete(ReclamoEntity.builder().codigo(id).build());
    }
}
