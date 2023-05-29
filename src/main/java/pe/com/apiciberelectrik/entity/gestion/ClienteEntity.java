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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
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
@Entity(name="ClienteEntity") 
@Table(name="cliente", uniqueConstraints = @UniqueConstraint(columnNames = "dnicli")) 
public class ClienteEntity extends BaseEntity implements  Serializable {
    private static final long serialVersionUID=1L;
    @Id 
    @Column(name="codcli")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    @Column(name="dnicli",length = 8, nullable = false)
    @Size(min=8,max=8,message = "El dni debe de tener 8 caracteres.")
    private String dni;
    @Column(name="nomcli",length = 50, nullable = false)
    @Size(min=5,max=50,message = "El nombre debe de tener como mínimo {min} y máximo {max} caracteres.")
    private String nombre;
    @Column(name="domicli", length = 100, nullable = false)
    @Size(min=5,max=100,message = "El domicilio debe de tener como mínimo {min} y máximo {max} caracteres.")
    private String domicilio;
    @Column(name="telcli", length = 9, nullable = true)
    @Size(min=5,max=9,message = "El telefono debe de tener como mínimo {min} y máximo {max} caracteres.")
    private String telefono;
}
