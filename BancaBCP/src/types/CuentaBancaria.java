package types;

public abstract class CuentaBancaria {
    private static int contador = 1;
    private final String numeroCuenta;
    protected double saldo;
    private final TipoCuenta tipo;

    protected CuentaBancaria(double saldo, TipoCuenta tipoCuenta) {
        this.numeroCuenta = generarNumeroCuenta();
        this.saldo = saldo;
        this.tipo = tipoCuenta;
    }

    public void depositar(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a depositar debe ser mayor que cero.");
        }
        this.saldo += cantidad;
        System.out.println("Depósito exitoso de " + cantidad + ". Saldo actual: " + saldo);
    }

    public abstract void retirar(double monto);

    private String generarNumeroCuenta() {
        String prefijo = (tipo == TipoCuenta.AHORRO) ? "AHO" : "COR";
        return prefijo + "-" + String.format("%04d", contador++); // ejemplo: AHO-0001
    }

    // getters and setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }
}
