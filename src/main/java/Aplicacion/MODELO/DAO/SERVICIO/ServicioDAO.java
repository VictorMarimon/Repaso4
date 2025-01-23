package Aplicacion.MODELO.DAO.SERVICIO;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.EMPLEADO.Empleado;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculo;
import Aplicacion.MODELO.DAO.VEHICULO.Vehiculo;
import Aplicacion.MODELO.DAO.VEHICULOCLIENTE.VehiculoClienteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServicioDAO implements IDAO {
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

    public boolean agregarServicio(Object object, Empleado empleado, Vehiculo vehiculo) {
        Servicio servicio = (Servicio) object;

        VehiculoClienteDAO vcDAO = new VehiculoClienteDAO();

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into servicio(descripcion, fecha, tipo, cedula_empleado, id_vehiculo_cliente)\n" +
                "values(?,?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, servicio.getDescripcion());
            ps.setString(2, servicio.getFecha());
            ps.setString(3, servicio.getTipo());
            ps.setInt(4, empleado.getCedula());

            int ID_VEHICULO_CLIENTE = vcDAO.buscarID(vehiculo);

            if (ID_VEHICULO_CLIENTE != -1){
                ps.setInt(5, ID_VEHICULO_CLIENTE);
            }else{
                ps.setInt(5, 1);
            }

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo servicio " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }

    public int buscarID(Servicio servicio){

        PreparedStatement ps;
        ResultSet rs;

        Connection con = conexionInst.getConexion();

        var sql = "select id from servicio where fecha = ? and tipo = ?;";

        int ID;

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, servicio.getFecha());
            ps.setString(2, servicio.getTipo());
            rs = ps.executeQuery();

            if (rs.next()){
                ID = rs.getInt("id");
                return ID;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar id servicio " + e.getMessage());
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
