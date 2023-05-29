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
@Entity(name="VentaEntity")
@Table(name="venta")
public class VentaEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="codventa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @NotNull(message = "Debe seleccionarse un usuario.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="coduse",nullable = false)
    private UsuarioEntity usuario;

    @Column(name="fecha", nullable = false)
    private LocalDate fechaventa = LocalDate.now();

    @NotNull(message = "Debe seleccionarse un cliente.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codcli",nullable = false)
    private ClienteEntity cliente;
}
