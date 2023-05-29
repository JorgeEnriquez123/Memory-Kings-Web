package pe.com.apiciberelectrik.entity.gestion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoProveedorPK implements Serializable {
    private static final long serialVersionUID=1L;

    private long producto;
    private long proveedor;


}
