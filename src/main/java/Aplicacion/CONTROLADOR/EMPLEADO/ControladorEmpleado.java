package Aplicacion.CONTROLADOR.EMPLEADO;

import Aplicacion.MODELO.DAO.EMPLEADO.EmpleadoDAO;

public class ControladorEmpleado extends EmpleadoDAO {
    public boolean nuevoEmpleado(Object object){
        return agregar(object);
    }
}
