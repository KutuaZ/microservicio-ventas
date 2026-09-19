package cl.duoc.d.semana3d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.d.semana3d.model.ResumenGanancias;
import cl.duoc.d.semana3d.model.Venta;

@RestController
public class VentaController {

    private List<Venta> ventas = new ArrayList<>();

    public VentaController() {
        ventas.add(new Venta(1, "Alimento Perro 15kg", "Perro", "Raza Grande", 2, 25000, "2026-08-28"));
        ventas.add(new Venta(2, "Rascador Gato", "Gato", "Mestizo", 1, 30000, "2026-08-28"));
        ventas.add(new Venta(3, "Juguete Hueso", "Perro", "Poodle", 2, 5000, "2026-08-15"));
        ventas.add(new Venta(4, "Cama Mascotas", "Perro", "Mediana", 1, 40000, "2026-05-10"));
        ventas.add(new Venta(5, "Acuario 20L", "Pez", "Betta", 1, 60000, "2025-12-10"));
    }

    @GetMapping("/ventas")
    public List<Venta> getAllVentas() {
        return ventas;
    }

    @GetMapping("/ventas/ganancias")
    public ResumenGanancias getGanancias(
            @RequestParam(defaultValue = "diario") String periodo,
            @RequestParam(defaultValue = "2026-08-28") String fecha) {

        double suma = 0.0;
        String anio = fecha.substring(0, 4);
        String mes = fecha.substring(0, 7);

        for (Venta v : ventas) {
            if ("diario".equalsIgnoreCase(periodo) && v.getFecha().equals(fecha)) {
                suma += v.getTotal();
            } else if ("mensual".equalsIgnoreCase(periodo) && v.getFecha().startsWith(mes)) {
                suma += v.getTotal();
            } else if ("anual".equalsIgnoreCase(periodo) && v.getFecha().startsWith(anio)) {
                suma += v.getTotal();
            }
        }

        return new ResumenGanancias(periodo, suma);
    }
}