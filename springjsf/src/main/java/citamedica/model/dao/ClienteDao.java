package citamedica.model.dao;

import java.util.List;

import citamedica.model.entities.Cliente;


public interface ClienteDao {
    public Cliente getCliente(int id);
    public List<Cliente> getAllClientes();
    public void removeCliente(int id);
    public void saveCliente(Cliente cliente);
    public List<Cliente> getClientesFiltered(String razonSoc, String municipio);
}