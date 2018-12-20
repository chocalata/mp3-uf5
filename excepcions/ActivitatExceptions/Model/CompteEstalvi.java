package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;
import excepcions.ActivitatExceptions.Exceptions.BankAccountException;

import java.util.ArrayList;
import java.util.List;

import static excepcions.ActivitatExceptions.Exceptions.ExceptionMessage.ACCOUNT_OVERDRAFT;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    private List<Client> llista_usuaris;

    public CompteEstalvi(String numCompte) {
        this.llista_usuaris = new ArrayList<>();
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
     @throws BankAccountException
     **/
    public int removeUser(String dni) {
        llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
        return llista_usuaris.size();
    }

    /**
     * Afegeix m diners al saldo
     * @param m
     */
    public void ingressar(double m) {
        saldo += m;
    }

    /**
     * Treu m diners del compte si n'hi han suficient
     * @param m
     * @throws BankAccountException
     */
    public void treure(double m) throws BankAccountException{
        if(OperacionsBanc.verifySaldo(saldo, m)) saldo -= m;
        else throw new BankAccountException(ACCOUNT_OVERDRAFT);
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompteEstalvi compteEstalvi = (CompteEstalvi) obj;
        return this.numCompte.equals((compteEstalvi.getNumCompte()));
    }



    public void tranferir(String cuenta, double cantidad) throws BankAccountException{
        OperacionsBanc.transferencia(this,cantidad,OperacionsBanc.noExisteCuenta(cuenta));
    }
}
