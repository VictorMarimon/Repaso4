package Aplicacion.MODELO.DAO.VEHICULOCLIENTE;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.CLIENTE.Cliente;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculo;
import Aplicacion.MODELO.DAO.VEHICULO.Vehiculo;
import Aplicacion.MODELO.DAO.VEHICULO.VehiculoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehiculoClienteDAO implements IDAO {
    private Conexion conexionInst = Conexion.getInstance();
    @Override
    public List<Object> listar() {
        return List.of();
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

    public boolean agregarVehiculoCliente(Cliente cliente, Vehiculo vehiculo) {

        VehiculoDAO vDAO = new VehiculoDAO();

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into vehiculoCliente(cedula_cliente, id_vehiculo)\n" +
                "values(?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setInt(1, cliente.getCedula());

            int ID_VEHICULO = vDAO.buscarPlaca(vehiculo);

            if (ID_VEHICULO != -1){
                ps.setInt(2, ID_VEHICULO);
            }else{
                ps.setInt(2, 1);
            }

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo vehiculo cliente " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }

    public int buscarID(Vehiculo vehiculo){

        VehiculoDAO vDAO = new VehiculoDAO();

        PreparedStatement ps;
        ResultSet rs;

        Connection con = conexionInst.getConexion();

        var sql = "select id from vehiculocliente where id_vehiculo = ?;";

        int ID;

        try{
            ps = con.prepareStatement(sql);

            int ID_VEHICULO = vDAO.buscarPlaca(vehiculo);

            if (ID_VEHICULO != -1){
                ps.setInt(1, ID_VEHICULO);
            }else{
                ps.setInt(1, 1);
            }

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
