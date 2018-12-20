package excepcions.ActivitatExceptions.Control;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Model.CompteEstalvi;

import java.util.ArrayList;
import java.util.List;

import static excepcions.ActivitatExceptions.Exceptions.ExceptionMessage.*;

public class OperacionsBanc {
    static List<CompteEstalvi> cuentas = new ArrayList<>();

    public static boolean verifyDNI(String dni) {
        char[] asignacionLetra = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        if(dni.length()==9){
            String dniNumber= dni.substring(0,8);
            if (dni.charAt(8) == asignacionLetra[Integer.parseInt(dniNumber)%23]){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean verifySaldo(double saldo, double cant){
        return saldo - cant >= 0;
    }

    public static CompteEstalvi noExisteCuenta(String numCompte) throws BankAccountException{
        for (CompteEstalvi compteEstalvi : cuentas) {
            if(compteEstalvi.getNumCompte().equals(numCompte)){
                return compteEstalvi;
            }
        }
        throw new BankAccountException(ACCOUNT_NOT_FOUND);
    }

    public static CompteEstalvi existeCuenta(String numCompte) throws BankAccountException{
        for (CompteEstalvi compteEstalvi : cuentas) {
            if(compteEstalvi.getNumCompte().equals(numCompte)){
                throw new BankAccountException(ACCOUNT_EXISTS);
            }
        }
        return new CompteEstalvi(numCompte);
    }

    public static void addCuenta(CompteEstalvi compteEstalvi){
        cuentas.add(compteEstalvi);
    }

    public static void transferencia(CompteEstalvi cuentaActual, double cantidad, CompteEstalvi cuentaTransferencia) throws BankAccountException{
        if (cantidad<0){
            throw new BankAccountException(TRANSFER_ERROR);
        }
        else {
            for (CompteEstalvi cuenta : cuentas) {
                if (cuenta.equals(cuentaTransferencia)) {
                    cuentaActual.treure(cantidad);
                    cuenta.ingressar(cantidad);
                }
            }
        }
    }

    public static void borrarUsuario(CompteEstalvi compteEstalvi, int posicion) throws BankAccountException{
        if(compteEstalvi.getLlista_usuaris().size() == 1){
            throw new BankAccountException(ACCOUNT_ZERO_USER);
        }else{
            compteEstalvi.removeUser(compteEstalvi.getLlista_usuaris().get(posicion-1).getDNI());
        }
    }

}
