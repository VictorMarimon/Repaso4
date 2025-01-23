package Aplicacion.MODELO.DAO.PIEZASERVICIO;

import Aplicacion.MODELO.CONEXION.Conexion;
import Aplicacion.MODELO.DAO.CLIENTE.Cliente;
import Aplicacion.MODELO.DAO.EMPLEADO.Empleado;
import Aplicacion.MODELO.DAO.IDAO;
import Aplicacion.MODELO.DAO.PIEZA.Pieza;
import Aplicacion.MODELO.DAO.PIEZA.PiezaDAO;
import Aplicacion.MODELO.DAO.SERVICIO.Servicio;
import Aplicacion.MODELO.DAO.SERVICIO.ServicioDAO;
import Aplicacion.MODELO.DAO.VEHICULO.Vehiculo;
import Aplicacion.MODELO.DAO.VEHICULO.VehiculoDAO;
import Aplicacion.MODELO.DAO.VENTA.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PiezaServicioDAO implements IDAO {
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

    public boolean agregarVenta(Servicio servicio, Pieza pieza) {

        ServicioDAO sDAO = new ServicioDAO();
        PiezaDAO pDAO = new PiezaDAO();

        PreparedStatement ps;

        Connection con = conexionInst.getConexion();

        var sql = "insert into piezaservicio(id_servicio, id_pieza, fecha)\n" +
                "values(?,?,CURRENT_DATE)";

        try {

            ps = con.prepareStatement(sql);

            int ID_SERVICIO = sDAO.buscarID(servicio);

            if (ID_SERVICIO != -1){
                ps.setInt(1, ID_SERVICIO);
            }else{
                ps.setInt(1, 1);
            }

            int ID_PIEZA = pDAO.buscarID(pieza);

            if (ID_PIEZA != -1){
                ps.setInt(2, ID_PIEZA);
            }else{
                ps.setInt(2, 1);
            }

            ps.execute();
            return true;

        }catch (Exception e) {
            System.out.println("Error al agregar una nueva pieza servicio " + e.getMessage());
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
