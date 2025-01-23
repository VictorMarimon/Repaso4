package Aplicacion.MODELO.DAO.PIEZA;

public class Pieza {
    private String descripcion;
    private String marca;
    private int id_inventario_pieza;
    private int nit_proveedor;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId_inventario_pieza() {
        return id_inventario_pieza;
    }

    public void setId_inventario_pieza(int id_inventario_pieza) {
        this.id_inventario_pieza = id_inventario_pieza;
    }

    public int getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(int nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }
}
