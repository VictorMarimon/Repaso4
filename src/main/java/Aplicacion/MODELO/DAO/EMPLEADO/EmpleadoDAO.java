package Aplicacion.MODELO.DAO.EMPLEADO;
import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.INVENTARIOVEHICULO.InventarioVehiculo;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                "values(?,?,?,?,?,?,?,?::rolempleado,?,?)";

        try {

            ps = con.prepareStatement(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaNacimiento = LocalDate.parse(empleado.getFecha_nacimiento(), formatter);
            LocalDate fechaContratacion = LocalDate.parse(empleado.getFecha_contratacion(), formatter);

            ps.setInt(1, empleado.getCedula());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setDate(4, Date.valueOf(fechaNacimiento));
            ps.setString(5, empleado.getDireccion());
            ps.setString(6, empleado.getTelefono());
            ps.setDate(7, Date.valueOf(fechaContratacion));
            ps.setString(8, empleado.getRol());
            ps.setTime(9, Time.valueOf(empleado.getHora_inicio()));
            ps.setTime(10, Time.valueOf(empleado.getHora_fin()));

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
