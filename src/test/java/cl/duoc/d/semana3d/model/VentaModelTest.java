package cl.duoc.d.semana3d.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VentaModelTest {
    @Test
    void testVentaModel() {
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setProducto("Comida para perros");
        venta.setTipoMascota("Perro");
        venta.setRaza("Labrador");
        venta.setCantidad(5);
        venta.setPrecioUnitario(2000.0);

        assertEquals(1L, venta.getId());
        assertEquals("Comida para perros", venta.getProducto());
        assertEquals("Perro", venta.getTipoMascota());
        assertEquals("Labrador", venta.getRaza());
        assertEquals(5, venta.getCantidad());
        assertEquals(2000.0, venta.getPrecioUnitario());
    }
}
