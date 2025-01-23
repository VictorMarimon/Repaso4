package Aplicacion.VISTA;

import Aplicacion.VISTA.CLIENTE.MenuCliente;
import Aplicacion.VISTA.EMPLEADO.MenuEmpleado;

import java.util.Scanner;

public class Menu {

    public void MenuPrincipal(){
        Scanner entrada = new Scanner(System.in);

        var opcion = "";

        System.out.println("""
                1. Gestión de Vehículos
                2. Gestión de Clientes
                3. Gestión de Ventas
                4. Gestión de Servicios
                5. Gestión de Proveedores
                6. Gestión de Empleados
                7. Gestión de Departamentos
                8. Consultas
                9. Salir
                >> Seleccione una opción
                """);

        opcion = entrada.next();

        switch (opcion){
            case "2" -> {
                MenuCliente mCliente = new MenuCliente();
                mCliente.MenuCliente();
            }
            case "6" -> {
                MenuEmpleado mEmpleado = new MenuEmpleado();
                mEmpleado.MenuEmpleado();
            }
            case "9" ->{
                System.out.println("""
                        
                        Cerrando Programa...
                        
                        """);
            }
            default -> System.out.println("Seleccione una opción correcta");
        }

    }

}
