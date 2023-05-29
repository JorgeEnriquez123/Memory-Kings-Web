/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.apiciberelectrik.repository.gestion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.UsuarioEntity;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity , Long> {
    @Query("select u from UsuarioEntity u where u.estado=1")
    List<UsuarioEntity> findAllCustom();
    UsuarioEntity findByUsuario(String nombre);
}
