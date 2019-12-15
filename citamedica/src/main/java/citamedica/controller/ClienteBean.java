package citamedica.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import citamedica.model.entities.Cliente;
import citamedica.model.service.ClienteService;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{clienteService}")
	private ClienteService clienteService;

	private Cliente cliente;
	private List<Cliente> clientesList;

	private String razonSocFilter;
	private String municipioFilter;
	private ArrayList<String> errors;

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public void removeCliente() {
		int id = Integer
				.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		clienteService.removeCliente(id);
	}

	public String saveCliente() {
		errors = null;
		errors = clienteService.saveCliente(cliente);
		if (errors.isEmpty()) {
			return toMostrarClientes();
		} else {
			return null;
		}
	}

	public String getRazonSocFilter() {
		return razonSocFilter;
	}

	public void setRazonSocFilter(String razonSocFilter) {
		this.razonSocFilter = razonSocFilter;
	}

	public String getMunicipioFilter() {
		return municipioFilter;
	}

	public void setMunicipioFilter(String municipioFilter) {
		this.municipioFilter = municipioFilter;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

	public String toAddCliente() {
		errors = null;
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (param != null) {
			int id = Integer.valueOf(param);
			cliente = clienteService.getCliente(id);
		} else {
			cliente = new Cliente();
		}
		return "cliente.xhtml";
	}

	public String toMostrarClientes() {
		razonSocFilter = null;
		municipioFilter = null;
		clientesList = clienteService.getAllClientes();
		return "mostrarClientes.xhtml";
	}

	public void filtrarClientes() {
		clientesList = clienteService.getClientesFiltered(razonSocFilter, municipioFilter);
	}

	public void exportarExel() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		ec.responseReset();
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"listado_clientes.xlsx\"");

		OutputStream output = null;
		try {
			output = ec.getResponseOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Clientes");

		int rowNum = 0;
		Row row = sheet.createRow(rowNum++);
		int columnNum = 0;
		Cell cell = row.createCell(columnNum++);
		cell.setCellValue("ID");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Razón social");

		cell = row.createCell(columnNum++);
		cell.setCellValue("CIF");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Direccíon");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Municipio");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Provincia");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Inicio del contrato");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Fin del contrato");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Número de reconocimientos");
		for (Cliente cliente : clientesList) {
			row = sheet.createRow(rowNum++);
			columnNum = 0;
			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getId());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getRazonSocial());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getCif());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getDireccion());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getMunicipio());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getProvincia());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getInicioContrato());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getFinContrato());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cliente.getNumReconocimientos());
		}

		try {
			workbook.write(output);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fc.responseComplete();
	}

}
