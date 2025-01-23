package Aplicacion.MODELO.DAO.CLIENTE;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.List;

public class ClienteDAO implements IDAO {

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
        Cliente cliente = (Cliente) object;

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into cliente(cedula, nombre, apellido, fecha_nacimiento, direccion, telefono, tipo)\n" +
                "values(?,?,?,?,?,?,'nuevo')";

        try {

            ps = con.prepareStatement(sql);

            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getFecha_nacimiento());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getTelefono());

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar un nuevo cliente " + e.getMessage());
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
