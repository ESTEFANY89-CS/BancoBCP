package repository;

import types.CuentaBancaria;

import java.util.*;

public class CuentaRepositoryImpl implements CuentaRepository {

    private final Map<String, CuentaBancaria> storage = new HashMap<>();

    @Override
    public void save(CuentaBancaria cuenta) {
        storage.put(cuenta.getNumeroCuenta(), cuenta);
    }

    @Override
    public Optional<CuentaBancaria> findByNumero(String numeroCuenta) {
        return Optional.ofNullable(storage.get(numeroCuenta));
    }

    @Override
    public List<CuentaBancaria> findAll() {
        return new ArrayList<>(storage.values());
    }
}
