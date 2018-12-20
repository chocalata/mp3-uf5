package excepcions.ActivitatExceptions;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;
import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;
import excepcions.ActivitatExceptions.Model.Client;
import excepcions.ActivitatExceptions.Model.CompteEstalvi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionInicio;
        int opcionCuenta;

        do {
            new LimpiarConsola();
            new Menu(2).show("Cuenta de ahorros", "Iniciar sesión", "Registrarse", "Salir");
            opcionInicio = scanner.nextInt();scanner.nextLine();

            switch(opcionInicio){
                case 1:
                    new LimpiarConsola();
                    System.out.println("En que cuenta quieres entrar? ");
                    try {
                        CompteEstalvi cuentaUsuario = OperacionsBanc.noExisteCuenta(scanner.nextLine());
                        do {
                            new LimpiarConsola();
                            new Menu(2).show("Cuenta " + cuentaUsuario.getNumCompte() + "\t\t\t" + cuentaUsuario.getSaldo() + "€",
                                    "Ingresar", "Retirar", "Tranferir", "Añadir Usuario", "Borrar usuario" ,"Salir");
                            opcionCuenta = scanner.nextInt();
                            scanner.nextLine();

                            switch (opcionCuenta) {
                                case 1:
                                    new LimpiarConsola();
                                    System.out.println("Que cantidad de dinero quieres ingresar?");
                                    cuentaUsuario.ingressar(scanner.nextDouble());scanner.nextLine();
                                    break;
                                case 2:
                                    try{
                                        new LimpiarConsola();
                                        System.out.println("Que candidad de dinero quieres retirar?");

                                        cuentaUsuario.treure(scanner.nextDouble());scanner.nextLine();
                                    }catch (BankAccountException e){
                                        System.err.println(e.getMessage());
                                        new Wait();
                                    }
                                    break;
                                case 3:
                                    new LimpiarConsola();
                                    System.out.println("Que cantidad de dinero quieres transferir?");
                                    double cantidad = scanner.nextDouble();scanner.nextLine();
                                    System.out.println("A que cuenta quieres transferirle esa cantidad?");
                                    String cuenta = scanner.nextLine();
                                    try{
                                        cuentaUsuario.tranferir(cuenta, cantidad);
                                    }catch (BankAccountException e){
                                        System.err.println(e.getMessage());
                                        new Wait();
                                    }
                                    break;
                                case 4:
                                    System.out.println("Nombre del nuevo usuario: ");
                                    String nombre = scanner.nextLine();
                                    System.out.println("Apellidos del nuevo usuario: ");
                                    String apellidos = scanner.nextLine();
                                    System.out.println("DNI del nuevo usuario: ");
                                    String dni = scanner.nextLine();
                                    try {
                                        cuentaUsuario.addUser(new Client(nombre, apellidos, dni));
                                    }catch (ClientAccountException e){
                                        System.err.println(e.getMessage());
                                        new Wait();
                                    }
                                    break;
                                case 5:
                                    try {
                                        int opcionUsuario;
                                        do {
                                            new LimpiarConsola();
                                            new Menu(cuentaUsuario.getLlista_usuaris().size()).showUsuarios("Borrar", cuentaUsuario.getLlista_usuaris());
                                            opcionUsuario = scanner.nextInt();scanner.nextLine();
                                            OperacionsBanc.borrarUsuario(cuentaUsuario, opcionUsuario);
                                        }while (opcionUsuario != 0);
                                    }catch (BankAccountException e){
                                        System.err.println(e.getMessage());
                                        new Wait();
                                    }
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Opción incorrecta.");
                                    break;

                            }
                        }while(opcionCuenta != 0);

                    } catch (BankAccountException e) {
                        System.err.println(e.getMessage());
                        new Wait();
                    }
                    break;
                case 2:
                    new LimpiarConsola();
                    String nombre;
                    String apellidos;
                    String dni;
                    System.out.println("Nombre: ");
                    nombre = scanner.nextLine();

                    System.out.println("Apellidos: ");
                    apellidos = scanner.nextLine();

                    System.out.println("Dni: ");
                    dni = scanner.nextLine();
                    try{
                        Client client = new Client(nombre, apellidos, dni);

                        System.out.println("Que numero de cuenta quieres tener.");
                        CompteEstalvi compteEstalvi = OperacionsBanc.existeCuenta(scanner.nextLine());
                        compteEstalvi.addUser(client);
                        OperacionsBanc.addCuenta(compteEstalvi);
                    }catch (BankAccountException | ClientAccountException e){
                        System.err.println(e.getMessage());
                        new Wait();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Opción Incorrecta.");
            }
        }while(opcionInicio!=0);

        try {
            Client client = new Client("pepe", "apellidodeejemplo", "53337701B");
            CompteEstalvi compteEstalvi = new CompteEstalvi("123");
            compteEstalvi.addUser(client);
            compteEstalvi.ingressar(100);
            compteEstalvi.treure(50);
            System.out.println("he sacado 50");
            compteEstalvi.treure(51);
            System.out.println("he sacado 51");
        }catch (ClientAccountException e){
            System.err.println(e.getMessage());
        }catch (BankAccountException e){
            System.err.println(e.getMessage());
        }
    }
}
