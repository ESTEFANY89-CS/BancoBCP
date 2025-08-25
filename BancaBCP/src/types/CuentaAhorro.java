package types;

import exception.SaldoInsuficienteException;

public class CuentaAhorro extends CuentaBancaria{


    protected CuentaAhorro(double saldoInicial, TipoCuenta tipoCuenta) {
        super(saldoInicial, tipoCuenta);
    }

    @Override
    public void retirar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El monto debe ser mayor que 0");
        if (getSaldo() - monto < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente: la cuenta de ahorro no puede quedar en negativo.");
        }
        saldo -= monto;
    }

}
