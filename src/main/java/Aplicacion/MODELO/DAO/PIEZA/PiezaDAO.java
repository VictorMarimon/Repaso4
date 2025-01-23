package Aplicacion.MODELO.DAO.PIEZA;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOPIEZA.InventarioPieza;
import Aplicacion.MODELO.DAO.INVENTARIOPIEZA.InventarioPiezaDAO;
import Aplicacion.MODELO.DAO.PROVEEDOR.Proveedor;
import Aplicacion.MODELO.DAO.PROVEEDOR.ProveedorDAO;
import Aplicacion.MODELO.DAO.SERVICIO.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PiezaDAO implements IDAO {
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

    public boolean agregarPieza(Object object, InventarioPieza invPieza, Proveedor proveedor) {
        Pieza pieza = (Pieza) object;

        InventarioPiezaDAO invPiezaDAO = new InventarioPiezaDAO();

        ProveedorDAO pDAO = new ProveedorDAO();

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into pieza(descripcion, marca, id_inventario_pieza, nit_proveedor)\n" +
                "values(?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, pieza.getDescripcion());
            ps.setString(2, pieza.getMarca());

            int ID_INVENTARIO_PIEZA = invPiezaDAO.buscarID(invPieza);

            if (ID_INVENTARIO_PIEZA != -1){
                ps.setInt(3, ID_INVENTARIO_PIEZA);
            }else{
                ps.setInt(3, 1);
            }

            int NIT_PROVEEDOR = pDAO.buscarNIT(proveedor);

            if (NIT_PROVEEDOR != -1){
                ps.setInt(4, NIT_PROVEEDOR);
            }else{
                ps.setInt(4, 1);
            }

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar una nueva pieza " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }

    public int buscarID(Pieza pieza){

        PreparedStatement ps;
        ResultSet rs;

        Connection con = conexionInst.getConexion();

        var sql = "select id from pieza where marca = ? and descripcion = ?;";

        int ID;

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, pieza.getMarca());
            ps.setString(2, pieza.getDescripcion());
            rs = ps.executeQuery();

            if (rs.next()){
                ID = rs.getInt("id");
                return ID;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar id pieza " + e.getMessage());
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
