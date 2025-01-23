package Aplicacion.MODELO.DAO.VENTA;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.CLIENTE.Cliente;
import Aplicacion.MODELO.DAO.EMPLEADO.Empleado;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculo;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculoDAO;
import Aplicacion.MODELO.DAO.VEHICULO.Vehiculo;
import Aplicacion.MODELO.DAO.VEHICULO.VehiculoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VentaDAO implements IDAO {

    private Conexion conexionInst = Conexion.getInstance();
    @Override
    public List<Object> listar() {
        return null;
    }

    @Override
    public boolean buscar(Object object) {
        return false;
    }

    @Override
    public boolean agregar(Object object) {
        return false;
    }

    @Override
    public boolean modificar(Object object) {
        return false;
    }

    public boolean agregarVenta(Object object, Empleado empleado, Cliente cliente, Vehiculo vehiculo) {
        Venta venta = (Venta) object;

        VehiculoDAO vDAO = new VehiculoDAO();

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into venta(fecha, cantidad, monto, cedula_empleado, cedula_cliente, id_vehiculo)\n" +
                "values(?,?,?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, venta.getFecha());
            ps.setInt(2, venta.getCantidad());
            ps.setInt(3, venta.getMonto());
            ps.setInt(4, empleado.getCedula());
            ps.setInt(5, cliente.getCedula());

            int ID_VEHICULO = vDAO.buscarPlaca(vehiculo);

            if (ID_VEHICULO != -1){
                ps.setInt(6, ID_VEHICULO);
            }else{
                ps.setInt(6, 1);
            }

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar una nueva venta " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }
}
