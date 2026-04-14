
package modelo.excepciones;


public class ClienteNoEncontradoException extends SistemaBancarioException {
    
    private String idCliente;
    
    public ClienteNoEncontradoException(String idCliente) {
        
        super("Cliente no encontrado", "CLIENTE_404");
        this.idCliente = idCliente;
    
    }
    
    public String getIdCliente() {
        
        return idCliente;
        
    }
    
}
