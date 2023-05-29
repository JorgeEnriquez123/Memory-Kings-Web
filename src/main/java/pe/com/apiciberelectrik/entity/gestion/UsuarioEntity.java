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
import jakarta.validation.constraints.*;

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
@Entity(name="UsuarioEntity") 
@Table(name="usuario ", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni","user"})}) 
public class UsuarioEntity extends BaseEntity implements  Serializable {
    private static final long serialVersionUID=1L;
    @Id 
    @Column(name="coduse")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    @Column(name="nomuse",length = 50, nullable = false)
    @Size(min=5,max=50,message = "El nombre debe de tener como minimo {min} y maximo {max} caracteres.")
    private String nombre;
    @Column(name="user", length=40, nullable = false)
    @Size(min=5,max=40, message = "El usuario debe de tener como minimo {min} y maximo {max} caracteres.")
    private String usuario;
    @Column(name="password", length = 200, nullable = false)
    @Size(min=5, message = "El password debe de tener como minimo {min} caracteres.")
    private String password;
    @Column(name="dni", length=8, nullable = false)
    @Size(min=8,max=8,message = "El dni debe de tener 8 caracteres.")
    private String dni;
    @NotNull(message = "Debe seleccionar un rol")
    @ManyToOne //relacion de uno a muchos
    @JoinColumn(name="codrol",nullable = false)
    private RolEntity rol;
}
