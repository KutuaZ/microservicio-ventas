package cl.duoc.d.semana3d;

public class Venta {
    private int id;
    private String producto;
    private String tipoMascota;
    private String raza;
    private int cantidad;
    private double precioUnitario;
    private String fecha;

    public Venta(int id, String producto, String tipoMascota, String raza, int cantidad, double precioUnitario, String fecha) {
        this.id = id;
        this.producto = producto;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fecha = fecha;
    }

    public double getTotal() {
        return cantidad * precioUnitario;
    }

    public int getId() {
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