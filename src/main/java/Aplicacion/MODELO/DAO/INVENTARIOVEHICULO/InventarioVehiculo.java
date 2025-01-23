package Aplicacion.MODELO.DAO.INVENTARIOVEHICULO;

public class InventarioVehiculo {
    private int cantidad;
    private String lote;
    private String tipo;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
