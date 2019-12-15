package citamedica.model.service;

import java.util.ArrayList;
import java.util.List;

import citamedica.model.entities.Cliente;

public interface ClienteService {
    public Cliente getCliente(int id);
    public List<Cliente> getAllClientes();
    public void removeCliente(int id);
    public ArrayList<String> saveCliente(Cliente cliente);
    public List<Cliente> getClientesFiltered(String razonSoc, String municipio);
}
