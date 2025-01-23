package Aplicacion.VISTA.EMPLEADO;

import Aplicacion.CONTROLADOR.EMPLEADO.ControladorEmpleado;
import Aplicacion.MODELO.DAO.CLIENTE.Cliente;
import Aplicacion.MODELO.DAO.EMPLEADO.Empleado;
import Aplicacion.VISTA.Menu;

import java.util.Scanner;

public class MenuEmpleado {
    ControladorEmpleado ce = new ControladorEmpleado();

    Scanner entrada = new Scanner(System.in);

    public void MenuEmpleado(){

        var opcion = "";

        System.out.println("""
                1. Agregar Empleado
                2. Listar Empleados
                3. Modificar Empleado
                4. Eliminar Empleado
                5. Regresar
                >> Seleccione una opción
                """);

        opcion = entrada.nextLine();

        switch (opcion){
            case "1" ->{
                Agregar();
            }
            case "5" ->{
                Menu m = new Menu();
                m.MenuPrincipal();
            }
            default -> System.out.println("Seleccione una opción correcta");
        }
    }

    public void Agregar(){
        Empleado empleado = new Empleado();

        System.out.print("Ingrese la cédula: ");
        empleado.setCedula(entrada.nextInt());
        entrada.nextLine();

        System.out.print("Ingrese los nombres: ");
        empleado.setNombre(entrada.next());
        entrada.nextLine();

        System.out.print("Ingrese los apellidos: ");
        empleado.setApellido(entrada.next());
        entrada.nextLine();

        System.out.print("Ingrese la fecha de nacimiento: ");
        empleado.setFecha_nacimiento(entrada.next());
        entrada.nextLine();

        System.out.print("Ingrese la dirección: ");
        empleado.setDireccion(entrada.next());
        entrada.nextLine();

        System.out.print("Ingrese el telefono: ");
        empleado.setTelefono(entrada.next());
        entrada.nextLine();

        System.out.println("Ingrese la fecha de contratación: ");
        empleado.setFecha_contratacion(entrada.next());
        entrada.nextLine();

        System.out.println("Ingrese un rol: ");
        System.out.println("(vendedor, servicio, administrativo, seguridad, limpieza)");
        empleado.setRol(entrada.next());
        entrada.nextLine();

        System.out.println("Ingrese hora inicio trabajo: ");
        empleado.setHora_inicio(entrada.next());
        entrada.nextLine();

        System.out.println("Ingrese hora fin trabajo: ");
        empleado.setHora_fin(entrada.next());
        entrada.nextLine();

        if (ce.nuevoEmpleado(empleado)){
            System.out.println("Empleado agregado.");
        }else System.out.println("Error al agregar el empleado.");

        MenuEmpleado();
    }
}
