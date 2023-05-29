package pe.com.apiciberelectrik.entity.gestion;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.com.apiciberelectrik.entity.base.BaseEntity;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="ProveedorEntity") //define el nombre de la entidad
@Table(name="proveedor", uniqueConstraints = @UniqueConstraint(columnNames = "nomprov")) //define el nombre de la tabla
public class ProveedorEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;
    @Id //representa la clave primaria
    @Column(name="codprov")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincremento
    private long codigo;
    @Column(name="nomprov",length = 100, nullable = false)
    //validacion para controlar el tamaño del ingreso de datos
    @NotNull(message="Debe de ingresar el nombre")
    @Size(min=5, max=100,message = "El nombre debe de tener como minimo {min} y máximo {max}.")
    private String nombre;
}
