package Modelo.interfaces;

public interface Transaccionable {
    
    void depositar(double monto);
    void retirar(double monto);
    double calcularComision(double monto);
    double consultarSaldo();

}