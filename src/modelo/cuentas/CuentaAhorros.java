package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.SaldoInsuficienteException;

public class CuentaAhorros extends Cuenta implements Consultable, Transaccionable {

    private double tasaInteres;
    private int retirosMesActual;
    private int maxRetirosMes;

    public CuentaAhorros(String numeroCuenta, double saldoInicial, double tasaInteres, int maxRetirosMes) {
        super(numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres;
        this.maxRetirosMes = maxRetirosMes;
        this.retirosMesActual = 0;
    }

    // *********** interes abstracto de cuenta

    @Override
    public double calcularInteres() {
        return getSaldo() * tasaInteres / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return 2000;
    }

    @Override
    public String getTipoCuenta() {
        return "Cuenta Ahorros";
    }

    // ---------------- TRANSACCIONES ----------------

    @Override
    public void depositar(double monto) throws CuentaBloqueadaException {

        verificarBloqueada(); // verifica si la cuenta esta bloqueada

        setSaldo(getSaldo() + monto);

    }

    @Override
    public void retirar(double monto) throws CuentaBloqueadaException, SaldoInsuficienteException {

        verificarBloqueada();

        if (monto > getSaldo()) {
            throw new SaldoInsuficienteException(getSaldo(), monto);
        }

        if (retirosMesActual >= maxRetirosMes) {
            throw new RuntimeException("Se alcanzó el máximo de retiros del mes");
        }

        setSaldo(getSaldo() - monto);
        retirosMesActual++;
    }

    @Override
    public double calcularComision(double monto) {
        return monto * 0.01;
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
    }

    // ---------------- CONSULTABLE ----------------

    @Override
    public String obtenerResumen() {

        return "Cuenta: " + getNumeroCuenta() +
               " Tipo: Ahorros" +
               " Saldo: " + getSaldo();

    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CuentaAhorros";
    }

}