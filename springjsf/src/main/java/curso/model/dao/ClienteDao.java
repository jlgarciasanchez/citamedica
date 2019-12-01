package curso.model.dao;

import java.util.List;

import curso.model.entities.Cliente;


public interface ClienteDao {
    public Cliente getCliente(int id);
    public List<Cliente> getAllClientes();
}