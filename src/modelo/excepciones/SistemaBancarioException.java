
package modelo.excepciones;

import java.time.LocalDateTime;

public class SistemaBancarioException {
    private String codigoError;
    private LocalDateTime timestamp;
    
    public SistemaBancarioException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.timestamp = LocalDateTime.now();
    }

    public String getCodigoError() {
        return codigoError;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString(){
        return "Error; " + getmessage() + 
               " codigo: " + codigoError + 
               " Fecha: " + timestamp;
    }
}
