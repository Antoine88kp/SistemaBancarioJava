package modelo.abstractas;

import java.time.LocalDate;
import modelo.excepciones.DatoInvalidoException;

public abstract class Empleado extends Persona {

    private String legajo;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private boolean activo;

    public Empleado(String id, String nombre, String apellido,
                    LocalDate fechaNacimiento, String email,
                    String legajo, LocalDate fechaContratacion,
                    double salarioBase) {

        super(id, nombre, apellido, fechaNacimiento, email);

        setLegajo(legajo);
        setFechaContratacion(fechaContratacion);
        setSalarioBase(salarioBase);
        this.activo = true;
    }

    // -------- MÉTODOS ABSTRACTOS --------

    public abstract double calcularSalario();
    public abstract double calcularBono();

    // -------- MÉTODO CONCRETO --------

    public int calcularAntiguedad() {
        return LocalDate.now().getYear() - fechaContratacion.getYear();
    }

    // -------- GETTERS --------

    public String getLegajo() {
        return legajo;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public boolean isActivo() {
        return activo;
    }

    // -------- SETTERS CON VALIDACIÓN --------

    public void setLegajo(String legajo) {
        if (legajo == null || legajo.isEmpty()) {
            throw new DatoInvalidoException("legajo", legajo);
        }
        this.legajo = legajo;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        if (fechaContratacion == null || fechaContratacion.isAfter(LocalDate.now())) {
            throw new DatoInvalidoException("fechaContratacion", fechaContratacion);
        }
        this.fechaContratacion = fechaContratacion;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase <= 0) {
            throw new DatoInvalidoException("salarioBase", salarioBase);
        }
        this.salarioBase = salarioBase;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}