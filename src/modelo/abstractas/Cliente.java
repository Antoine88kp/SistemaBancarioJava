
package modelo.abstractas;

import modelo.cuentas.Cuenta;
import modelo.excepciones.CapacidadExcedidaException;

public abstract class Cliente extends Persona {
    
    private Cuenta[] cuentas;
    private int totalCuentas;
    
     public Cliente(String id, String nombre, String apellido,
                   java.time.LocalDate fechaNacimiento, String email) {

        super(id, nombre, apellido, fechaNacimiento, email);

        this.cuentas = new Cuenta[5];
        this.totalCuentas = 0;
    }
     
     
     ///****************** gestios de cuentas
     
        public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {

        if (totalCuentas >= cuentas.length) {
            throw new CapacidadExcedidaException(cuentas.length);
        }

        cuentas[totalCuentas] = cuenta;
        totalCuentas++;

    }

    public Cuenta[] getCuentas() {

        Cuenta[] copia = new Cuenta[totalCuentas];

        System.arraycopy(cuentas, 0, copia, 0, totalCuentas);

        return copia;

    }
    
}
