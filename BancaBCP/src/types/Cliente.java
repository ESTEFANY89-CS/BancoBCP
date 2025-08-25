package types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private List<CuentaBancaria> cuentas;

    public Cliente(String nombre, String apellido, String dni, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        if (cuenta == null) throw new IllegalArgumentException("Cuenta no puede ser null");
        cuentas.add(cuenta);
    }

    public List<CuentaBancaria> getCuentas() {
        return Collections.unmodifiableList(cuentas);
    }

    // GETTERS AND SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
}
