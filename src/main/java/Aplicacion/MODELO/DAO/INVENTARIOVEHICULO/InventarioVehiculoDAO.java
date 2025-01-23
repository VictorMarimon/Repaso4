package Aplicacion.MODELO.DAO.INVENTARIOVEHICULO;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOPIEZA.InventarioPieza;
import Aplicacion.MODELO.DAO.PROVEEDOR.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventarioVehiculoDAO implements IDAO {
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
        InventarioVehiculo inventarioVehiculo = (InventarioVehiculo) object;

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into inventarioVehiculo(cantidad, lote, tipo)\n" +
                "values(?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setInt(1, inventarioVehiculo.getCantidad());
            ps.setString(2, inventarioVehiculo.getLote());
            ps.setString(3, inventarioVehiculo.getTipo());

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo inventario de vehiculo " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean modificar(Object object) {
        return false;
    }

    public int buscarID(InventarioVehiculo invVehiculo){

        PreparedStatement ps;
        ResultSet rs;

        Connection con = conexionInst.getConexion();

        var sql = "select id from inventariovehiculo i where i.lote = ?;";

        int ID;

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, invVehiculo.getLote());
            rs = ps.executeQuery();

            if (rs.next()){
                ID = rs.getInt("id");
                return ID;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar id inventario vehiculo " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }
        ID = -1;

        return ID;
    }
}
