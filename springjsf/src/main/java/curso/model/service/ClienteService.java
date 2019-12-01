package curso.model.service;

import java.util.List;

import curso.model.entities.Cliente;

public interface ClienteService {
    public Cliente getCliente(int id);
    public List<Cliente> getAllClientes();
}
