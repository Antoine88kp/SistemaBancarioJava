package modelo.personas;


import modelo.abstractas.Cliente;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;
import modelo.interfaces.Auditable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 Esta clase representa un cliente empresarial (empresa).
 
 Hereda de Cliente y además implementa las interfaces
 Consultable, Notificable y Auditable.
*/
public class ClienteEmpresarial extends Cliente implements Consultable, Notificable, Auditable {

   
    private String nit;   
    private String razonSocial;   
    private String representanteLegal;
    

    // -------- ATRIBUTOS DE AUDITORÍA --------

    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    

    // -------- ATRIBUTOS DE NOTIFICACIÓN --------

    private boolean aceptaNotificaciones;
    private String contacto;

    /*
     Constructor de la clase.
     Recibe los datos necesarios para crear una empresa en el sistema.
    */
    public ClienteEmpresarial(String id, String nombre, String apellido,
                              LocalDate fechaNacimiento, String email,
                              String nit, String razonSocial,
                              String representanteLegal,
                              String contacto, boolean aceptaNotificaciones) {

        // Llamamos al constructor de Cliente (y este a Persona)
        super(id, nombre, apellido, fechaNacimiento, email);

        this.nit = nit;
        this.razonSocial = razonSocial;
        this.representanteLegal = representanteLegal;

        this.contacto = contacto;
        this.aceptaNotificaciones = aceptaNotificaciones;

        // Auditoría
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
    }

    // ================= PERSONA =================

    /*
     Calcula la edad de la empresa según la fecha de registro
     (usamos fechaNacimiento heredada de Persona).
    */
    @Override
    public int calcularEdad() {

        return LocalDate.now().getYear() - getFechaNacimiento().getYear();

    }

    /*
     Identifica el tipo concreto de persona.
    */
    @Override
    public String obtenerTipo() {

        return "ClienteEmpresarial";

    }

    /*
     Retorna el documento principal de la empresa (NIT).
    */
    @Override
    public String obtenerDocumentoIdentidad() {

        return "NIT " + nit;

    }

    // ================= CONSULTABLE =================

    /*
     Devuelve un resumen de la empresa.
    */
    @Override
    public String obtenerResumen() {

        return "Empresa: " + razonSocial +
               " | NIT: " + nit +
               " | Representante: " + representanteLegal;

    }

    /*
     Indica si la empresa está activa en el sistema.
    */
    @Override
    public boolean estaActivo() {

        return true;

    }

    // ================= NOTIFICABLE =================

    /*
     Envía un mensaje a la empresa si acepta notificaciones.
    */
    @Override
    public void notificar(String mensaje) {

        if (aceptaNotificaciones) {

            System.out.println("Notificación para empresa " +
                    razonSocial + ": " + mensaje);

        }

    }

    /*
     Devuelve el medio de contacto de la empresa.
    */
    @Override
    public String obtenerContacto() {

        return contacto;

    }

    /*
     Indica si la empresa acepta notificaciones.
    */
    @Override
    public boolean aceptaNotificaciones() {

        return aceptaNotificaciones;

    }

    // ================= AUDITABLE =================

    /*
     Retorna la fecha de creación del registro.
    */
    @Override
    public LocalDateTime obtenerFechaCreacion() {

        return fechaCreacion;

    }

    /*
     Retorna la última modificación del registro.
    */
    @Override
    public LocalDateTime obtenerUltimaModificacion() {

        return ultimaModificacion;

    }

    /*
     Devuelve quién modificó el registro por última vez.
    */
    @Override
    public String obtenerUsuarioModificacion() {

        return usuarioModificacion;

    }

    /*
     Registra una modificación del cliente empresarial.
    */
    @Override
    public void registrarModificacion(String usuario) {

        this.usuarioModificacion = usuario;
        this.ultimaModificacion = LocalDateTime.now();

    }

}