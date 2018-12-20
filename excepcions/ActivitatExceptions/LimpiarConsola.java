package excepcions.ActivitatExceptions;

public class LimpiarConsola {
    //Este método lo que hace es dejar 10 espacios en blanco.
    public LimpiarConsola() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}