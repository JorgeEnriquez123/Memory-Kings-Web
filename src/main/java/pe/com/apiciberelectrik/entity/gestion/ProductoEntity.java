package pe.com.apiciberelectrik.entity.gestion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.com.apiciberelectrik.entity.base.BaseEntity;

@SuperBuilder 
@NoArgsConstructor 
@AllArgsConstructor 
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="ProductoEntity") 
@Table(name="producto", uniqueConstraints = @UniqueConstraint(columnNames = "despro")) 
public class ProductoEntity extends BaseEntity implements  Serializable{
    private static final long serialVersionUID=1L;
    @Id 
    @Column(name="codpro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    @Column(name="despro",length = 200, nullable = false)
    @Size(min=5,max=200,message = "La descripción debe de tener como minimo {min} y maximo {max} caracteres.")
    private String descripcion;
    @Column(name="prepro",nullable = false)
    @Positive(message = "El precio debe de ser mayor a 0.")//permita valores positivos
    @NotNull(message="Debe de ingresar el precio")
    @DecimalMax(value = "999999.99")
    private double precio;
    @Column(name="stopro",nullable = false)
    @PositiveOrZero(message = "La cantidad debe de ser mayor o igual a cero.")//permite valores positivos o cero
    @NotNull(message="Debe de ingresar el stock")
    @Max(value = 999, message = "El valor no puede ser mayor a 999")
    private int stock = 0;

    @NotNull(message = "Debe seleccionar una categoría.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codcat",nullable = false)
    private CategoriaEntity categoria;

    public void agregarStock(int cantidad){
        this.stock += cantidad;
    }
    public void restarStock(int cantidad){
        this.stock -= cantidad;
    }
}
