package pe.com.apiciberelectrik.entity.gestion;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.com.apiciberelectrik.entity.base.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="GuiaEntradaEntity")
@Table(name="guiaentrada")
public class GuiaEntradaEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name="codentrada")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @NotNull(message = "Debe seleccionarse un usuario.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="coduse",nullable = false)
    private UsuarioEntity usuario;

    @Column(name="fecha", nullable = false)
    private LocalDate fechaguiaentrada = LocalDate.now();

    @NotNull(message = "Debe seleccionar un proveedor.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codcompra",nullable = false)
    private OrdenCompraEntity ordencompra;

}
