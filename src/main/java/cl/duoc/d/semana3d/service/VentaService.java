package cl.duoc.d.semana3d.service;

import java.util.List;
import java.util.Optional;

import cl.duoc.d.semana3d.model.ResumenGanancias;
import cl.duoc.d.semana3d.model.Venta;

public interface VentaService {
    
    List<Venta> getAllVentas();
    Optional<Venta> getVentaById(Long id);
    ResumenGanancias getGanancias(String periodo, String fecha);

}
