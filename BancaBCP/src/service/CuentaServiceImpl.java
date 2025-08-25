package service;

import repository.ClienteRepository;
import repository.ClienteRepositoryImpl;
import repository.CuentaRepository;
import repository.CuentaRepositoryImpl;
import types.CuentaBancaria;
import types.CuentaFactory;
import types.TipoCuenta;

import java.util.Objects;

public class CuentaServiceImpl implements CuentaService{

    private final CuentaRepository repository = new CuentaRepositoryImpl();

    @Override
    public CuentaBancaria crearCuenta(TipoCuenta tipo, double saldoInicial) {
        CuentaBancaria cuenta = CuentaFactory.crearCuenta(tipo, saldoInicial);
        repository.save(cuenta);
        return cuenta;
    }

    @Override
    public void depositar(String numeroCuenta, double monto) {
        CuentaBancaria cuenta = repository.findByNumero(numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta));
        cuenta.depositar(monto);
    }

    @Override
    public void retirar(String numeroCuenta, double monto) {
        CuentaBancaria cuenta = repository.findByNumero(numeroCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta));
        cuenta.retirar(monto);
    }

    @Override
    public void transferir(String desde, String hacia, double monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto de transferencia inválido");
        CuentaBancaria cDesde = repository.findByNumero(desde)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada: " + desde));
        CuentaBancaria cHacia = repository.findByNumero(hacia)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no encontrada: " + hacia));
        cDesde.retirar(monto);
        cHacia.depositar(monto);
    }
}
