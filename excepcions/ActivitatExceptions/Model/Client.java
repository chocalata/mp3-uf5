package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;

import static excepcions.ActivitatExceptions.Exceptions.ExceptionMessage.WRONG_DNI;

public class Client {
    private String Nom;
    private String Cognoms;
    private String DNI;

    public Client(String nom, String cognoms, String DNI) throws ClientAccountException{
        Nom = nom;
        Cognoms = cognoms;
        if(OperacionsBanc.verifyDNI(DNI)) setDNI(DNI);
        else new ClientAccountException(WRONG_DNI);

    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void transferencia(CompteEstalvi font, CompteEstalvi desti, double suma) {
        //TODO implementar transferència
    }

}
