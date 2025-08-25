package service;

import types.CuentaBancaria;
import types.TipoCuenta;

public interface CuentaService {
    CuentaBancaria crearCuenta(TipoCuenta tipo, double saldoInicial);
    void depositar(String numeroCuenta, double monto);
    void retirar(String numeroCuenta, double monto);
    void transferir(String desde, String hacia, double monto);
}
