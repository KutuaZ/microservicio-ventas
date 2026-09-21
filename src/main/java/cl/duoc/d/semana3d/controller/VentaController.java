package cl.duoc.d.semana3d.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.d.semana3d.model.ResumenGanancias;
import cl.duoc.d.semana3d.model.Venta;
import cl.duoc.d.semana3d.service.VentaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


@Validated 
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

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ganancias")
    public ResumenGanancias getGanancias(
            @RequestParam(defaultValue = "diario")
            @Pattern(regexp = "^(diario|mensual|anual)$", message = "El periodo debe ser uno de los siguientes: diario, mensual, anual")
            String periodo,
            @RequestParam(defaultValue = "2026-08-28")
            @Pattern(regexp = "^\\d{4}(-\\d{2}(-\\d{2})?)?$", message = "La fecha debe tener formato YYYY, YYYY-MM o YYYY-MM-DD")
            String fecha) {
        return ventaService.getGanancias(periodo, fecha);
    }

    @PostMapping
    public Venta createVenta(@Valid @RequestBody Venta venta) {
        return ventaService.createVenta(venta);
    }

    @PutMapping("/{id}")
    public Venta updateVenta(@PathVariable Long id,@Valid @RequestBody Venta venta) {
        return ventaService.updateVenta(id, venta);
    }

    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
    }

}