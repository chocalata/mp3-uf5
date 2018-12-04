package excepcions.ActivitatExceptions.Model;

import java.util.List;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    private List<Client> llista_usuaris;

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        saldo = 0;
    }

    /**
        Afegeix un usuari d'aquest compte
        @param client
        @return quantitat d'usuaris que té el compte

     **/
    public int addUser(Client client) {
        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     Elimina un usuari d'aquest compte
     @param dni
     @return quantitat d'usuaris que té el compte
     @exception
     **/
    public int removeUser(String dni) {
        llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
        return llista_usuaris.size();
    }

    /**
     treure i posar usuaris -> Exception Compte sense usuari, Exception usuari no existent
     ingressar i treure diners -> Exception Compte negatiu
     transferència d'un compte a un altre
    **/

}
