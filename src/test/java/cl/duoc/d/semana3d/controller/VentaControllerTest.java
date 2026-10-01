package cl.duoc.d.semana3d.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.duoc.d.semana3d.model.Venta;
import cl.duoc.d.semana3d.service.VentaService;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(VentaController.class)
class VentaControllerTest {

    @Autowired 
    private MockMvc mockMvc;

    @MockitoBean
    private VentaService ventaService;

    @Autowired 
    private ObjectMapper Mapper;

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
    void testGetAllVentas() throws Exception {
        when(ventaService.getAllVentas()).thenReturn(Arrays.asList(venta));
        mockMvc.perform(get("/ventas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].producto").value("Comida para perros"));
    }

    @Test
    void testGetVentaById() throws Exception {
        when(ventaService.getVentaById(1L)).thenReturn(Optional.of(venta));
        mockMvc.perform(get("/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.producto").value("Comida para perros"));
    }
    
    @Test 
    void testCreateVenta() throws Exception {
        when(ventaService.createVenta(any(Venta.class))).thenReturn(venta);
        mockMvc.perform(post("/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Mapper.writeValueAsString(venta)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.producto").value("Comida para perros"));
    }

    @Test
    void testUpdateVenta() throws Exception {
        when(ventaService.updateVenta(eq(1L), any(Venta.class))).thenReturn(venta);
        mockMvc.perform(put("/ventas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Mapper.writeValueAsString(venta)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.producto").value("Comida para perros"));
    }

    @Test
    void testDeleteVenta() throws Exception {
        mockMvc.perform(delete("/ventas/1"))
                .andExpect(status().isOk());
        verify(ventaService).deleteVenta(1L);
    }

    
}
