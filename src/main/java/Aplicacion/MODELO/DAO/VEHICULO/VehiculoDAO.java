package Aplicacion.MODELO.DAO.VEHICULO;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.EMPLEADO.Empleado;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOPIEZA.InventarioPieza;
import Aplicacion.MODELO.DAO.INVENTARIOPIEZA.InventarioPiezaDAO;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculo;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculoDAO;
import Aplicacion.MODELO.DAO.PIEZA.Pieza;
import Aplicacion.MODELO.DAO.PROVEEDOR.Proveedor;
import Aplicacion.MODELO.DAO.PROVEEDOR.ProveedorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehiculoDAO implements IDAO {
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

    public boolean agregarVehiculo(Object object, InventarioVehiculo invVehiculo) {
        Vehiculo vehiculo = (Vehiculo) object;

        InventarioVehiculoDAO invVehiculoDAO = new InventarioVehiculoDAO();

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into vehiculo(placa, marca, modelo, estado, precio, fecha_fabricacion, id_inventario_vehiculo)\n" +
                "values(?,?,?,?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getEstado());
            ps.setDouble(5, vehiculo.getPrecio());
            ps.setString(6, vehiculo.getFecha_fabricacion());

            int ID_INVENTARIO_VEHICULO = invVehiculoDAO.buscarID(invVehiculo);

            if (ID_INVENTARIO_VEHICULO != -1){
                ps.setInt(7, ID_INVENTARIO_VEHICULO);
            }else{
                ps.setInt(7, 1);
            }

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo vehiculo " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }

    public int buscarPlaca(Vehiculo vehiculo){

        PreparedStatement ps;
        ResultSet rs;

        Connection con = conexionInst.getConexion();

        var sql = "select id from vehiculo where placa = ?;";

        int PLACA;

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca());
            rs = ps.executeQuery();

            if (rs.next()){
                PLACA = rs.getInt("id");
                return PLACA;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar placa de vehiculo " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }
        PLACA = -1;

        return PLACA;
    }
}
