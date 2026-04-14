
package modelo.excepciones;

public class CuentaBloqueadaException extends SistemaBancarioException {
    
    public CuentaBloqueadaException() {
        
        super("La cuenta esta bloqueada", "CUENTA_001");
       
    }
    
}
