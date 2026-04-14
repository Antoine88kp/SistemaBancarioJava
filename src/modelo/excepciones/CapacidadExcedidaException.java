
package modelo.excepciones;

public class CapacidadExcedidaException extends SistemaBancarioException {
    
    private int capacidadMaxima;
    
    public CapacidadExcedidaException(int capacidadMaxima) {
        super("Se alcanzo la capacidad maxima del sistema", "CAP_001");
        this.capacidadMaxima = capacidadMaxima;
    
    }
    
    public int getCapacidadMaxima() {
        return capacidadMaxima;
       
    }
}
