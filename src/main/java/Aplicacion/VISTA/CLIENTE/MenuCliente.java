package Aplicacion.VISTA.CLIENTE;

import Aplicacion.CONTROLADOR.CLIENTE.ControladorCliente;
import Aplicacion.MODELO.DAO.CLIENTE.Cliente;
import Aplicacion.VISTA.Menu;

import java.util.Scanner;

public class MenuCliente {

    ControladorCliente cc = new ControladorCliente();

    Scanner entrada = new Scanner(System.in);

    public void MenuCliente(){

        var opcion = "";

        System.out.println("""
                1. Agregar Cliente
                2. Listar Clientes
                3. Modificar Cliente
                4. Eliminar Cliente
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
        Cliente cliente = new Cliente();

        System.out.print("Ingrese la cédula: ");
        cliente.setCedula(entrada.nextInt());
        entrada.nextLine();

        System.out.print("Ingrese los nombres: ");
        cliente.setNombre(entrada.nextLine());
        entrada.nextLine();

        System.out.print("Ingrese los apellidos: ");
        cliente.setApellido(entrada.nextLine());
        entrada.nextLine();

        System.out.print("Ingrese la fecha de nacimiento: ");
        cliente.setFecha_nacimiento(entrada.nextLine());
        entrada.nextLine();

        System.out.print("Ingrese la dirección: ");
        cliente.setDireccion(entrada.nextLine());
        entrada.nextLine();

        System.out.print("Ingrese el telefono: ");
        cliente.setTelefono(entrada.nextLine());
        entrada.nextLine();

        if (cc.nuevoCliente(cliente)){
            System.out.println("Cliente agregado.");
        }else System.out.println("Error al agregar el cliente.");

        MenuCliente();
    }
}
