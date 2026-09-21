package cl.duoc.d.semana3d.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "venta")
@JsonPropertyOrder({ "id", "producto", "tipoMascota", "raza", "cantidad", "precioUnitario", "fecha" })
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank (message = "El nombre del producto es obligatorio")
    @Size (min = 2, max = 100, message = "El nombre del producto debe tener entre 2 y 100 caracteres")
    @Column (name = "producto")
    private String producto;

    @NotBlank (message = "El tipo de mascota es obligatorio (ej. perro, gato, ave, etc.)")
    @Column (name = "tipo_mascota")
    private String tipoMascota;

    @NotBlank (message = "La raza es obligatoria")
    @Column (name = "raza")
    private String raza;

    @Min (value = 1, message = "La cantidad debe ser al menos 1 unidad")
    @Column (name = "cantidad")
    private int cantidad;

    @DecimalMin(value = "0.1", message = "El precio unitario debe ser al menos 0.1")
    @Column (name = "precio_unitario")
    private double precioUnitario;

    @NotNull(message = "La fecha de la venta es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener el formato YYYY-MM-DD (ej. 2023-12-31)")
    @Column (name = "fecha")
    private String fecha;

    @AssertTrue(message = "La fecha debe ser una fecha válida con formato YYYY-MM-DD")
    @JsonIgnore
    @Transient
    public boolean isFechaValida() {
        if (fecha == null || fecha.isBlank()) {
            return true;
        }

        try {
            LocalDate.parse(fecha);
            return true;
        } catch (java.time.format.DateTimeParseException ex) {
            return false;
        }
    }

    
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

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setId(Long id) {
        this.id = id;
    }
}