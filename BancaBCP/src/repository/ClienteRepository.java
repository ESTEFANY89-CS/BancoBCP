package repository;

import types.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    void save(Cliente cliente);
    Optional<Cliente> findByDni(String dni);
    boolean existeCliente(String dni);
}
