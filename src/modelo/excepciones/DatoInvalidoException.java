
package modelo.excepciones;

public class DatoInvalidoException extends BancoRuntimeException {

    private String campo;
    private Objeto valorRecibilido;
    
    public DatoInvalidoException(String campo, Object valorRecibido) {
        super("Dato invalido en el campo: " + campo);
        this.campo = campo;
        this.valorRecibido = valorRecibido;
    }
}
