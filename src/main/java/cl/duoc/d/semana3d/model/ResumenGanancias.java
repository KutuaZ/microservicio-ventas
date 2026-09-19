package cl.duoc.d.semana3d.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResumenGanancias {
    @JsonIgnore
    private Long id;

    private String periodo;
    private double gananciaTotal;

    public ResumenGanancias() {
    }

    public ResumenGanancias(String periodo, double gananciaTotal) {
        this.periodo = periodo;
        this.gananciaTotal = gananciaTotal;
    }

    public Long getId() {
        return id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }
}