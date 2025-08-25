package repository;

import types.Cliente;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final Map<String, Cliente> storage = new HashMap<>(); // clave = dni

    @Override
    public void save(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("cliente null");
        storage.put(cliente.getDni(), cliente);
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        if (dni == null) return Optional.empty();
        return Optional.ofNullable(storage.get(dni));
    }

    @Override
    public boolean existeCliente(String dni) {
        if (dni == null) return false;
        return storage.containsKey(dni);
    }
}
