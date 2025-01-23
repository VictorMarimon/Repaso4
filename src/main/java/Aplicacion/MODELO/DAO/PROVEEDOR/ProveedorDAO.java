package Aplicacion.MODELO.DAO.PROVEEDOR;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.EMPLEADO.Empleado;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOPIEZA.InventarioPieza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProveedorDAO implements IDAO {
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
        Proveedor proveedor = (Proveedor) object;

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into proveedor(nit, empresa, telefono, email)\n" +
                "values(?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setInt(1, proveedor.getNit());
            ps.setString(2, proveedor.getEmpresa());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo proveedor " + e.getMessage());
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

    public int buscarNIT(Proveedor proveedor){

        PreparedStatement ps;
        ResultSet rs;

        Connection con = conexionInst.getConexion();

        var sql = "select nit from proveedor p where p.empresa = ?;";

        int ID;

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getEmpresa());
            rs = ps.executeQuery();

            if (rs.next()){
                ID = rs.getInt("nit");
                return ID;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar nit del proveedor " + e.getMessage());
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
