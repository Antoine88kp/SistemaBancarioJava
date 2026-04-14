package modelo.personas;

import modelo.abstractas.Cliente;
import modelo.enums.TipoDocumento;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;
import modelo.interfaces.Auditable;

import java.time.LocalDate;
import java.time.LocalDateTime;

// esta clase se hereda de la clase abstracta Cliente 

public class ClienteNatural extends Cliente implements Consultable, Notificable, Auditable {
    //enums que define el tipo de documento y si numero 
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

    // auditoria 
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    // sistema notificaciones
    private boolean aceptaNotificaciones;
    private String contacto;
    
    /*
     Constructor de la clase.
     donde inicializamos los datos del cliente cuando se crea.
    */

    public ClienteNatural(String id, String nombre, String apellido,
                          LocalDate fechaNacimiento, String email,
                          TipoDocumento tipoDocumento, String numeroDocumento,
                          String contacto, boolean aceptaNotificaciones) {

        super(id, nombre, apellido, fechaNacimiento, email); //super cliente

        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.contacto = contacto;
        this.aceptaNotificaciones = aceptaNotificaciones;

        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
    }

    // ************* PERSONA 

    @Override
    public int calcularEdad() {

        return LocalDate.now().getYear() - getFechaNacimiento().getYear();

    }

    @Override
    public String obtenerTipo() {

        return "ClienteNatural";

    }

    @Override
    public String obtenerDocumentoIdentidad() {

        return tipoDocumento + " " + numeroDocumento;

    }

    // ***************** CONSULTABLE 

    @Override
    public String obtenerResumen() {

        return "Cliente Natural: " + getNombreCompleto() +
               " Documento: " + numeroDocumento;

    }

    @Override
    public boolean estaActivo() {

        return true;

    }

    // ************ NOTIFICABLE 

    @Override
    public void notificar(String mensaje) {

        if (aceptaNotificaciones) {
            System.out.println("Notificacion para " + getNombreCompleto() + ": " + mensaje);
        }

    }

    @Override
    public String obtenerContacto() {

        return contacto; // retorna emails o telefono

    }

    @Override
    public boolean aceptaNotificaciones() {

        return aceptaNotificaciones; // verifica si el clienten acepto la notificacion o no 

    }

    //*********** AUDITABLE 

    @Override
    public LocalDateTime obtenerFechaCreacion() { //fehca de creacion

        return fechaCreacion;

    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {// ultima fecha de modificacion

        return ultimaModificacion;

    }

    @Override
    public String obtenerUsuarioModificacion() {

        return usuarioModificacion;

    }
    
     /*
     Registra una modificación del cliente.
     Guarda quién hizo el cambio y cuándo.
    */

    @Override
    public void registrarModificacion(String usuario) {

        this.usuarioModificacion = usuario;
        this.ultimaModificacion = LocalDateTime.now();

    }

}