package Aplicacion.CONTROLADOR.CLIENTE;

import Aplicacion.MODELO.DAO.CLIENTE.ClienteDAO;

public class ControladorCliente extends ClienteDAO {
    public boolean nuevoCliente(Object object){
        return agregar(object);
    }
}
