package Aplicacion.MODELO.DAO.VEHICULO;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String estado;
    private double precio;
    private String fecha_fabricacion;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(String fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }
}
