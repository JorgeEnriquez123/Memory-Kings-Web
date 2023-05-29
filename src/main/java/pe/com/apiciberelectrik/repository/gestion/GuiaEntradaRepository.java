package pe.com.apiciberelectrik.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.apiciberelectrik.entity.gestion.GuiaEntradaEntity;

import java.util.List;

@Repository
public interface GuiaEntradaRepository extends JpaRepository<GuiaEntradaEntity, Long> {
    @Query("select g from GuiaEntradaEntity g where g.estado=1")
    List<GuiaEntradaEntity> findAllCustom();
}
