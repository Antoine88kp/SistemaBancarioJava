
package modelo.excepciones;


public class SaldoInsuficienteException extends SistemaBancarioException{
    
    private double saldoActual;
    private double montoSolicitado;
    
    public SaldosuficienteException(double saldoActual, double montoSolicitado){
    
        super("Saldo insufciente para realizar la operacion", "saldo_001");
        this.saldoActual = saldoActual;
        this.montoSolicitado = montoSolicitado;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }


}