package Aplicacion.MODELO.DAO.EMPLEADO;
import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoDAO implements IDAO {

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
        Empleado empleado = (Empleado) object;

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into empleado(cedula, nombre, apellido, fecha_nacimiento, direccion, telefono, fecha_contratacion, rol, hora_inicio, hora_fin)\n" +
                "values(?,?,?,?,?,?,?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setInt(1, empleado.getCedula());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setString(4, empleado.getFecha_nacimiento());
            ps.setString(5, empleado.getDireccion());
            ps.setString(6, empleado.getTelefono());
            ps.setString(7, empleado.getFecha_contratacion());
            ps.setString(8, empleado.getRol());
            ps.setString(9, empleado.getHora_inicio());
            ps.setString(10, empleado.getHora_fin());

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo empleado " + e.getMessage());
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
}
