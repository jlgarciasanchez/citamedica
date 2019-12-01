package curso.model.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import curso.model.dao.ClienteDao;
import curso.model.entities.Cliente;
import curso.model.service.ClienteService;

public class ClienteServiceImpl implements ClienteService {
	@Autowired
	private ClienteDao clienteDao;

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	@Override
	public Cliente getCliente(int id) {
		return clienteDao.getCliente(id);
	}

	@Override
	public List<Cliente> getAllClientes() {
		return clienteDao.getAllClientes();
	}
}