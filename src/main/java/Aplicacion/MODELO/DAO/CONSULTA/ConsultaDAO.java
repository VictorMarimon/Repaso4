package Aplicacion.MODELO.DAO.CONSULTA;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.CLIENTE.Cliente;
import Aplicacion.MODELO.DAO.EMPLEADO.Empleado;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.VEHICULO.Vehiculo;
import Aplicacion.MODELO.DAO.VEHICULO.VehiculoDAO;
import Aplicacion.MODELO.DAO.VENTA.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ConsultaDAO implements IDAO {

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

    public boolean agregarConsulta(Cliente cliente, Vehiculo vehiculo) {

        VehiculoDAO vDAO = new VehiculoDAO();

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into consulta(cedula_cliente, id_vehiculo, fecha)\n" +
                "values(?,?, CURRENT_DATE)";

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
            System.out.println("Error al agregar una nueva consulta " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }
}
