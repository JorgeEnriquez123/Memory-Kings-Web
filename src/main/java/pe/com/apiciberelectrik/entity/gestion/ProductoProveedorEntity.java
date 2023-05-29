package pe.com.apiciberelectrik.entity.gestion;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.apiciberelectrik.entity.base.BaseEntity;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="ProductoProveedorEntity")
@Table(name="productoproveedor", uniqueConstraints = {@UniqueConstraint(columnNames = {"codpro","codprov"})})
@IdClass(ProductoProveedorPK.class)
public class ProductoProveedorEntity implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @NotNull(message = "Debe seleccionar un producto.")
    @ManyToOne
    @JoinColumn(name="codpro",nullable = false)
    private ProductoEntity producto;
    @Id
    @NotNull(message = "Debe seleccionar un proveedor.")
    @ManyToOne
    @JoinColumn(name="codprov",nullable = false)
    private ProveedorEntity proveedor;
    @Basic
    @Column(name="preciocompra",nullable = false)
    @Positive(message = "El precio debe de ser mayor a 0.")//permita valores positivos
    @NotNull(message="Debe de ingresar el precio")
    @DecimalMax("999999.99")
    private double preciocompra;

    @Column(name="estado", nullable = false, columnDefinition = "BIT DEFAULT 1")
    private boolean estado = true;

}
