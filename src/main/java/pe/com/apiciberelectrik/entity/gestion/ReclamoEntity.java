/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.apiciberelectrik.entity.gestion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.com.apiciberelectrik.entity.base.BaseEntity;

/**
 *
 * @author user
 */
@SuperBuilder 
@NoArgsConstructor 
@AllArgsConstructor 
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="ReclamoEntity") 
@Table(name="reclamo") 
public class ReclamoEntity extends BaseEntity implements  Serializable {

    private static final long serialVersionUID=1L;
    @Id 
    @Column(name="codreclamo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    @Column(name="descripcion", length = 200, nullable = false)
    @Size(min=1,max=200,message = "La descripción debe de tener como minimo {min} y maximo {max} caracteres.")
    private String descripcion;
    @NotNull(message = "Debe seleccionar un cliente.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codcli",nullable = false)
    private ClienteEntity cliente;
    @Column(name="fechareclamo",nullable = false)
    private LocalDate fechareclamacion = LocalDate.now();
    @NotNull(message = "Debe seleccionarse una venta.")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codventa",nullable = false)
    private VentaEntity venta;
}
