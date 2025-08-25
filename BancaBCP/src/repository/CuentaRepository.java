package repository;

import types.CuentaBancaria;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository {
    void save(CuentaBancaria cuenta);
    Optional<CuentaBancaria> findByNumero(String numeroCuenta);
    List<CuentaBancaria> findAll();
}
