package citamedica.model.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import citamedica.model.dao.CitaDao;
import citamedica.model.entities.Cita;
import citamedica.model.entities.Cliente;
import citamedica.model.service.CitaService;

public class CitaServiceImpl implements CitaService {
	
	private static final String CITA_ANTES = "La fecha de la cita no puede ser anterior al inicio del contrato.";
	private static final String CITA_DESPUES = "La fecha de la cita no puede ser posterior al final del contrato.";
	private static final String VISITAS_MENOR = "El número de visitas debe ser de al menos 1";
	private static final String VISITAS_MAYOR = "El número de visitas realizado ha sido mayor que el contratado.";
	private static final String VALORES_NULOS = "No puede haber ningún valor nulo";
	
	@Autowired
	private CitaDao citaDao;

	public CitaDao getCitaDao() {
		return citaDao;
	}

	@Override
	public Cita getCita(int id) {
		return citaDao.getCita(id);
	}

	@Override
	public List<Cita> getAllCitas() {
		return citaDao.getAllCitas();
	}

	@Override
	public void removeCita(int id) {
		citaDao.removeCita(id);
	}

	@Override
	public ArrayList<String> saveCita(Cita cita) {
		ArrayList<String> errors = validateCita(cita);
		if(errors.isEmpty()) {
			citaDao.saveCita(cita);
		}
		return errors;
	}

	private ArrayList<String> validateCita(Cita cita) {
		ArrayList<String> errors = new ArrayList<>();
		Cliente cliente = cita.getCliente();
		if(cliente == null || cita.getFecha() == null || cita.getHora() == null || cita.getNumeroVisitasPrevistas() == null) {
			errors.add(VALORES_NULOS);
		}else {
			if(cita.getFecha().before(cliente.getInicioContrato())){
				errors.add(CITA_ANTES);
			}
			else if(cita.getFecha().after(cliente.getFinContrato())){
				errors.add(CITA_DESPUES);
			}
			if(cita.getNumeroVisitasPrevistas()<1) {
				errors.add(VISITAS_MENOR);
			}
			if(cita.getNumeroVisitasRealizadas() != null && cita.getNumeroVisitasPrevistas()<cita.getNumeroVisitasRealizadas()) {
				errors.add(VISITAS_MAYOR);
			}
		}
		return errors;
	}

	@Override
	public List<Cita> getCitasFiltered(Date desde, Date hasta, Integer cliente) {
		return citaDao.getCitasFiltered(desde, hasta, cliente);
	}
}