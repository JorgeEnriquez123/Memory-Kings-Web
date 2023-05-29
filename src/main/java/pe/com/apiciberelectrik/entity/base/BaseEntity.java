package pe.com.apiciberelectrik.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder // herencia
@NoArgsConstructor // metodo constructor sin parametros
@AllArgsConstructor //metodo constructor con parametros
@Data //metodos Get y Set
@MappedSuperclass //mapear la herencia
public class BaseEntity {
    @Column(name="estado", nullable = false) //nombre de la columna en la tabla
    private boolean estado = true;
}
