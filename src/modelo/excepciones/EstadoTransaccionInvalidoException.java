package modelo.excepciones;

public class EstadoTransaccionInvalidoException extends BancoRuntimeException {
    
    public EstadoTransaccionInvalidoException(String mensaje) {
        super(mensaje);
    
    }
    
}
