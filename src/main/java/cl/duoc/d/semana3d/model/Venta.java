package cl.duoc.d.semana3d.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
@JsonPropertyOrder({ "id", "producto", "tipoMascota", "raza", "cantidad", "precioUnitario", "fecha" })
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "producto")
    private String producto;

    @Column (name = "tipo_mascota")
    private String tipoMascota;

    @Column (name = "raza")
    private String raza;

    @Column (name = "cantidad")
    private int cantidad;

    @Column (name = "precio_unitario")
    private double precioUnitario;

    @Column (name = "fecha")
    private String fecha;

    
    public double getTotal() {
        return cantidad * precioUnitario;
    }

    public Long getId() {
        return id;
    }

    public String getProducto() {
        return producto;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public String getRaza() {
        return raza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public String getFecha() {
        return fecha;
    }
}