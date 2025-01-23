package Aplicacion.MODELO.DAO.INVENTARIOPIEZA;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InventarioPiezaDAO implements IDAO {
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
        InventarioPieza inventarioPieza = (InventarioPieza) object;

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into inventarioPieza(cantidad, lote, tipo)\n" +
                "values(?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setInt(1, inventarioPieza.getCantidad());
            ps.setString(2, inventarioPieza.getLote());
            ps.setString(3, inventarioPieza.getTipo());

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo inventario de pieza " + e.getMessage());
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

    public int buscarID(InventarioPieza invPieza){

        PreparedStatement ps;
        ResultSet rs;

        Connection con = conexionInst.getConexion();

        var sql = "select id from inventariopieza i where i.lote = ?;";

        int ID;

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, invPieza.getLote());
            rs = ps.executeQuery();

            if (rs.next()){
                ID = rs.getInt("id");
                return ID;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar id inventario pieza " + e.getMessage());
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
