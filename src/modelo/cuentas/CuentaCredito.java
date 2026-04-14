
package modelo.cuentas;


public class CuentaCredito extends Cuenta implements Consultable, Transaccionable {
    
    private double limiteCredito;
    private double tasaInteres;
    private double deudaActual;

    public CuentaCredito(String numeroCuenta, double limiteCredito, double tasaInteres) {
        
        super (numeroCuenta, 0);
        
        this.limiteCredito = limiteCredito;
        this.tasaInteres = tasaInteres;
        this.deudaActual = 0;
    } 
    // ******************* metodos de cuenta 

     @Override
    public double calcularInteres() {
        return deudaActual * tasaInteres / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return limiteCredito - deudaActual;
    }

    @Override
    public String getTipoCuenta() {
        return "Cuenta Credito";
    }
    
    //***************** transaccion
    
    @Override
    public void depositar(double monto) throws CuentaBloqueadaException {
        verificarBloqueada();
        deudaActual -= monto;

        if (deudaActual < 0) {
            deudaActual = 0;
        }
    }

    @Override
    public void retirar(double monto) throws CuentaBloqueadaException {
        verificarBloqueada();
        if (monto > (limiteCredito - deudaActual)) {
            throw new RuntimeException("Se excede el limite de credito");
        }
        deudaActual += monto;
    }

    @Override
    public double calcularComision(double monto) {
        return monto * 0.02;
    }

    @Override
    public double consultarSaldo() {
        return limiteCredito - deudaActual;
    }
    //************** consultable
    
    @Override
    public String obtenerResumen() {

        return "Cuenta Credito - Numero: " + getNumeroCuenta() +
               " Deuda actual: " + deudaActual +
               " Limite disponible: " + consultarSaldo();
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CuentaCredito";
    }
}
