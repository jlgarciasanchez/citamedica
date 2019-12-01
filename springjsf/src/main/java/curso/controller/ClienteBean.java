package curso.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import curso.model.entities.Cliente;
import curso.model.service.ClienteService;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
        //inject spring bean via DI
	@ManagedProperty("#{clienteService}")
	private ClienteService clienteService;
	private List<Cliente> clientesList;
	


	public ClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	public List<Cliente> getClientesList() {
		if (clientesList == null) {
			clientesList = clienteService.getAllClientes();
		}
		return clientesList;
	}
	public void setClientesList(List<Cliente> clientesList) {
		this.clientesList = clientesList;
	}
}
