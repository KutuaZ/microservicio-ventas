package cl.duoc.d.semana3d.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.d.semana3d.model.ResumenGanancias;
import cl.duoc.d.semana3d.model.Venta;
import cl.duoc.d.semana3d.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public ResumenGanancias getGanancias(String periodo, String fecha) {
        List<Venta> ventas = ventaRepository.findAll();
        double suma = 0.0;

        if (fecha == null || fecha.isBlank()) {
            fecha = "2026-08-28";
        }

        String anio = fecha.length() >= 4 ? fecha.substring(0, 4) : "";
        String mes = fecha.length() >= 7 ? fecha.substring(0, 7) : "";

        for (Venta v : ventas) {
            if (v.getFecha() == null || v.getFecha().isBlank()) {
                continue;
            }

            if ("diario".equalsIgnoreCase(periodo) && fecha.length() >= 10 && v.getFecha().equals(fecha)) {
                suma += v.getTotal();
            } else if ("mensual".equalsIgnoreCase(periodo) && !mes.isEmpty() && v.getFecha().startsWith(mes)) {
                suma += v.getTotal();
            } else if ("anual".equalsIgnoreCase(periodo) && !anio.isEmpty() && v.getFecha().startsWith(anio)) {
                suma += v.getTotal();
            }
        }

        return new ResumenGanancias(periodo, suma);
    }

    @Override
    public Venta createVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Long id, Venta venta) {
        return ventaRepository.findById(id).map(v -> {
            v.setFecha(venta.getFecha());
            v.setCantidad(venta.getCantidad());
            v.setPrecioUnitario(venta.getPrecioUnitario());
            return ventaRepository.save(v);
        }).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }

}