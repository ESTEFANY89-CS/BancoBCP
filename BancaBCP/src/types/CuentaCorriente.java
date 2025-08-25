package types;

import exception.SaldoInsuficienteException;

public class CuentaCorriente extends CuentaBancaria{

    public static final double LIMITE_SOBREGIRO = -500.0;

    protected CuentaCorriente(double saldoInicial, TipoCuenta tipoCuenta) {
        super(saldoInicial, tipoCuenta);
    }

    @Override
    public void retirar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El monto debe ser mayor que 0");
        if (getSaldo() - monto < LIMITE_SOBREGIRO) {
            throw new SaldoInsuficienteException("Límite de sobregiro excedido.");
        }
        saldo -= monto;
    }
}
