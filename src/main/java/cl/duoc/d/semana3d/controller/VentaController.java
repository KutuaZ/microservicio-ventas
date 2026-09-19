package cl.duoc.d.semana3d.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.d.semana3d.model.ResumenGanancias;
import cl.duoc.d.semana3d.model.Venta;
import cl.duoc.d.semana3d.service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/ganancias")
    public ResumenGanancias getGanancias(
            @RequestParam(defaultValue = "diario") String periodo,
            @RequestParam(defaultValue = "2026-08-28") String fecha) {
        return ventaService.getGanancias(periodo, fecha);
    }
}