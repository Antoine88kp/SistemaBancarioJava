
package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.SaldoInsuficienteException;

public class CuentaCorriente extends Cuenta implements Consultable, Transaccionable {
   
    private double montoSobregiro;
    private double comisionMantenimiento;
    
    public CuentaCorriente(String numeroCuenta, double saldoInicial,double montoSobregiro, double comisionMantenimiento) {
        
        super (numeroCuenta, saldoInicial);
        this.montoSobregiro = montoSobregiro;
        this.comisionMantenimiento = comisionMantenimiento; 
    }
    // **************** inereses *****************
    
    @Override
    public double calcularInteres(){
        return 0;
    }
    
     @Override
    public double getLimiteRetiro() {
        return getSaldo() + montoSobregiro;
    }

    @Override
    public String getTipoCuenta() {
        return "Cuenta Corriente";
    }
    
    // **************** transacciones  *******************
    
    @Override
    public void depositar(double monto) throws CuentaBloqueadaException {

        verificarBloqueada();

        setSaldo(getSaldo() + monto);

    }
     @Override
    public void retirar(double monto)
            throws CuentaBloqueadaException, SaldoInsuficienteException {

        verificarBloqueada();

        if (monto > getSaldo() + montoSobregiro) {
            throw new SaldoInsuficienteException(getSaldo(), monto);
        }

        setSaldo(getSaldo() - monto);

    }
    
     @Override
    public double calcularComision(double monto) {
        return comisionMantenimiento;
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
    }

    // **************** CONSULTABLE ****************

    @Override
    public String obtenerResumen() {

        return "Cuenta Corriente - Numero: " + getNumeroCuenta() +
               " Saldo: " + getSaldo();

    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CuentaCorriente";
    }
}
    
