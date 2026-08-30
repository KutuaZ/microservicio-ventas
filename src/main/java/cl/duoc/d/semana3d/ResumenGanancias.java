package cl.duoc.d.semana3d;

public class ResumenGanancias {
    private String periodo;
    private double gananciaTotal;

    public ResumenGanancias(String periodo, double gananciaTotal) {
        this.periodo = periodo;
        this.gananciaTotal = gananciaTotal;
    }

    public String getPeriodo() {
        return periodo;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }
}