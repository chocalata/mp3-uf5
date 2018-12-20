package excepcions.ActivitatExceptions;

import excepcions.ActivitatExceptions.Model.Client;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    int longitud;

    public Menu(int longitud){
        this.longitud=longitud;
    }
    //Este método imprime un menú con los parámetros que le pases.
    public void show(String titulo,String ...lista){

        System.out.println("\033[104;97m" +  String.format("%-40s", titulo) + "\033[0m\n");
        for (int i = 0; i <lista.length ; i++) {
            if(i == lista.length-1 && lista[i]!=null) {
                System.out.println("\n0) " + lista[i]);
            } else if (lista[i]!=null) {
                System.out.println(i + 1 + ") " + lista[i]);
            }
        }
        System.out.println("");
    }
    
    public void showUsuarios(String titulo, List<Client> usuarios){
        System.out.println("\033[104;97m" +  String.format("%-40s", titulo) + "\033[0m\n");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(i + 1 + ") " + usuarios.get(i));
        }
        System.out.println("\n0) Salir");
        System.out.println("");
    }


}