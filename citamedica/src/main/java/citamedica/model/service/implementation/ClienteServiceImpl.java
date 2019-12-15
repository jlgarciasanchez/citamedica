package citamedica.model.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import citamedica.model.dao.ClienteDao;
import citamedica.model.entities.Cliente;
import citamedica.model.service.ClienteService;

public class ClienteServiceImpl implements ClienteService {
	
	private static final String FECHA_ERROR = "La fecha de inicio del contrato debe ser antrior a la fecha de fin del contrato";
	private static final String RECONOCIMIENTOS_MENOR = "El número de reconocimientos debe ser de al menos 1";
	private static final String VALORES_NULOS = "No puede haber ningún valor nulo";
	
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

	@Override
	public void removeCliente(int id) {
		clienteDao.removeCliente(id);
	}

	@Override
	public ArrayList<String> saveCliente(Cliente cliente) {
		ArrayList<String> errors = validateCliente(cliente);
		if(errors.isEmpty()) {
			clienteDao.saveCliente(cliente);
		}
		return errors;
	}

	@Override
	public List<Cliente> getClientesFiltered(String razonSoc, String municipio) {
		return clienteDao.getClientesFiltered(razonSoc, municipio);
	}
	
	private ArrayList<String> validateCliente(Cliente cliente) {
		ArrayList<String> errors = new ArrayList<>();
		if(cliente.getRazonSocial() == null|| cliente.getCif() == null|| cliente.getDireccion() == null
				|| cliente.getProvincia() == null || cliente.getMunicipio() == null || cliente.getInicioContrato() == null
				|| cliente.getFinContrato() == null) {
			errors.add(VALORES_NULOS);
		}else {
			if(!cliente.getInicioContrato().before(cliente.getFinContrato())) {
				errors.add(FECHA_ERROR);
			}
			if(cliente.getNumReconocimientos() < 1) {
				errors.add(RECONOCIMIENTOS_MENOR);
			}
		}
		return errors;
	}
}