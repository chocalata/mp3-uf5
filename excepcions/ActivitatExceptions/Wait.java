package excepcions.ActivitatExceptions;

import java.util.Scanner;

public class Wait {
    //Espera a que el usuario pulse intro
    public Wait(){
        System.out.println("\nPulse intro para continuar.");
        new Scanner(System.in).nextLine();
    }
}
