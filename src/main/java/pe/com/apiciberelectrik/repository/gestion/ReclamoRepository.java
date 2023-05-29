/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.apiciberelectrik.repository.gestion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.ReclamoEntity;
import pe.com.apiciberelectrik.entity.gestion.UsuarioEntity;

/**
 *
 * @author user
 */
@Repository
public interface ReclamoRepository extends JpaRepository<ReclamoEntity , Long> {
    @Query("select r from ReclamoEntity r where r.estado=1")
    List<ReclamoEntity> findAllCustom();
}
