package citamedica.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import citamedica.model.entities.Cita;
import citamedica.model.entities.Cliente;
import citamedica.model.service.CitaService;
import citamedica.model.service.ClienteService;

@ManagedBean
@SessionScoped
public class CitaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{citaService}")
	private CitaService citaService;
	@ManagedProperty("#{clienteService}")
	private ClienteService clienteService;

	private Cita cita;
	private List<Cita> citasList;

	private Date desdeFilter;
	private Date hastaFilter;
	private Integer clienteFilter;
	private ArrayList<String> errors;

	boolean edition = true;

	public CitaService getCitaService() {
		return citaService;
	}

	public void setCitaService(CitaService citaService) {
		this.citaService = citaService;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public List<Cita> getCitasList() {
		if (citasList == null) {
			citasList = citaService.getAllCitas();
		}
		return citasList;
	}

	public void setCitasList(List<Cita> citasList) {
		this.citasList = citasList;
	}

	public Date getDesdeFilter() {
		return desdeFilter;
	}

	public void setDesdeFilter(Date desdeFilter) {
		this.desdeFilter = desdeFilter;
	}

	public Date getHastaFilter() {
		return hastaFilter;
	}

	public void setHastaFilter(Date hastaFilter) {
		this.hastaFilter = hastaFilter;
	}

	public Integer getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(Integer clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

	public boolean isEdition() {
		return edition;
	}

	public void setEdition(boolean edition) {
		this.edition = edition;
	}

	public String toMostrarCitas() {
		desdeFilter = null;
		hastaFilter = null;
		clienteFilter = null;
		citasList = citaService.getAllCitas();
		return "mostrarCitas.xhtml";
	}

	public void filtrarCitas() {
		citasList = citaService.getCitasFiltered(desdeFilter, hastaFilter, clienteFilter);
	}

	public String toAddCita() {
		errors = null;
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		String paramNew = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("new");
		String paramEdit = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("edit");
		if (param != null) {
			//Creamos una cita nueva para el cliente.
			if (paramNew != null) {
				cita = new Cita();
				int id = Integer.valueOf(param);
				Cliente cliente = clienteService.getCliente(id);
				cita.setCliente(cliente);
			} else {
				edition = paramEdit != null;
				int id = Integer.valueOf(param);
				cita = citaService.getCita(id);
				//Las citas que ya se han realizado no se pueden editar.
				//El botón está desactivado, esta restricción está por seguridad.
				if(edition || cita.getNumeroVisitasRealizadas() != null) {
					return null;
				}
			}
		}
		return "cita.xhtml";
	}

	public String saveCita() {
		errors = null;
		errors = citaService.saveCita(cita);
		if (errors.isEmpty()) {
			return toMostrarCitas();
		} else {
			return null;
		}
	}

	public void exportarExel() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		ec.responseReset();
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"listado_citas.xlsx\"");

		OutputStream output = null;
		try {
			output = ec.getResponseOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Citas");

		int rowNum = 0;
		Row row = sheet.createRow(rowNum++);
		int columnNum = 0;
		Cell cell = row.createCell(columnNum++);
		cell.setCellValue("ID");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Contrato");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Fecha");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Hora");

		cell = row.createCell(columnNum++);
		cell.setCellValue("Numero de visitas realizadas");
		
		cell = row.createCell(columnNum++);
		cell.setCellValue("Numero de visitas previstas");

		for (Cita cita : citasList) {
			row = sheet.createRow(rowNum++);
			columnNum = 0;
			cell = row.createCell(columnNum++);
			cell.setCellValue(cita.getId());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cita.getCliente().getId());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cita.getFecha());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cita.getHora());

			cell = row.createCell(columnNum++);
			cell.setCellValue(cita.getNumeroVisitasPrevistas());
			
			cell = row.createCell(columnNum++);
			if(cita.getNumeroVisitasRealizadas() == null) {
				cell.setCellValue("");
			}else {
				cell.setCellValue(cita.getNumeroVisitasRealizadas());
			}	

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
