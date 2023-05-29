package pe.com.apiciberelectrik.entity.gestion;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="DetalleVentaEntity")
@Table(name="detalleventa")
public class DetalleVentaEntity implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name="codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @Column(name="cantidad",nullable = false)
    @Positive(message = "La cantidad debe de ser mayor o igual a cero.")//permite valores positivos o cero
    @NotNull(message="Debe de ingresar el stock")
    @Max(value = 999, message = "El valor no puede ser mayor a 999")
    private int cantidad;

    @Column(name="preciouni",nullable = false)
    @Positive(message = "El precio debe de ser mayor a 0.0")//permita valores positivos
    @NotNull(message="Debe de ingresar el precio")
    @DecimalMax("999999.99")
    private double preciouni;

    @NotNull(message = "Debe seleccionarse un producto.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codpro",nullable = false)
    private ProductoEntity producto;

    @NotNull(message = "Debe seleccionarse una venta.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codventa",nullable = false)
    private VentaEntity venta;

}
