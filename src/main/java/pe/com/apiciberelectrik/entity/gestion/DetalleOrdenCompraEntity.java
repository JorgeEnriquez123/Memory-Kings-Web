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
@Entity(name="DetalleOrdenCompraEntity")
@Table(name="detalleordencompra")
public class DetalleOrdenCompraEntity implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name="codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @Column(name="stock",nullable = false)
    @Positive(message = "La cantidad debe de ser mayor a cero.")//permite valores positivos o cero
    @NotNull(message="Debe de ingresar la cantidad")
    @Max(value = 999, message = "El valor no puede ser mayor a 999")
    private int cantidad;

    @Column(name="precio",nullable = false)
    @Positive(message = "El precio debe de ser mayor a 0.0")//permita valores positivos
    @NotNull(message="Debe de ingresar el precio")
    @DecimalMax("999999.99")
    private double preciouni;

    @NotNull(message = "Debe seleccionarse un producto.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codpro",nullable = false)
    private ProductoEntity producto;

    @NotNull(message = "Debe seleccionarse una guia de entrada.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codcompra",nullable = false)
    private OrdenCompraEntity ordencompra;


}
