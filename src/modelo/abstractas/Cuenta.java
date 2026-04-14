package modelo.abstractas;

import java.time.LocalDateTime;
import modelo.banco.Transaccion;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.interfaces.Auditable;

public abstract class Cuenta implements Auditable {

    private String numeroCuenta;
    private double saldo;
    private boolean bloqueada;

    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;

    private Transaccion[] historial;
    private int totalTransacciones;

    public Cuenta(String numeroCuenta, double saldoInicial) {

        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.bloqueada = false;

        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();

        this.historial = new Transaccion[20];
        this.totalTransacciones = 0;
    }

    // ---------------- MÉTODOS ABSTRACTOS ----------------

    public abstract double calcularInteres();

    public abstract double getLimiteRetiro();

    public abstract String getTipoCuenta();

    // ---------------- MÉTODOS CONCRETOS ----------------

    public void verificarBloqueada() throws CuentaBloqueadaException {

        if (bloqueada) {
            throw new CuentaBloqueadaException();
        }

    }

    public void agregarAlHistorial(Transaccion t) throws CapacidadExcedidaException {

        if (totalTransacciones >= historial.length) {
            throw new CapacidadExcedidaException(historial.length);
        }

        historial[totalTransacciones] = t;
        totalTransacciones++;

    }

    public Transaccion[] getHistorial() {

        Transaccion[] copia = new Transaccion[totalTransacciones];

        System.arraycopy(historial, 0, copia, 0, totalTransacciones);

        return copia;

    }

    // ---------------- GETTERS ----------------

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void bloquearCuenta() {
        this.bloqueada = true;
    }

    public void desbloquearCuenta() {
        this.bloqueada = false;
    }

    // ---------------- AUDITABLE ----------------

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return ultimaModificacion;
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return usuarioModificacion;
    }

    @Override
    public void registrarModificacion(String usuario) {

        this.usuarioModificacion = usuario;
        this.ultimaModificacion = LocalDateTime.now();

    }
}
