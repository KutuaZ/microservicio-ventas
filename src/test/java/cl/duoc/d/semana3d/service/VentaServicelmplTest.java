package cl.duoc.d.semana3d.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.d.semana3d.model.Venta;
import cl.duoc.d.semana3d.repository.VentaRepository;


@ExtendWith (MockitoExtension.class)
public class VentaServicelmplTest {
    
    @Mock 
    private VentaRepository Repository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    private Venta venta;

    @BeforeEach 
    void setUp() {
        venta = new Venta();
        venta.setId(1L);
        venta.setProducto("Comida para perros");
        venta.setTipoMascota("Perro");
        venta.setRaza("Labrador");
        venta.setCantidad(5);
        venta.setPrecioUnitario(2000.0);
        venta.setFecha("2026-08-28");
    }

    @Test 
    void testGetAllVentas() {
        List<Venta> expected = Arrays.asList(venta);
        when(Repository.findAll()).thenReturn(expected);
        assertEquals(expected, ventaService.getAllVentas());
    }

    @Test 
    void testGetVentaById() {
        when(Repository.findById(1L)).thenReturn(Optional.of(venta));
        assertEquals(Optional.of(venta), ventaService.getVentaById(1L));
    }

    @Test 
    void testCreateVenta() {
        when(Repository.save(venta)).thenReturn(venta);
        assertEquals(venta, ventaService.createVenta(venta));
    }

    @Test 
    void testUpdateVentaExists() {
        when(Repository.findById(1L)).thenReturn(Optional.of(venta));
        when(Repository.save(venta)).thenReturn(venta);
        Venta result = ventaService.updateVenta(1L, venta);
        assertEquals(1L, result.getId());
        assertEquals(venta, result);
        verify(Repository).save(venta);
    }

    @Test
    void testUpdateVentaNotExists() {
        when(Repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ventaService.updateVenta(1L, venta));
        verify(Repository, never()).save(any());
    }

    @Test 
    void testGetGananciasDiario() {
        when(Repository.findAll()).thenReturn(Arrays.asList(venta));
        assertEquals(10000.0, ventaService.getGanancias("diario", "2026-08-28").getGananciaTotal());
    }

    @Test
    void testGetGananciasMensual() {
        when(Repository.findAll()).thenReturn(Arrays.asList(venta));
        assertEquals(10000.0, ventaService.getGanancias("mensual", "2026-08").getGananciaTotal());
    }

    @Test
    void testGetGananciasAnual() {
        when(Repository.findAll()).thenReturn(Arrays.asList(venta));
        assertEquals(10000.0, ventaService.getGanancias("anual", "2026").getGananciaTotal());
    }

    @Test 
    void testGetGananciasInvalidPeriodo() {
        when(Repository.findAll()).thenReturn(Arrays.asList(venta));
        assertEquals(0.0, ventaService.getGanancias("invalid", "2026-08-28").getGananciaTotal());
    }

    @Test 
    void testDeleteVenta() {
        ventaService.deleteVenta(1L);
        verify(Repository).deleteById(1L);
    }


}
